package com.company;

import com.company.auth.AuthenticationManagerImpl;
import com.company.cinema.DatabaseManagerImpl;
import com.company.cinemaManagement.Cinema;
import com.company.communication.ConsoleCommManager;

public class Main {

    public static void main(String[] args) {
        new Cinema(new AuthenticationManagerImpl(),
                new ConsoleCommManager(),
                new DatabaseManagerImpl())
                .initializeProgram();

    }
}
