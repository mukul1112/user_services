package com.jwtexample.JwtToken.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.Random;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // Set your SMTP host
        mailSender.setPort(587); // Set your SMTP port
        mailSender.setUsername("mukulinfo9@gmail.com"); // Set your email address
        mailSender.setPassword("dgkc qapo nsut fdvh"); // Set your email password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");


        this.javaMailSender = mailSender;
    }

    public String  sendOtp(String toEmail) {
        // Generate a random 6-digit OTP
        String otp = generateOtp();

        // Send OTP via email
        sendOtpEmail(toEmail, otp);
        return otp;
    }

    private String generateOtp() {
        int otpLength = 6;
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < otpLength; i++) {
            otp.append(new Random().nextInt(10));
        }
        System.out.println("the otp is"+otp.toString());
        return otp.toString();
    }

    private void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mukulinfo9@gmail.com"); // Replace with your email
        message.setTo(toEmail);
        message.setSubject("Your OTP for Verification");
        message.setText("Your OTP is: " + otp);

        javaMailSender.send(message);
    }



}
