package com.company.cinema;

import java.util.Date;

public interface MovieManager {

    void addMovie(String title, Date[] projections, String description);

     Movie getMovie(String title);


}
