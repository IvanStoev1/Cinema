package com.company.cinemaManagement;

import com.company.auth.AuthManager;
import com.company.auth.LoginStatus;
import com.company.auth.RegistrationCredentials;
import com.company.cinema.*;
import com.company.communication.Communication;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;

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
                case 3: addMovie();break;
                case 4: createProjection();break;
                case 5: showMovies();break;
            }
        } else {
            communication.show(getClientUserOptions());
            int userChoice = communication.getNumberInput();
            switch (userChoice) {
                case 1: authentication.logout(); break;
                case 2: buyTicket(); break;
                case 3: testDate();break;
            }
        }
    }

    private void showMovies() {
        System.out.println(movieManager.getAllMovies().get(1));
    }

    private void testDate() {
        Date projectionDate = null;
        communication.show("Enter date");
        projectionDate = communication.askForDate(projectionDate);
        communication.show("Enter time");
        projectionDate = communication.askForTime(projectionDate);
        System.out.println(projectionDate);

    }

    private void createProjection() {
        Date projectionDate = new Date();
        communication.show("Please enter movie title");
        String movieTitle = communication.getTextInput();
        Movie chosenMovie = movieManager.getMovie(movieTitle);
        communication.show("Enter date");
        projectionDate = communication.askForDate(projectionDate);
        communication.show("Enter time");
        projectionDate = communication.askForTime(projectionDate);
        movieManager.addProjection(chosenMovie,projectionDate);


    }

    private void addMovie() {
        communication.show("Please enter movie title");
        String movieTitle = communication.getTextInput();
        communication.show("Enter movie description");
        String description = communication.getTextInput();
        movieManager.addMovie(movieTitle,description);

    }
                //when buying a ticket check for index out of bounds
    private void buyTicket() {
        communication.showProjections(movieManager.getUpcomingProjections());
        communication.show("Choose projection number");
        int projectionNumber = communication.getNumberInput();
        Projection chosenProjection = movieManager.getUpcomingProjections().get(projectionNumber);
        communication.showTheaterOccupation(chosenProjection);
        communication.show("How many tickets do you want");
        int tickets = communication.getNumberInput();
        for (int i = 0; i < tickets; i++) {
            communication.show("Please enter row");
            int row = communication.getNumberInput();
            communication.show("Please enter seat number");
            int col = communication.getNumberInput();
            while (chosenProjection.getTheater().isSeatOccupied(row,col)){
                System.out.println("This seat is occupied");
                communication.show("Please enter another row");
                row = communication.getNumberInput();
                communication.show("Please enter another seat number");
                col = communication.getNumberInput();
            }
            chosenProjection.getTheater().occupySeat(row,col);

        }


    }
            //TODO to REMOVE or NOT
    private Movie choose() {
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
            communication.show("Login successful");
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
        return """
                1. Logout
                2. Create another Admin
                3. Add movie
                4. Create Projection""";
    }

}