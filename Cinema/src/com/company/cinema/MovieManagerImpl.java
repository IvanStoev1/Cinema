package com.company.cinema;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MovieManagerImpl implements MovieManager {

    private final MovieDao movies;
    private final ProjectionDao projections;


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

    @Override
    public void addProjection(Movie movieTitle, Date date) {
        Projection projection = new Projection(movieTitle, date);
        projections.insert(projection);
    }

    public List<Projection> getUpcomingProjections() {
        Date date = new Date();
        Date endDate = new Date();
        endDate.setHours(23);
        endDate.setMinutes(59);
        return projections
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
        if (projectionIndex(projection, pr) != -1) {
            pr.remove(projectionIndex(projection, pr));
        }
        pr.add(projection);
        projections.overwrite(pr);

    }

    private int projectionIndex(Projection projection, List<Projection> pr) {
        for (int i = 0; i < pr.size(); i++) {
            if (pr.get(i).getMovieTitle().equals(projection.getMovieTitle()) &&
                    pr.get(i).getProjectionDate().equals(projection.getProjectionDate())) {
                return i;
            }
        }
        return -1;
    }

    private void autoRemovePastProjections() {
        Date now = new Date();
        List<Projection> pastProjections = getAllProjections();
        pastProjections
                .stream()
                .filter(projection -> projection.getProjectionDate().after(now))
                .collect(Collectors.toList());
        projections.overwrite(pastProjections);
    }

}
