package com.maks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AwsFileUploadController {

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("name", "Max");
        return "index";
    }

    @PostMapping("/")
    public String upload(@RequestParam MultipartFile[] files, Model model) {


        return list(model);
    }

}
