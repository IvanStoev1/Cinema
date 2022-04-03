package com.company.cinema;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MovieManagerImpl implements MovieManager {

    private MovieMenu menu;


    public MovieManagerImpl() {
        this.menu = new MovieMenu();
    }

    @Override
    public void addMovie(String title, String description) {
        Movie movie = new Movie(title, description);
        menu.addMovieToMenu(movie);

    }

    //TODO EXCEPTION NEEDED
    @Override
    public Movie getMovie(String title) {
        if (menu.doesMovieExist(title)) {
            return menu.getMovies()
                    .stream()
                    .filter(movie -> movie.getTitle().equals(title))
                    .findFirst().get();

        }
        return null;

    }
        //TODO throw exception null
    @Override
    public Projection getProjection(String movieTitle, List<Projection> projections, Date projectionDate) {
        Projection selectedProjection;
        for (Projection projection : projections) {
            if (projection.getMovieTitle().equals(movieTitle) && projection.getProjectionDate() == projectionDate) {
                return selectedProjection = projection;
            }
        }
        return null;
    }

    @Override
    public void addProjection(Movie movieTitle, Date date) {
        Projection projection = new Projection(movieTitle, date);
        menu.addProjectionToMenu(projection);
    }

    public List<Projection> getUpcomingProjections() {  //TODO throw exception null
        Date date = new Date();
        Date endDate = date;
        endDate.setHours(23);
        endDate.setMinutes(59);
        return menu.getProjections()
                .stream()
                .filter(projection -> projection.getProjectionDate().after(date)
                        && projection.getProjectionDate().before(endDate))
                .collect(Collectors.toList());
    }
}
