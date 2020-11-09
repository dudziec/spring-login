package com.example.emailsender.security;

import java.net.http.HttpResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfirmationController {
    
    @GetMapping("/confirm")
    public HttpResponse confirmEmail(@PathVariable(name = "key") String confirmationKey) {
        // TODO: confirm
        return null;
    }
}
