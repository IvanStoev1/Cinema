package com.company.cinema;

import java.io.Serializable;

public class Seat implements Serializable {
    private final int row;
    private final int col;
    private boolean isTaken;

    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
        this.isTaken = false;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public char getSymbolForTaken() {
            return '■';

    }
}
