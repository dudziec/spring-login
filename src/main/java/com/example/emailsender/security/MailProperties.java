package com.example.emailsender.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class MailProperties {
    @Value("${mailsender.user.username}")
    String senderUsername;
    @Value("${mailsender.user.password}")
    String senderPassword;
    @Value("${mailsender.url}")
    String url;
    @Value("${mailsender.host.name}")
    String host;
    @Value("${mailsender.host.port}")
    int port;

    final String SUBJECT = "Confirm your email";
    final String AUTHOR = "Application";   

    String getContent() {
        return "Your confirmation email: " + this.url;
    }
}
