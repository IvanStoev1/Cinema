package com.company.communication;

import com.company.cinema.Movie;
import com.company.cinema.Projection;
import com.company.cinema.Ticket;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface Communication {

    void show(String text);

    void show(Number number);

    double getDecimalInput();

    int getNumberInput();

    String getTextInput();

    void show(String[][] matrix);

    Date askForDate();

    void printDate(Date date);

    void showTheaterOccupation(Projection projection);

    void showProjections(List<Projection> projections);

    void showMovies(List<Movie> movies);

    void printTicket(Ticket ticket);

}
