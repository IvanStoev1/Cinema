package com.company.cinema;

import java.util.Date;

public class Ticket {

    private int row;
    private int seat;
    private Date movieDate;
    private Date currentDate;
    private Movie movieTitle;

    public Ticket(int row, int seat, Date date, Movie movieTitle) {
        this.row = row;
        this.seat = seat;
        this.movieDate = date;
        this.movieTitle = movieTitle;
        this.currentDate = new Date();
    }
}
