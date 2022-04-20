package com.company.cinema;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MovieManager {

    void addMovie(String title, String description);

    Movie getMovie(String title);

    Projection getProjection(String movieTitle, List<Projection> projections, Date projectionDate);

    void addProjection(Movie movieTitle, Date date);

    List<Projection> getUpcomingProjections();

    List<Movie>getAllMovies();

    Movie getMovie(int index);

    List<Projection> getAllProjections();

    void removeMovie(int movieIndex);

    void removeProjection(int projectionIndex);

    void saveChanges(Projection projection);

}
