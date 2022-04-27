package com.company.cinemaManagement;

import com.company.auth.AuthManager;
import com.company.auth.LoginStatus;
import com.company.auth.RegistrationCredentials;
import com.company.cinema.*;
import com.company.communication.Communication;

import java.util.Date;

public class Cinema {

    private final AuthManager authentication;
    private final Communication communication;
    private final DatabaseManager databaseManager;
    private final CinemaManagerForms forms;

    public Cinema(AuthManager authentication,
                  Communication communication,
                  DatabaseManager databaseManager) {
        this.authentication = authentication;
        this.communication = communication;
        this.databaseManager = databaseManager;
        this.forms = new CinemaManagerForms();
    }

    public void initializeProgram() {
        while (true) {
            databaseManager.autoRemovePastProjections();
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
            default:communication.show("No such option");
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
                case 7:
                    showAllProjections();
                default:communication.show("No such option");
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

    private void showAllProjections() {
        int projectionsLength = databaseManager.getAllProjections().size();
        if (projectionsLength > 0) {
            communication.showProjections(databaseManager.getAllProjections());
        } else {
            communication.show("There are no projections");
        }
    }

    private void removeProjection() {
        communication.showProjections(databaseManager.getAllProjections());
        int allProjectionsLength = databaseManager.getAllProjections().size() - 1;
        int projectionIndex;
        communication.show("Please enter projection index");
        do {
            projectionIndex = communication.getNumberInput() - 1;
            if (projectionIndex < 0 || projectionIndex > allProjectionsLength) {
                communication.show("Please enter a valid movie index");
            }
        } while (projectionIndex < 0 || projectionIndex > allProjectionsLength);
        databaseManager.removeProjection(projectionIndex);
        communication.show("The projection was removed successfully");
    }

    private void removeMovie() {
        communication.showMovies(databaseManager.getAllMovies());
        int allMoviesLength = databaseManager.getAllMovies().size() - 1;
        int movieIndex;
        communication.show("Please enter movie index");
        do {
            movieIndex = communication.getNumberInput() - 1;
            if (movieIndex < 0 || movieIndex > allMoviesLength) {
                communication.show("Please enter a valid movie index");
            }
        } while (movieIndex < 0 || movieIndex > allMoviesLength);
        databaseManager.removeMovie(movieIndex);
        communication.show("The movie was removed successfully");
    }

    private void createProjection() {
        int movieLength = databaseManager.getAllMovies().size();
        if (movieLength > 0) {
            Date projectionDate;
            communication.showMovies(databaseManager.getAllMovies());
            communication.show("Please enter movie index");
            int movieIndex = communication.getNumberInput() - 1;
            Movie chosenMovie = databaseManager.getMovie(movieIndex);
            communication.show("Enter date");
            projectionDate = communication.askForDate();
            databaseManager.addProjection(chosenMovie, projectionDate);
            System.out.println(chosenMovie + " \n" + projectionDate);
        } else {
            communication.show("There are no movies. Please first enter a movie. ");
        }


    }

    private void addMovie() {
        communication.show("Please enter movie title");
        String movieTitle = communication.getTextInput();
        communication.show("Enter movie description");
        String description = communication.getTextInput();
        databaseManager.addMovie(movieTitle, description);

    }

    private void buyTicket() {
        TicketPurchaseProcedure ticketPurchaseProcedure = new TicketPurchaseProcedure(databaseManager, communication);
        ticketPurchaseProcedure.initializePurchase();
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
                6. Remove projection
                7. Show all projections""";
    }

}