package com.company.cinemaManagement;

import com.company.auth.AuthManager;
import com.company.auth.LoginStatus;
import com.company.auth.RegistrationCredentials;
import com.company.cinema.*;
import com.company.communication.Communication;

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
        if (authentication.isLoggedUserAdmin()) {
            communication.show(getAdminUserOptions());
            int userChoice = communication.getNumberInput();
            switch (userChoice) {
                case 1:
                    authentication.logout();
                    break;
                case 2:
                    initCreateAdminProcess();
                    break;
                case 3:
                    addMovie();
                    break;
                case 4:
                    removeMovie();
                    break;
                case 5:
                    createProjection();
                    break;
                case 6:
                    removeProjection();
                    break;
            }
        } else {
            communication.show(getClientUserOptions());
            int userChoice = communication.getNumberInput();
            switch (userChoice) {
                case 1:
                    authentication.logout();
                    break;
                case 2:
                    buyTicket();
                    break;
            }
        }
    }

    private void removeProjection() {
        communication.showProjections(movieManager.getAllProjections());
        int allProjectionsLength = movieManager.getAllProjections().size()-1;
        int projectionIndex;
        communication.show("Please enter projection index");
        do {
            projectionIndex = communication.getNumberInput() - 1;
            if (projectionIndex < 0 || projectionIndex > allProjectionsLength){
                communication.show("Please enter a valid movie index");
            }
        } while (projectionIndex < 0 || projectionIndex > allProjectionsLength);
        movieManager.removeProjection(projectionIndex);
        communication.show("The projection was removed successfully");
    }

    private void removeMovie() {
        communication.showMovies(movieManager.getAllMovies());
        int allMoviesLength = movieManager.getAllMovies().size()-1;
        int movieIndex;
        communication.show("Please enter movie index");
        do {
            movieIndex = communication.getNumberInput() - 1;
            if (movieIndex < 0 || movieIndex > allMoviesLength){
                communication.show("Please enter a valid movie index");
            }
        } while (movieIndex < 0 || movieIndex > allMoviesLength);
        movieManager.removeMovie(movieIndex);
        communication.show("The movie was removed successfully");
    }

    private void createProjection() {
        Date projectionDate;
        communication.showMovies(movieManager.getAllMovies());
        communication.show("Please enter movie index");
        int movieIndex = communication.getNumberInput() - 1;
        Movie chosenMovie = movieManager.getMovie(movieIndex);
        communication.show("Enter date");
        projectionDate = communication.askForDate();
        movieManager.addProjection(chosenMovie, projectionDate);
        System.out.println(chosenMovie + " \n" + projectionDate);

    }

    private void addMovie() {
        communication.show("Please enter movie title");
        String movieTitle = communication.getTextInput();
        communication.show("Enter movie description");
        String description = communication.getTextInput();
        movieManager.addMovie(movieTitle, description);

    }

    //when buying a ticket check for index out of bounds
    private void buyTicket() {
        communication.showProjections(movieManager.getUpcomingProjections());
        communication.show("Choose projection number");
        int projectionNumber = communication.getNumberInput() - 1;
        Projection chosenProjection = movieManager.getUpcomingProjections().get(projectionNumber);
        communication.showTheaterOccupation(chosenProjection);
        communication.show("How many tickets do you want");
        int tickets = communication.getNumberInput();
        for (int i = 0; i < tickets; i++) {
            communication.show("Please enter row");
            int row = communication.getNumberInput() - 1;
            communication.show("Please enter seat number");
            int col = communication.getNumberInput() - 1;
            while (chosenProjection.getTheater().isSeatOccupied(row, col)) {
                System.out.println("This seat is occupied");
                communication.show("Please enter another row");
                row = communication.getNumberInput() - 1;
                communication.show("Please enter another seat number");
                col = communication.getNumberInput() - 1;
            }
            chosenProjection.getTheater().occupySeat(row, col);
            communication.showTheaterOccupation(chosenProjection);
            movieManager.saveChanges(chosenProjection);
        }
    }

    private void initLoginProcess() {
        String[] input = this.forms.processLoginForm();
        String username = input[0];
        String password = input[1];
        LoginStatus loginStatus = authentication.login(username, password);
        if (loginStatus == LoginStatus.LOGIN_FAILED) {
            communication.show("Login failed");
        } else {
            communication.show("Login successful");
        }

    }

    private void initCreateAdminProcess() {
        CinemaCommunicator cm = (CinemaCommunicator) new CinemaCommunicatorImpl();
        RegistrationCredentials creds = cm.getAdminRegistrationCredentials();

        if (creds.password.equals(creds.repeatPassword)) {
            boolean registerIsSuccessful = authentication.registerAdmin(creds.username, creds.password);
            if (registerIsSuccessful) {
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

        if (password.equals(repeatPassword)) {
            boolean registerIsSuccessful = authentication.registerClient(username, password);
            if (registerIsSuccessful) {
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
                4. Remove movie
                5. Create projection
                6. Remove projection""";
    }

}