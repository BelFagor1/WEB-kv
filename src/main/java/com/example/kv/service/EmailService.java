package com.example.kv.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendConfirmationEmail(String toEmail) {
        String code = String.valueOf(new Random().nextInt(999999));
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("dronkat45@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("subject");
            helper.setText(code, true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }
}
