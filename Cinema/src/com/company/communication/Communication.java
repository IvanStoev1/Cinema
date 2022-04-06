package com.company.communication;

import com.company.cinema.Projection;

import java.util.Date;
import java.util.List;

public interface Communication {

    void show(String text);

    void show(Number number);

    double getDecimalInput();

    int getNumberInput();

    String getTextInput();

    void show(String[][] matrix);

    Date askForDate(Date date);

    Date askForTime(Date time);

    void printDate(Date date);

    void showTheaterOccupation(Projection projection);

    void showProjections(List<Projection> projections);

}
