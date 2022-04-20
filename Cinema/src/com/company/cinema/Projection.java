package com.company.cinema;

import com.company.theaterManager.MovieTheater;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Projection implements Serializable {

    private final String movieTitle;
    private final Date projectionDate;
    private final Ticket[] tickets;
    private final MovieTheater theater;


    private int ticketCount;

    public Projection(Movie movieTitle, Date date) {
        this.movieTitle = movieTitle.getTitle();
        this.projectionDate = date;
        this.theater = new MovieTheater();
        this.tickets = new Ticket[(int) theater.getTheaterCapacity()];
        this.ticketCount = 0;
    }

    public void addTicket(int row, int seat) {
        Ticket ticket = new Ticket(row, seat, projectionDate, movieTitle);
        tickets[ticketCount] = ticket;
        ticketCount++;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public Date getProjectionDate() {
        return projectionDate;
    }

    public MovieTheater getTheater() {
        return theater;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "Projection{" +
                "movieTitle='" + movieTitle + '\'' +
                ", projectionDate=" + projectionDate +
                ", tickets=" + Arrays.toString(tickets) +
                ", theater=" + theater +
                ", ticketCount=" + ticketCount +
                '}';
    }
}
