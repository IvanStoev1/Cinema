package com.company.communication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                }
                isNumber = false;
                System.out.println("Invalid input, please enter a number");
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
                }
                isNumber = false;
                System.out.println("Invalid input, please enter a number");
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
        System.out.print("Order date in format DD/MM/YYYY : ");
        String dateInput = scanner.nextLine();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateInput);
        } catch (ParseException e) {
            printIllegalInputMessage();
            askForDate();
        }
        return date;
    }

    @Override
    public Date askForTime() {
        Date time = null;
        System.out.print("Order time in format HH:MM : ");
        String timeInput = scanner.nextLine();
        try {
            time = new SimpleDateFormat("HH:mm").parse(timeInput);
        } catch (ParseException e) {
            printIllegalInputMessage();
            askForTime();
        }
        return time;
    }

    @Override
    public void printDate(Date date) {
        System.out.println(date.toString());
    }

    private void printIllegalInputMessage() {
        System.out.println("Incorrect date format, please try again: ");
    }

}


