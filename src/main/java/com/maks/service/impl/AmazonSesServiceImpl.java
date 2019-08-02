package com.maks.service.impl;

import com.maks.service.AmazonSesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AmazonSesServiceImpl implements AmazonSesService {

    @Value("${cloud.aws.mail.recepient}")
    private String mailRecepient;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String messageText) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mailRecepient);
        message.setSubject("AWS SES message");
        message.setText(messageText);

        mailSender.send(message);
    }

}
