package com.maks.service;

import com.amazonaws.services.cloudwatch.model.GetMetricDataResult;

public interface AmazonCloudWatchService {

    GetMetricDataResult getS3BucketSizeMetric();

}
