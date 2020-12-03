package com.example.emailsender.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Email is already in use!")
public class EmailTakenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    EmailTakenException() {
        super("Email is already in use");
        System.out.println("\n\n\n\n\n\nException throwed ! \n\n\n\n\n\n");
    }
}
