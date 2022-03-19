package com.company;

import com.company.auth.AuthenticationManagerImpl;
import com.company.cinema.MovieManagerImpl;
import com.company.cinemaManagement.Cinema;
import com.company.cinemaManagement.CinemaCommunicator;
import com.company.cinemaManagement.CinemaCommunicatorImpl;
import com.company.communication.ConsoleCommManager;

public class Main {

    //This is main method
    public static void main(String[] args) {
        new Cinema(new AuthenticationManagerImpl(),
                new ConsoleCommManager(),
                new MovieManagerImpl())
                .initializeProgram();
    }

}
