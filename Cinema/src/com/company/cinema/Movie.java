package com.company.cinema;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Movie implements Serializable {

    private String title;
    private String description;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
