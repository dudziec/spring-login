package com.example.emailsender;

import java.util.Properties;

import com.example.emailsender.security.User;
import com.example.emailsender.security.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


// TODO: Add profiles for development and production
//
@SpringBootApplication
public class EmailsenderApplication implements CommandLineRunner {
    @Autowired
    PasswordEncoder passwordEncoder;

	@Autowired
	JavaMailSender sender;
	@Autowired
	UserRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(EmailsenderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User("damian", passwordEncoder.encode("damian123"));
		
		repository.save(user);
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
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
