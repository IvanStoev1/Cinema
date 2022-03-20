package com.company.cinema;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MovieManager {

    void addMovie(String title, String description);

    Movie getMovie(String title);

    void addProjection(String movieTitle, Date date);

    Map<String,Date> getUpcomingMovies(Date endDate);
}
