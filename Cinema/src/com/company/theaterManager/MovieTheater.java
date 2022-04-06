package com.company.theaterManager;

import com.company.cinema.Projection;

import java.util.Arrays;

public class MovieTheater {
    private Projection projection;
    private Seat[][] seats;

    public MovieTheater() {
        seats = new Seat[12][20];
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public long getTheaterCapacity(){
       return Arrays.stream(seats).count();
    }
}
