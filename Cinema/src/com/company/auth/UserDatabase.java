package com.company.auth;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private List<Client> allClients;
    private List<Admin> allAdmins;

    public UserDatabase() {
        this.allClients = new ArrayList<>();
        this.allAdmins = new ArrayList<>();
        insertDefaultAdmin();
    }

    private void insertDefaultAdmin() {
        addUser(new Admin("admin", "admin"));
    }

    public void addUser(Client user) {
        allClients.add(user);
    }

    public void addUser(Admin user) {
        allAdmins.add(user);

    }

    public boolean userExists(String username) {
        return userExistsAsClient(username) || userExistsAsAdmin(username);
    }

    private boolean userExistsAsAdmin(String username) {
        return getAdmin(username) != null;
    }

    private boolean userExistsAsClient(String username) {
        return getClient(username) != null;
    }

    User getUser(String username) {
        User user = getClient(username);
        if (user == null) {
            user = getAdmin(username);
        }

        return user;
    }

    private User getClient(String username) {
        for (Client client : allClients) {
            if (client.getUsername().equals(username)) {
                return client;
            }
        }
        return null;
    }

    private User getAdmin(String username) {
        for (Admin admin : allAdmins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }

}
