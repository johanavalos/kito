package com.example.hiber_api.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public S3Client getS3Client(){
        AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        return S3Client.builder()
            .region(Region.of(region))    
            .endpointOverride(URI.create("https://s3." + region + ".amazonaws.com"))
            .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
            .build();
    }

    @Bean
    public S3AsyncClient getS3AsyncClient(){
        AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        return S3AsyncClient.builder()
            .region(Region.of(region))    
            .endpointOverride(URI.create("https://s3." + region + ".amazonaws.com"))
            .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
            .build();
    }
}
