package com.company.cinema;

import java.util.Date;

public class Ticket {

    private int row;
    private int seat;
    private Date movieDate;
    private Date currentDate;
    private String movieTitle;

    public Ticket(int row, int seat, Date date, Movie movie) {
        this.row = row;
        this.seat = seat;
        this.movieDate = date;
        this.movieTitle = movie.getTitle();
        this.currentDate = new Date();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "row=" + row +
                ", seat=" + seat +
                ", movieDate=" + movieDate +
                ", currentDate=" + currentDate +
                ", movieTitle='" + movieTitle + '\'' +
                '}';
    }
}
