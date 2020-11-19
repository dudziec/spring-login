package com.example.emailsender.security;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private MailSender sender;
    private PasswordEncoder passwordEncoder;
    private UserRepository repository;
	
    @Autowired
    UserService(MailSender sender, PasswordEncoder passwordEncoder, UserRepository repository) {
        this.sender = sender;
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    // TODO: Transactional
    User registerUser(UserRegistrationDTO userRegistrationDTO) {
        Optional<User> existingUser = repository.findByEmail(userRegistrationDTO.getEmail());
        if(existingUser.isPresent()) {
            logger.info("User with email: {} is already in database.", userRegistrationDTO.getEmail());
            // throw exception
        }

        User newUser = new User(userRegistrationDTO.getEmail(), passwordEncoder.encode(userRegistrationDTO.getPassword()));

        repository.save(newUser);
        sendConfirmationEmail(newUser);

        // TODO return user
        return null;
    }

    void sendConfirmationEmail(User user) {
        if(!user.isEnabled()) {
            sender.sendConfirmationEmail(user);
        }
        // TODO: Throw an exception that user has been already enabled
    }

    void enableUser(String confirmationToken) {
        Optional<User> optionalUser = repository.findByConfirmationToken(confirmationToken);
        if(optionalUser.isPresent()) {
            // TODO
        }
    }
}
