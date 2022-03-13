package com.company.communication;

import java.util.Date;

public interface Communication {

    void show(String text);

    void show(Number number);

    double getDecimalInput();

    int getNumberInput();

    String getTextInput();

    void show(String[][] matrix);

    Date askForDate();

    Date askForTime();
}
