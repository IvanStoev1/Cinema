package com.company.cinemaManagement;

import com.company.auth.AuthManager;
import com.company.auth.LoginStatus;
import com.company.auth.RegistrationCredentials;
import com.company.cinema.Movie;
import com.company.cinema.MovieManager;
import com.company.cinema.Ticket;
import com.company.communication.Communication;

public class Cinema {

    private AuthManager authentication;
    private Communication communication;
    private MovieManager movieManager;
    private CinemaManagerForms forms;

    public Cinema(AuthManager authentication,
                  Communication communication,
                  MovieManager movieManager) {
        this.authentication = authentication;
        this.communication = communication;
        this.movieManager = movieManager;
        this.forms = new CinemaManagerForms();
    }

    public void initializeProgram() {
        while (true) {
            if (authentication.hasLoggedUser()) {
                processLoggedUserOptions();
            } else {
                authenticateUser();
            }
        }
    }

    private void authenticateUser() {
        communication.show(getNonRegisterUserOptions());
        int userChoice = communication.getNumberInput();

        switch (userChoice) {
            case 1:
                initLoginProcess();
                break;
            case 2:
                initCreateClientProcess();
                break;
        }

    }

    private void processLoggedUserOptions() {
        if(authentication.isLoggedUserAdmin()) {
            communication.show(getAdminUserOptions());
            int userChoice = communication.getNumberInput();
            switch (userChoice) {
                case 1: authentication.logout(); break;
                case 2: initCreateAdminProcess(); break;
            }
        } else {
            communication.show(getClientUserOptions());
            int userChoice = communication.getNumberInput();
            switch (userChoice) {
                case 1: authentication.logout(); break;
                case 2: buyTicket(); break;
            }
        }
    }

    private void buyTicket() {

    }

    private Movie chooseMovie() {
        communication.show("Enter movie name");
        String movie = communication.getTextInput();
        return movieManager.getMovie(movie);

    }

    private void initLoginProcess() {
        String[] input = this.forms.processLoginForm();
        String username = input[0];
        String password = input[1];
        LoginStatus loginStatus = authentication.login(username, password);
        if(loginStatus == LoginStatus.LOGIN_FAILED) {
            communication.show("Login failed");
        } else {
            communication.show("Login successfull");
        }

    }
    private void initCreateAdminProcess() {
        CinemaCommunicator cm = (CinemaCommunicator) new CinemaCommunicatorImpl();
        RegistrationCredentials creds = cm.getAdminRegistrationCredentials();

        if(creds.password.equals(creds.repeatPassword)) {
            boolean registerIsSuccessful = authentication.registerAdmin(creds.username, creds.password);
            if(registerIsSuccessful) {
                communication.show("Registration successful");
            } else {
                communication.show("Such user exists.");
            }
        } else {
            communication.show("Passwords should match");
        }
    }
    private void initCreateClientProcess() {
        String[] input = this.forms.processForm();
        String username = input[0];
        String password = input[1];
        String repeatPassword = input[2];

        if(password.equals(repeatPassword)) {
            boolean registerIsSuccessful = authentication.registerClient(username, password);
            if(registerIsSuccessful) {
                communication.show("Registration successful");
            } else {
                communication.show("Such user exists.");
            }
        } else {
            communication.show("Passwords should match");
        }
    }


    private String getNonRegisterUserOptions() {
        return "1. Login\n" +
                "2. Create new Client account";
    }

    private String getClientUserOptions() {
        return "1. Logout\n" +
                "2. Buy a ticket";
    }

    private String getAdminUserOptions() {
        return "1. Logout\n" +
                "2. Create another Admin\n";
    }

}
