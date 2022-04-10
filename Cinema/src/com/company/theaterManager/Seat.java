package com.company.theaterManager;

import java.io.Serializable;

public class Seat implements Serializable {
    private int row;
    private int col;
    private boolean isTaken;

    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
        this.isTaken = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public char getSymbol(){
        if (isTaken){
            return '■';
        }
        return '□';
    }
}
