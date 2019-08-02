package com.maks.service.impl;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.model.*;
import com.maks.service.AmazonCloudWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class AmazonCloudWatchServiceImpl implements AmazonCloudWatchService {

    @Value("${cloud.aws.s3.namespace}")
    private String namespace;

    @Value("${cloud.aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonCloudWatch amazonCloudWatch;

    @Override
    public GetMetricDataResult getS3BucketSizeMetric() {
        return Optional.of(bucketName)
                       .map(this::prepareDimension)
                       .map(this::prepareMetric)
                       .map(this::prepareMetricStat)
                       .map(this::prepareMetricQuery)
                       .map(this::prepareMetricRequest)
                       .map(amazonCloudWatch::getMetricData)
                       .orElseThrow(IllegalArgumentException::new);
    }

    private Dimension prepareDimension(String bucketName) {
        return new Dimension()
                .withName("BucketName")
                .withValue(bucketName);
    }

    private Metric prepareMetric(Dimension bucketDimension) {
        return new Metric()
                .withMetricName("BucketSizeBytes")
                .withNamespace(namespace)
                .withDimensions(bucketDimension);
    }

    private MetricStat prepareMetricStat(Metric metric) {
        return new MetricStat()
                .withStat("Sum")
                .withUnit(StandardUnit.Megabytes)
                .withPeriod(86400)
                .withMetric(metric);
    }

    private MetricDataQuery prepareMetricQuery(MetricStat metricStat) {
        return new MetricDataQuery()
                .withId("m17")
                .withMetricStat(metricStat)
                .withReturnData(true);
    }

    private GetMetricDataRequest prepareMetricRequest(MetricDataQuery metricQuery) {
        Date startTime = new Date();

        Calendar endTime = Calendar.getInstance();
        endTime.setTime(startTime);
        endTime.add(Calendar.MINUTE, 1);

        return new GetMetricDataRequest()
                    .withMetricDataQueries(Collections.singleton(metricQuery))
                    .withStartTime(startTime)
                    .withEndTime(endTime.getTime());
    }

}
