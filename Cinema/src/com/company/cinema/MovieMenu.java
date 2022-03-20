package com.company.cinema;

import java.util.ArrayList;
import java.util.List;

public class MovieMenu {

    private List<Movie> movies = new ArrayList<>();
    private List<Projection> projections = new ArrayList<>();

    public void addMovieToMenu(Movie movie){
        movies.add(movie);
    }

    public void addProjectionToMenu(Projection projection){
        projections.add(projection);
    }

    public boolean doesMovieExist(String movieTitle){
        return movies
                .stream()
                .anyMatch(movie -> movie.getTitle().equals(movieTitle));
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Projection> getProjections() {
        return projections;
    }
}
