package com.company.cinema;

import java.util.Date;
import java.util.List;

public interface DatabaseManager {

    void addMovie(String title, String description);

    void addProjection(Movie movieTitle, Date date);

    List<Projection> getUpcomingProjections();

    List<Movie> getAllMovies();

    Movie getMovie(int index);

    List<Projection> getAllProjections();

    void removeMovie(int movieIndex);

    void removeProjection(int projectionIndex);

    void saveChanges(Projection projection);

    void autoRemovePastProjections();

}
