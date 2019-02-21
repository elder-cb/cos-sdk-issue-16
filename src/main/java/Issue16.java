import java.io.File;
import java.util.Properties;

import com.ibm.cloud.objectstorage.AmazonClientException;
import com.ibm.cloud.objectstorage.AmazonServiceException;
import com.ibm.cloud.objectstorage.ClientConfiguration;
import com.ibm.cloud.objectstorage.auth.AWSCredentials;
import com.ibm.cloud.objectstorage.auth.AWSStaticCredentialsProvider;
import com.ibm.cloud.objectstorage.auth.BasicAWSCredentials;
import com.ibm.cloud.objectstorage.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.ibm.cloud.objectstorage.oauth.BasicIBMOAuthCredentials;
import com.ibm.cloud.objectstorage.services.s3.AmazonS3;
import com.ibm.cloud.objectstorage.services.s3.AmazonS3ClientBuilder;
import com.ibm.cloud.objectstorage.services.s3.model.PutObjectRequest;
import com.ibm.cloud.objectstorage.services.s3.transfer.TransferManager;
import com.ibm.cloud.objectstorage.services.s3.transfer.TransferManagerBuilder;
import com.ibm.cloud.objectstorage.services.s3.transfer.Upload;

public class Issue16 {

	public static void main(String[] args) throws Exception {
		Issue16 issue = new Issue16();
		Properties props = new Properties();
		props.load(Issue16.class.getClassLoader().getResourceAsStream("testconfig.properties"));

		String apiKey = props.getProperty("API_KEY");
		String instanceId = props.getProperty("SERVICE_INSTANCE_ID");
		String endpoint = props.getProperty("ENDPOINT");
		String location = props.getProperty("LOCATION");
		String bucketName = props.getProperty("BUCKET_NAME");
		String key = props.getProperty("KEY");
		issue.run(apiKey, instanceId, endpoint, location, bucketName, key);
	}

	AmazonS3 client;

	void run(String apiKey, String instanceId, String endpoint, String location, String bucketName, String key)
			throws AmazonServiceException, AmazonClientException, InterruptedException {
		client = buildClient(apiKey, instanceId, endpoint, location);
		test(bucketName, key);
	}

	AmazonS3 buildClient(String apiKey, String instanceId, String endpoint, String location) {

		AWSCredentials credentials;
		if (endpoint.contains("objectstorage.softlayer.net")) {
			credentials = new BasicIBMOAuthCredentials(apiKey, instanceId);
		} else {
			String access_key = apiKey;
			String secret_key = instanceId;
			credentials = new BasicAWSCredentials(access_key, secret_key);
		}
		ClientConfiguration clientConfig = new ClientConfiguration().withRequestTimeout(5000);
		clientConfig.setUseTcpKeepAlive(true);

		AmazonS3 s3 = AmazonS3ClientBuilder.standard() //
				.withCredentials(new AWSStaticCredentialsProvider(credentials)) //
				.withEndpointConfiguration(new EndpointConfiguration(endpoint, location)) //
				.withPathStyleAccessEnabled(true) //
				.withClientConfiguration(clientConfig) //
				.build();
		return s3;
	}

	public void test(String bucketName, String key)
			throws AmazonServiceException, AmazonClientException, InterruptedException {
		// test file created with head -c $((15*1024*1024)) /dev/random > fileLargerThanOnePart.rnd
		File localFile = new File(this.getClass().getClassLoader().getResource("fileLargerThanOnePart.rnd").getFile());
		PutObjectRequest putRequest = new PutObjectRequest(bucketName, key, null, null);
		putRequest.setFile(localFile);
		Long partSize = 5 * 1024 * 1024L;
		TransferManager transferManager = TransferManagerBuilder.standard() //
				.withS3Client(this.client) //
				.withMinimumUploadPartSize(partSize) //
				.withMultipartCopyPartSize(partSize) //
				.withMultipartUploadThreshold(partSize) //
				.build();
		try {
			Upload upload = transferManager.upload(putRequest);
			upload.waitForCompletion();
		} finally {
			transferManager.shutdownNow(true);
		}
	}
}
