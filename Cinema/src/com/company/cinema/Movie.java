package com.company.cinema;

import java.util.Date;

public class Movie {

    private String title;
    private Date[] projections;
    private String description;

    public Movie(String title, Date[] projections, String description) {
        this.title = title;
        this.projections = projections;
        this.description = description;
    }

}
