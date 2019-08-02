package com.maks.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.maks.entity.File;
import com.maks.repository.FileRepository;
import com.maks.service.AmazonS3Service;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AmazonS3ServiceImpl implements AmazonS3Service {

    @Value("${cloud.aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3Client;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public File getFile(Long fileId) {
        return fileRepository.getOne(fileId);
    }

    @Override
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public Long getBucketSize() {
        List<S3ObjectSummary> objectSummaries = Optional.of(bucketName)
                                                        .map(amazonS3Client::listObjects)
                                                        .map(ObjectListing::getObjectSummaries)
                                                        .orElseThrow(IllegalArgumentException::new);
        return objectSummaries.stream()
                              .mapToLong(S3ObjectSummary::getSize)
                              .sum();
    }

    @Override
    public InputStream downloadFile(Long fileId) {
        return Optional.of(fileId)
                       .map(id -> amazonS3Client.getObject(bucketName, id.toString()))
                       .map(S3Object::getObjectContent)
                       .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void storeFile(MultipartFile multipartFile) {
        Optional.of(multipartFile)
                .map(File::new)
                .map(fileRepository::save)
                .ifPresent(storedFile -> putToBucket(storedFile, multipartFile));
    }

    @SneakyThrows
    private void putToBucket(File file, MultipartFile multipartFile) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(multipartFile.getBytes().length);

        amazonS3Client.putObject(new PutObjectRequest(bucketName,
                                 file.getId().toString(),
                                 new ByteArrayInputStream(multipartFile.getBytes()),
                                 meta)
                      .withCannedAcl(CannedAccessControlList.PublicRead));
    }

}
