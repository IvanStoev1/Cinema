package com.company.auth;

import java.io.Serializable;

public class User implements Serializable {

    private static int userCount = 0;
    private final long id;
    private final String username;
    private final String password;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
