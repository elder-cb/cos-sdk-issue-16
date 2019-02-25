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

## Running the test with docker

The test can be ran within docker to remove dependencies from the environment:

- build the image with `docker build -t 'cos-sdk-issue-16' .`
- run the image with `docker run cos-sdk-issue-16`

Output (part of it):

```
$ docker run cos-sdk-issue-16
To honour the JVM settings for this build a new JVM will be forked. Please consider using the daemon: https://docs.gradle.org/5.2.1/userguide/gradle_daemon.html.
Daemon will be stopped at the end of the build stopping after processing
> Task :compileJava UP-TO-DATE
> Task :processResources UP-TO-DATE
> Task :classes UP-TO-DATE

> Task :run
java.nio.channels.ClosedChannelException
        at sun.nio.ch.FileChannelImpl.ensureOpen(FileChannelImpl.java:122)
        at sun.nio.ch.FileChannelImpl.position(FileChannelImpl.java:288)
        at com.ibm.cloud.objectstorage.internal.ResettableInputStream.reset(ResettableInputStream.java:174)
        at com.ibm.cloud.objectstorage.internal.SdkFilterInputStream.reset(SdkFilterInputStream.java:112)
        at com.ibm.cloud.objectstorage.services.s3.internal.InputSubstream.reset(InputSubstream.java:110)
        at com.ibm.cloud.objectstorage.services.s3.AmazonS3Client.uploadPart(AmazonS3Client.java:2811)
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.UploadPartCallable.call(UploadPartCallable.java:33)
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.UploadPartCallable.call(UploadPartCallable.java:23)
        at java.util.concurrent.FutureTask.run(FutureTask.java:277)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1160)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
        at java.lang.Thread.run(Thread.java:812)
Exception in thread "main" com.ibm.cloud.objectstorage.SdkClientException: Unable to complete multi-part upload. Individual part upload failed : Failed to mark the file position
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.CompleteMultipartUpload.collectPartETags(CompleteMultipartUpload.java:128)
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.CompleteMultipartUpload.call(CompleteMultipartUpload.java:89)
        at com.ibm.cloud.objectstorage.services.s3.transfer.internal.CompleteMultipartUpload.call(CompleteMultipartUpload.java:40)
        at java.util.concurrent.FutureTask.run(FutureTask.java:277)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1160)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
        at java.lang.Thread.run(Thread.java:812)
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
        at sun.nio.ch.FileChannelImpl.ensureOpen(FileChannelImpl.java:122)
        at sun.nio.ch.FileChannelImpl.position(FileChannelImpl.java:265)
        at com.ibm.cloud.objectstorage.internal.ResettableInputStream.mark(ResettableInputStream.java:146)
        ... 23 more

> Task :run FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':run'.
> Process 'command '/opt/ibm/java/bin/java'' finished with non-zero exit value 1

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 17s
3 actionable tasks: 1 executed, 2 up-to-date
```
