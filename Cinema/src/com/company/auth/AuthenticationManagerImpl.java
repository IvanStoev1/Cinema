package com.company.auth;

public class AuthenticationManagerImpl implements AuthManager {

    private final UserDatabase database;
    private User loggedUser;

    public AuthenticationManagerImpl() {
        this.database = new UserDatabase();
        this.loggedUser = null;
    }


    @Override
    public boolean registerClient(String clientName, String clientPassword) {
        if(database.userExists(clientName)) {
            return false;
        }

        Client client = new Client(clientName, clientPassword);
        database.addUser(client);
        return true;

    }

    @Override
    public boolean registerAdmin(String username, String password) {
        if(database.userExists(username)) {
            return false;
        }

        Admin admin = new Admin(username, password);
        database.addUser(admin);
        return true;
    }

    @Override
    public LoginStatus login(String username, String password) {
        User user = database.getUser(username);
        if(user != null && user.getPassword().equals(password)) {
            loggedUser = user;
            return user instanceof Client ? LoginStatus.SUCCESS_CLIENT : LoginStatus.SUCCESS_ADMIN;
        }

        return LoginStatus.LOGIN_FAILED;
    }

    @Override
    public void logout() {
        this.loggedUser = null;

    }

    @Override
    public boolean hasLoggedUser() {
        return loggedUser != null;
    }

    @Override
    public boolean isLoggedUserAdmin() {
        return hasLoggedUser() && loggedUser instanceof Admin;
    }
}
