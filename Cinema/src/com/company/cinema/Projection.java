package com.company.cinema;

import com.company.theaterManager.MovieTheater;

import java.util.Date;

public class Projection {

    private String movieTitle;
    private Date projectionDate;
    private Ticket[] ticket;
    private MovieTheater theater;

    public Projection(Movie movie, Date date, Ticket[] ticket) {
        this.movieTitle = movie.getTitle();
        this.projectionDate = date;
        this.ticket = ticket;
        this.theater = new MovieTheater();
    }
}
