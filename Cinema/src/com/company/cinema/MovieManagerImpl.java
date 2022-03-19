package com.company.cinema;

import java.util.Date;
import java.util.stream.Collectors;

public class MovieManagerImpl implements MovieManager{

    private MovieMenu menu;

    public MovieManagerImpl() {
        this.menu = new MovieMenu();
    }

    @Override
    public void addMovie(String title, Date[] projections, String description) {
        Movie movie = new Movie(title,projections,description);
        menu.addToMenu(movie);

    }
//TODO EXCEPTION NEEDED
    @Override
    public Movie getMovie(String title) {
        if(menu.doesMovieExist(title)) {
            return menu.getMovies()
                    .stream()
                    .filter(movie -> movie.getTitle().equals(title))
                    .findFirst().get();

        }
        return null;

    }

}
