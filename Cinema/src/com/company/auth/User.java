package com.company.auth;

public class User {

    private static int userCount = 0;
    private long id;
    private String username;
    private String password;

    public User(String username, String password) {
        userCount++;
        this.username = username;
        this.password = password;
        this.id = userCount;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
