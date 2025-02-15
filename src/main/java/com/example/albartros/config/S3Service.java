package com.example.albartros.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;

    @Value("${aws.bucketName}")
    private String bucketName;

    public String uploadFile(byte[] data, String mimeType, String generatedName) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(generatedName)
                .contentType(mimeType)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(data));
        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, generatedName);
    }

    public void deleteFile(String bucketName, String generatedName) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(generatedName)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }
}
