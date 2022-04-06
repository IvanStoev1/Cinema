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

    public long getTheaterCapacity() {
        return Arrays.stream(seats).count();
    }

    public void occupySeat(int row, int col) {

        seats[row][col].setTaken(true);
    }

    public boolean isSeatOccupied(int row, int col) {

        return seats[row][col].isTaken();
    }
}