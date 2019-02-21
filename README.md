# cos-sdk-issue-16
Test application for [IBM COS SDK Java issue 16](https://github.com/IBM/ibm-cos-sdk-java/issues/16)

Update file `src/main/resources/testconfig.properties` with the credentials to access a COS instance, then run `./gradlew run`

Expected result of the test:
```
java.nio.channels.ClosedChannelException
        at sun.nio.ch.FileChannelImpl.ensureOpen(FileChannelImpl.java:110)
        at sun.nio.ch.FileChannelImpl.position(FileChannelImpl.java:276)
        at com.ibm.cloud.objectstorage.internal.ResettableInputStream.reset(ResettableInputStream.java:174)
        at com.ibm.cloud.objectstorage.internal.SdkFilterInputStream.reset(SdkFilterInputStream.java:112)
        at com.ibm.cloud.objectstorage.services.s3.internal.InputSubstream.reset(InputSubstream.java:110)
        at com.ibm.cloud.objectstorage.services.s3.AmazonS3Client.uploadPart(AmazonS3Client.java:2811)
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.UploadPartCallable.call(UploadPartCallable.java:33)
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.UploadPartCallable.call(UploadPartCallable.java:23)
        at java.util.concurrent.FutureTask.run(FutureTask.java:266)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
        at java.lang.Thread.run(Thread.java:748)
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.CompleteMultipartUpload.collectPartETags(CompleteMultipartUpload.java:128)
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.CompleteMultipartUpload.call(CompleteMultipartUpload.java:89)
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.CompleteMultipartUpload.call(CompleteMultipartUpload.java:40)
        at java.util.concurrent.FutureTask.run(FutureTask.java:266)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
        at java.lang.Thread.run(Thread.java:748)
Caused by: com.ibm.cloud.objectstorage.SdkClientException: Failed to mark the file position
        at com.ibm.cloud.objectstorage.internal.ResettableInputStream.mark(ResettableInputStream.java:148)
        at com.ibm.cloud.objectstorage.internal.SdkFilterInputStream.mark(SdkFilterInputStream.java:106)
        at com.ibm.cloud.objectstorage.services.s3.internal.InputSubstream.mark(InputSubstream.java:104)
        at com.ibm.cloud.objectstorage.internal.SdkFilterInputStream.mark(SdkFilterInputStream.java:106)
        at com.ibm.cloud.objectstorage.services.s3.internal.MD5DigestCalculatingInputStream.mark(MD5DigestCalculatingInputStream.java:95)
        at com.ibm.cloud.objectstorage.internal.SdkFilterInputStream.mark(SdkFilterInputStream.java:106)
        at com.ibm.cloud.objectstorage.internal.SdkFilterInputStream.mark(SdkFilterInputStream.java:106)
        at com.ibm.cloud.objectstorage.http.AmazonHttpClient$RequestExecutor.executeHelper(AmazonHttpClient.java:998)
        at com.ibm.cloud.objectstorage.http.AmazonHttpClient$RequestExecutor.doExecute(AmazonHttpClient.java:742)
        at com.ibm.cloud.objectstorage.http.AmazonHttpClient$RequestExecutor.executeWithTimer(AmazonHttpClient.java:716)
        at com.ibm.cloud.objectstorage.http.AmazonHttpClient$RequestExecutor.execute(AmazonHttpClient.java:699)
        at com.ibm.cloud.objectstorage.http.AmazonHttpClient$RequestExecutor.access$500(AmazonHttpClient.java:667)
        at com.ibm.cloud.objectstorage.http.AmazonHttpClient$RequestExecutionBuilderImpl.execute(AmazonHttpClient.java:649)
        at com.ibm.cloud.objectstorage.http.AmazonHttpClient.execute(AmazonHttpClient.java:513)
        at com.ibm.cloud.objectstorage.services.s3.AmazonS3Client.invoke(AmazonS3Client.java:3884)
        at com.ibm.cloud.objectstorage.services.s3.AmazonS3Client.invoke(AmazonS3Client.java:3831)
        at com.ibm.cloud.objectstorage.services.s3.AmazonS3Client.doUploadPart(AmazonS3Client.java:2845)
        at com.ibm.cloud.objectstorage.services.s3.AmazonS3Client.uploadPart(AmazonS3Client.java:2830)
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.UploadPartCallable.call(UploadPartCallable.java:33)
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.UploadPartCallable.call(UploadPartCallable.java:23)
        ... 4 more
Caused by: java.nio.channels.ClosedChannelException
        at sun.nio.ch.FileChannelImpl.ensureOpen(FileChannelImpl.java:110)
        at sun.nio.ch.FileChannelImpl.position(FileChannelImpl.java:253)
        at com.ibm.cloud.objectstorage.internal.ResettableInputStream.mark(ResettableInputStream.java:146)
        ... 23 more
```
