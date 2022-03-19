package com.company.theaterManager;

public class Seat {
    private int number;
    private boolean isTaken;

    public Seat(int number, boolean isTaken) {
        this.number = number;
        this.isTaken = isTaken;
    }

    public int getNumber() {
        return number;
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
