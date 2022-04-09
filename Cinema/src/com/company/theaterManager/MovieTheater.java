package com.company.theaterManager;

import com.company.cinema.Projection;

import java.util.Arrays;

public class MovieTheater {
    private Projection projection;
    private Seat[][] seats;

    public MovieTheater() {
        initialiseSeats();
    }

    private Seat[][] initialiseSeats(){
        seats = new Seat[12][20];
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = new Seat(i,j);
            }
        }
        return seats;
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