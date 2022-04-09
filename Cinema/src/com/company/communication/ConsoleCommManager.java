package com.company.communication;

import com.company.cinema.Projection;
import com.company.theaterManager.MovieTheater;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleCommManager implements Communication {

    private Scanner scanner;

    public ConsoleCommManager() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void show(String text) {
        System.out.println(text);
    }

    @Override
    public void show(Number number) {
        System.out.println(number);

    }

    @Override
    public double getDecimalInput() {
        double n = 0;
        boolean isNumber;
        do {
            String input = scanner.nextLine();
            try {
                n = Double.parseDouble(input);
                isNumber = true;
            } catch (NumberFormatException e) {
                if (n <= 0) {
                    isNumber = true;
                    System.out.println("Enter a positive number");
                }else {
                    isNumber = false;
                    System.out.println("Invalid input, please enter a number");
                }
            }
        }
        while (!(isNumber));
        return n;
    }


    @Override
    public int getNumberInput() {
        int n = 0;
        boolean isNumber;
        do {
            String input = scanner.nextLine();
            try {
                n = Integer.parseInt(input);
                isNumber = true;
            } catch (NumberFormatException e) {
                if (n <= 0) {
                    isNumber = true;
                    System.out.println("Enter a positive number");
                }else {
                    isNumber = false;
                    System.out.println("Invalid input, please enter a number");
                }
            }
        } while (!(isNumber));
        return n;
    }

    @Override
    public String getTextInput() {
        return scanner.nextLine();
    }

    @Override
    public void show(String[][] matrix) {

    }
// TODO FIX Validation
    @Override
    public Date askForDate(Date date) {

        System.out.print("Order date in format DD/MM/YYYY : ");
        String dateInput = scanner.nextLine();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            date = df.parse(dateInput);
        } catch (ParseException e) {
            printIllegalInputMessage();
            askForDate(date);
        }
        return date;
    }
// TODO FIX Validation
    @Override
    public Date askForTime(Date time) {
        System.out.print("Order time in format HH:MM : ");
        String timeInput = scanner.nextLine();
        try {
            time = new SimpleDateFormat("HH:mm").parse(timeInput);
        } catch (ParseException e) {
            printIllegalInputMessage();
            askForTime(time);
        }
        return time;
    }

    @Override
    public void printDate(Date date) {
        System.out.println(date.toString());
    }

    @Override
    public void showTheaterOccupation(Projection projection) {
        MovieTheater theater = projection.getTheater();
        System.out.println("\t\t\t\t\t\t\t\t------------------------------------");
        System.out.println("rows\t\t\t\t\t\t\t\t\t\t   SCREEN \n");
        for (int i = 0; i <theater.getSeats().length ; i++) {
            System.out.print((i + 1) + "\t\t");
            for (int j = 0; j < theater.getSeats()[i].length; j++) {
                if (theater.getSeats()[i][j].isTaken()) {
                    System.out.print(theater.getSeats()[i][j].getSymbol() + " | ");
                }else{
                    System.out.print(j+1 + " | ");
                }
            }
            System.out.println();
        }
        System.out.println("\n legend \n ■ - seat is taken");
    }

    @Override
    public void showProjections(List<Projection> projections) {
        for (int i = 0; i < projections.size(); i++) {
            System.out.print(i+1);
            System.out.print(projections.get(i).getMovieTitle());
            System.out.println(projections.get(i).getProjectionDate().getHours()
            + ":" +
                    projections.get(i).getProjectionDate().getMinutes());

        }
    }

    private void printIllegalInputMessage() {
        System.out.println("Incorrect date format, please try again: ");
    }

}


