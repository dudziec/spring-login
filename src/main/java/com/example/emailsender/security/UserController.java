package com.example.emailsender.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/reg")
    public ResponseEntity<UserRegistrationDTO> register(@RequestBody UserRegistrationDTO user) {
        logger.info("Registering user with email: {} and password: {}", user.getEmail(), user.getPassword());
        
        try {
            service.registerUser(user);
            return new ResponseEntity<UserRegistrationDTO>(HttpStatus.OK);
        } catch (EmailTakenException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail is already in use!", ex);
        }
    }

    @GetMapping("/confirm")
    public String confirmEmail(@RequestParam(name = "key") String confirmationToken) {
        logger.info("Received confirmation key: " + confirmationToken);

        service.enableUser(confirmationToken);
        // TODO: Return valid response object
        return "Account confirmed";
    }

    // @ExceptionHandler({EmailTakenException.class})
    // public ResponseEntity handleException() {
    //     return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use!");
    // }
}
