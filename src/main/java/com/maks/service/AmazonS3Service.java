package com.maks.service;

import com.maks.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AmazonS3Service {

    void storeFile(MultipartFile multipartFile);

    List<File> getAllFiles();

}
