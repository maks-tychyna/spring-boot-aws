package com.maks.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfiguration {

    @Value("${cloud.aws.region.name}")
    private String region;

    @Bean
    public AmazonS3 amazonS3Client(AWSCredentialsProvider credentialsProvider) {
        return AmazonS3ClientBuilder.standard()
                                    .withCredentials(credentialsProvider)
                                    .withRegion(region)
                                    .build();
    }

    @Bean
    public AmazonCloudWatch amazonCloudWatch(AWSCredentialsProvider credentialsProvider) {
        return AmazonCloudWatchClientBuilder.standard()
                                            .withCredentials(credentialsProvider)
                                            .withRegion(region)
                                            .build();
    }

}
