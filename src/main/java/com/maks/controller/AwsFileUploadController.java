package com.maks.controller;

import com.maks.service.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class AwsFileUploadController {

    @Autowired
    private AmazonS3Service amazonS3Service;

    @GetMapping("/")
    public ModelAndView listFiles() {
        return new ModelAndView("index", "files", amazonS3Service.getAllFiles());
    }

    @PostMapping("/")
    public ModelAndView uploadFiles(@RequestParam MultipartFile[] files) {
        Arrays.stream(files)
              .forEach(amazonS3Service::storeFile);

        return listFiles();
    }

}
