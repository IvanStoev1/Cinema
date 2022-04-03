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
    public void addProjection(String movieTitle, Date date) {
        Projection projection = new Projection(movieTitle, date);
        menu.addProjectionToMenu(projection);
    }

    //should I just leave getUpcomingProjections and make endDate local
    @Override
    public Map<String, Date> getUpcomingMovies(Date endDate) {
        List<Projection> upcomingProjections = getUpcomingProjections(endDate);
        Map<String, Date> upcomingMovies = upcomingProjections
                .stream()
                .collect(Collectors
                        .toMap(Projection::getMovieTitle, Projection::getProjectionDate));
        return upcomingMovies;
    }

    private List<Projection> getUpcomingProjections(Date endDate) {  //TODO throw exception null
        Date date = new Date();
        List<Projection> upcomingProjections = menu.getProjections()
                .stream()
                .filter(projection -> projection.getProjectionDate().after(date)
                        && projection.getProjectionDate().before(endDate))
                .collect(Collectors.toList());
        return upcomingProjections;
    }
}
