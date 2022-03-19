package com.company.cinema;

import java.util.ArrayList;
import java.util.List;

public class MovieMenu {

    List<Movie> movies = new ArrayList<>();

    public void addToMenu(Movie movie){
        movies.add(movie);
    }
    public boolean doesMovieExist(String movieTitle){
        return movies
                .stream()
                .anyMatch(movie -> movie.getTitle().equals(movieTitle));
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
