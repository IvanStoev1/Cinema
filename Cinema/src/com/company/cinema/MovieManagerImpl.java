package com.company.cinema;

import java.util.Date;

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

}
