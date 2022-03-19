package com.company.theaterManager;

import com.company.cinema.Projection;

public class MovieTheater {
    private Projection projection;
    private final Seat[][] seats = new Seat[12][20];

    public MovieTheater() {
    }

    public Seat[][] getSeats() {
        return seats;
    }
}
