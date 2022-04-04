package com.company.cinemaManagement;

import com.company.cinema.MovieManager;
import com.company.communication.Communication;

public class TicketPurchaseProcedure {
    MovieManager movieManager;
    Communication communication;

    public TicketPurchaseProcedure(MovieManager movieManager, Communication communication) {
        this.movieManager = movieManager;
        this.communication = communication;
    }


}
