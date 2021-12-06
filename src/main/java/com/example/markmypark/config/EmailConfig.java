
package com.example.markmypark.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("markmyparkmmp@gmail.com");
        mailSender.setPassword("vvogrsmdwtpprmzu");

        Properties p = mailSender.getJavaMailProperties();
        p.put("mail.transport.protocol", "smtp");
        p.put("mail.smtp.auth", "true");
        p.put("mail.debug", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return mailSender;
    }

    //@Bean
    public void defMailSender(String userEmail, String promo) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setFrom("markmyparkmmp@gmail.com");
        message.setSubject("You have got a promocode from MMP");
        message.setText("Thanks for booking with us frequently.\nHere is your promocode for future bookings: ".concat(promo));
        getJavaMailSender().send(message);
    }
}