package com.company.cinema;

import java.util.Collections;
import java.util.Comparator;
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
        List<Movie> movies = getAllMovies();
        movies.remove(index);
        this.movies.overwrite(movies);
    }

    @Override
    public void removeProjection(int projectionIndex) {
        List<Projection> projections = getAllProjections();
        projections.remove(projectionIndex);
        this.projections.overwrite(projections);
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

    public List<Projection> getUpcomingProjections() {  //TODO  throw exception null or size 0
        Date date = new Date();
        Date endDate = new Date();
        endDate.setHours(23);
        endDate.setMinutes(59);
        List<Projection> upcomingProjections = projections
                .findAll()
                .stream()
                .filter(projection -> projection.getProjectionDate().after(date)
                        && projection.getProjectionDate().before(endDate))
                .sorted(new Comparator<Projection>() {
                    @Override
                    public int compare(Projection o1, Projection o2) {
                        return o1.getProjectionDate().compareTo(o2.getProjectionDate());
                    }
                }).collect(Collectors.toList());
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

    @Override
    public void saveChanges(Projection projection) {
        List<Projection> pr = getAllProjections();
        pr.remove(projectionIndex(projection,pr));
        pr.add(projection);
        projections.overwrite(pr);

    }

    private int projectionIndex(Projection projection, List<Projection> pr) {
        for (int i = 0; i < pr.size(); i++) {
            if(pr.get(i).getMovieTitle().equals(projection.getMovieTitle()) &&
                    pr.get(i).getProjectionDate().equals(projection.getProjectionDate())){
                return i;
            }
        }
        return -1;
    }
}
