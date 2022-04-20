package com.company.auth;

import com.company.communication.FIleIO;

import java.util.Optional;

public class UserDao extends FIleIO<User> {

    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) instance = new UserDao();
        return instance;
    }

    private UserDao() {
        insertDefaultAdmin();
    }

    @Override
    public String getFileName() {
        return "users.db";
    }

    @Override
    public User getObject(String object) {
        Optional<User> first =
                findAll()
                        .stream()
                        .filter(user -> user.getUsername().equals(object))
                        .findFirst();

        return first.orElse(null);
    }


    public boolean userExists(String username, String password) {
        Optional<User> first = findAll()
                .stream()
                .filter(user -> user.getUsername().equals(username)
                        && user.getPassword().equals(password))
                .findFirst();

        return first.isPresent();
    }

    private void insertDefaultAdmin() {
        insert(new Admin("admin", "admin"));
    }
}

