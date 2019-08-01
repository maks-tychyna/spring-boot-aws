package com.maks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AwsFileUploadController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Test";
    }

}
