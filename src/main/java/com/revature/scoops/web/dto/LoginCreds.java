package com.revature.scoops.web.dto;

public class LoginCreds {

    private String username;
    private String password;

    // JACKSON REQUIRES A NO ARG CONSTRUCTOR

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
