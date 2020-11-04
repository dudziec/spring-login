package com.example.emailsender;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class EmailsenderApplication implements CommandLineRunner {

	@Autowired
	JavaMailSender sender;

	public static void main(String[] args) {
		SpringApplication.run(EmailsenderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("dudziecdamian@gmail.com");
        email.setSubject("Subj");
		email.setText("message");
		
        sender.send(email);
	}

	@Bean
	JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(465);

		mailSender.setUsername("kwokdamian123@gmail.com");
		mailSender.setPassword("");
		Properties properties = mailSender.getJavaMailProperties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.debug", "true");

		return mailSender;
	}

}
