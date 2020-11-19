package com.example.emailsender.security;

import java.io.Serializable;

class UserRegistrationDTO implements Serializable {
    private static final long serialVersionUID = 8636738044685000591L;
    private String email;
    private String password;

    public UserRegistrationDTO() {}

    public UserRegistrationDTO(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
