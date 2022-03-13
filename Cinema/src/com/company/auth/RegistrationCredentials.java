package com.company.auth;

public class RegistrationCredentials {
    public String username;
    public String password;
    public String repeatPassword;

    public RegistrationCredentials(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

}
