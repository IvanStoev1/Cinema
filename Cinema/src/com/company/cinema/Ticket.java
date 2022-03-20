package com.company.cinema;

import java.util.Date;
            // should the ticket have a price
public class Ticket {

    private int row;
    private int seat;
    private Date projectionDate;
    private Date currentDate;
    private String movieTitle;

    public Ticket(int row, int seat, Date date, String movie) {
        this.row = row;
        this.seat = seat;
        this.projectionDate = date;
        this.movieTitle = movie;
        this.currentDate = new Date();
    }

    @Override
    public String toString() {  //TODO format
        return "Ticket{" +
                "row=" + row +
                ", seat=" + seat +
                ", movieDate=" + projectionDate +
                ", currentDate=" + currentDate +
                ", movieTitle='" + movieTitle + '\'' +
                '}';
    }
}
