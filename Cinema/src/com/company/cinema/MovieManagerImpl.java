package com.company.cinema;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MovieManagerImpl implements MovieManager {

    private MovieDao movies;
    private ProjectionDao projections;


    public MovieManagerImpl() {
        this.movies = new MovieDao();
        this.projections = new ProjectionDao();
    }

    @Override
    public void addMovie(String title, String description) {
        Movie movie = new Movie(title, description);
        movies.insert(movie);

    }

    public void removeMovie(int index) {
        //add method in FileIO or in MovieDao
    }

    //TODO EXCEPTION NEEDED
    @Override
    public Movie getMovie(String title) {
        if (movies.getObject(title) != null) {
            return movies.
                    findAll()
                    .stream()
                    .filter(movie -> movie.getTitle().equals(title))
                    .findFirst()
                    .get();
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
        projections.insert(projection);
    }

    public List<Projection> getUpcomingProjections() {  //TODO returns size 0 / throw exception null or size 0
        Date date = new Date();
        Date endDate = date;
        endDate.setHours(23);
        endDate.setMinutes(59);
        List<Projection> upcomingProjections = projections
                .findAll()
                .stream()
                .filter(projection -> projection.getProjectionDate().after(date)
                        && projection.getProjectionDate().before(endDate))
                .collect(Collectors.toList());
        return upcomingProjections;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movies.findAll();
    }

    @Override
    public Movie getMovie(int index) {
        return movies.findAll().get(index);
    }

    @Override
    public List<Projection> getAllProjections() {
        return projections.findAll();
    }


}
