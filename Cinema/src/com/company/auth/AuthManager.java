package com.company.auth;

public interface AuthManager {
    boolean registerClient(String clientName, String clientPassword);

    boolean registerAdmin(String username, String password);

    LoginStatus login(String username, String password);

    void logout();

    boolean hasLoggedUser();

    boolean isLoggedUserAdmin();

}
