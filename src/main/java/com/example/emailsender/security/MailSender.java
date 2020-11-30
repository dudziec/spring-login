package com.example.emailsender.security;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
class MailSender {
    private JavaMailSender sender;
    private MailProperties properties;

    @Autowired
    MailSender(MailProperties properties) {
        this.properties = properties;
        this.sender = this.getConfiguredJavaMailSender();
    }

    void sendConfirmationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.AUTHOR);
        message.setSubject(properties.SUBJECT);
        
        message.setText(properties.getContent() + user.getConfirmationToken());
        message.setTo(user.getEmail());

        
        //sender.send(message);
    }

    private JavaMailSender getConfiguredJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(465);
		mailSender.setUsername(properties.senderUsername);
        mailSender.setPassword(properties.senderPassword);
        
		Properties properties = mailSender.getJavaMailProperties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.debug", "true");

		return mailSender;
    }
}
