package com.company.cinema;

import java.io.Serializable;
import java.util.Date;
            // should the ticket have a price
public class Ticket implements Serializable {

    private final int row;
    private final int seat;
    private final Date projectionDate;
    private final Date currentDate;
    private final String movieTitle;

    public Ticket(int row, int seat, Date date, String movieTitle) {
        this.row = row + 1;
        this.seat = seat + 1;
        this.projectionDate = date;
        this.movieTitle = movieTitle;
        this.currentDate = new Date();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                " Row " + row +
                ", Seat " + seat +
                ", Movie date " + projectionDate +
                ", Purchase date " + currentDate +
                ", Title '" + movieTitle + '\'' +
                '}';
    }
}
