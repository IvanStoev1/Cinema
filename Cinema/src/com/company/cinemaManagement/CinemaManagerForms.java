package com.company.cinemaManagement;

import com.company.communication.Communication;
import com.company.communication.ConsoleCommManager;

import java.util.Scanner;

public class CinemaManagerForms {
    private final Communication communicationManager;

    public CinemaManagerForms() {
        this.communicationManager = new ConsoleCommManager();
    }

    public String[] processForm() {
        communicationManager.show("Enter username:");
        String username = communicationManager.getTextInput();
        communicationManager.show("Enter password:");
        String password = communicationManager.getTextInput();
        communicationManager.show("Enter password again:");
        String repeatPassword = communicationManager.getTextInput();
        return new String[]{username, password, repeatPassword};
    }

    public String[] processLoginForm() {
        communicationManager.show("Enter username:");
        String username = communicationManager.getTextInput();
        communicationManager.show("Enter password:");
        String password = communicationManager.getTextInput();
        return new String[]{username, password};
    }
}

