package com.company.cinemaManagement;

import com.company.auth.RegistrationCredentials;
import com.company.communication.Communication;
import com.company.communication.ConsoleCommManager;


public class CinemaCommunicatorImpl {

    private  Communication communicationManager;

    public CinemaCommunicatorImpl() {
        this.communicationManager = new ConsoleCommManager();
    }

    public RegistrationCredentials getAdminRegistrationCredentials() {
        communicationManager.show("Enter username:");
        String username = communicationManager.getTextInput();
        communicationManager.show("Enter password:");
        String password = communicationManager.getTextInput();
        communicationManager.show("Enter password again:");
        String repeatPassword = communicationManager.getTextInput();

        return new RegistrationCredentials(username, password, repeatPassword);
    }
}
