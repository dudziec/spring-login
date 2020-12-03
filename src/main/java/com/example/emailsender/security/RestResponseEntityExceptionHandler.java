package com.example.emailsender.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// import org.springframework.web.context.request.WebRequest;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// @ControllerAdvice
// public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//     @ExceptionHandler(value = { EmailTakenException.class })
//     protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
//         String bodString = "This should be application specific";
//         return handleExceptionInternal(ex, bodString, new HttpHeaders(), HttpStatus.CONFLICT, request);
//     }
// }
