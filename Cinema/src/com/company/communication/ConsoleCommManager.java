package com.company.communication;

import com.company.cinema.Movie;
import com.company.cinema.Projection;
import com.company.theaterManager.MovieTheater;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleCommManager implements Communication {
    static DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    .withResolverStyle(ResolverStyle.STRICT);
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

    @Override
    public Date askForDate() {
        Date date = null;
        System.out.print("Order date in format DD/MM/YY HH:mm : ");
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
        df.setLenient(false);
        try {
            String dateInput = scanner.nextLine();
            date = df.parse(dateInput);
        } catch (ParseException e) {
            printIllegalInputMessage();
            askForDate();
        }
        return date;
    }


    @Override
    public void printDate(Date date) {
        System.out.println(date.toString());
    }

    @Override
    public void showTheaterOccupation(Projection projection) {
        MovieTheater theater = projection.getTheater();
        System.out.println("\t\t\t\t\t\t\t\t" + projection.getMovieTitle() + " \n \t\t\t\t\t\t\t\t" + projection.getProjectionDate());
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
            System.out.print(i+1 + " ");
            System.out.print(projections.get(i).getMovieTitle() + "\t|\t ");
            System.out.println(projections.get(i).getProjectionDate());

        }
    }

    @Override
    public void showMovies(List<Movie> movies) {
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i+1) + " " + movies.get(i).getTitle());
        }
    }

    private void printIllegalInputMessage() {
        System.out.println("Incorrect date format, please try again: ");
    }

}


