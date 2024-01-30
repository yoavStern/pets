package com.signalpet.pets.repository;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@Service
public class S3StorageService implements StorageService, InitializingBean {
    private final AmazonS3 s3client;
    private final String bucketName;

    public S3StorageService(AmazonS3 s3client, @Value("${cloud.aws.s3.bucket-name}") String bucketName) {
        this.s3client = s3client;
        this.bucketName = bucketName;
    }

    @Override
    public void afterPropertiesSet() {
        boolean bucketExist = s3client.doesBucketExistV2(bucketName);
        if (!bucketExist) {
            s3client.createBucket(bucketName);
        }
    }

    @Override
    public void uploadFile(String key, MultipartFile multipartFile) throws IOException {
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(multipartFile.getContentType());
        data.setContentLength(multipartFile.getSize());
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, multipartFile.getInputStream(), data);
        s3client.putObject(putObjectRequest);
    }

    @Override
    public void deleteFile(String name) {
        s3client.deleteObject(bucketName, name);
    }

    public InputStream downloadFileById(String keyName) throws IOException {
        S3Object s3object = this.s3client.getObject(bucketName, keyName);
        return s3object.getObjectContent();
    }

}
