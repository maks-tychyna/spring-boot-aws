package com.maks.service.impl;

import com.maks.service.AmazonLambdaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AmazonLambdaServiceImpl implements AmazonLambdaService {

    @Value("${cloud.aws.bucket-size.lambda.endpoint}")
    private String bucketSizeEndpoint;

    @Value("${cloud.aws.bucket.name}")
    private String bucketName;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Long getBucketSize() {
        return restTemplate.getForObject(bucketSizeEndpoint + bucketName, Long.class);
    }

}
