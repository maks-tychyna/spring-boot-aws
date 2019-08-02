package com.maks.service;

import com.maks.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface AmazonS3Service {

    Long getBucketSize();

    InputStream downloadFile(Long fileId);

    void storeFile(MultipartFile multipartFile);

    File getFile(Long fileId);

    List<File> getAllFiles();

}
