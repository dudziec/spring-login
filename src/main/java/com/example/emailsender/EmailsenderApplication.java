package com.example.emailsender;

import java.util.Properties;

import com.example.emailsender.security.User;
import com.example.emailsender.security.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private PasswordEncoder passwordEncoder;
	@Autowired
	private JavaMailSender sender;
	@Autowired
	private UserRepository repository;
	@Autowired
	private YamlConfig config;

	private static final Logger logger = LoggerFactory.getLogger(EmailsenderApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmailsenderApplication.class, args);
		logger.info("Example log from {}", EmailsenderApplication.class.getSimpleName());
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User("damian", passwordEncoder.encode("damian123"));
		logger.info("Created user: {}", user.getUsername());		
		repository.save(user);
		logger.info("User {} saved into database.", user.getUsername());
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
