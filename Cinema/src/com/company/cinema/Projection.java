package com.company.cinema;

import java.util.Date;

public class Projection {

    String movieName;
    Date date;
    String time;
    Ticket[] ticket;

    public Projection(String movieName, Date date, String time, Ticket[] ticket) {
        this.movieName = movieName;
        this.date = date;
        this.time = time;
        this.ticket = ticket;
    }
}
