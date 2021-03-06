package com.company.cinema;

import com.company.auth.User;
import com.company.communication.FIleIO;

import java.util.Optional;

public class MovieDao extends FIleIO<Movie> {

    private static MovieDao instance = new MovieDao();

    private MovieDao(){

    }

    public static MovieDao getInstance(){
        if (instance == null){
            instance = new MovieDao();
        }
        return instance;
    }

    @Override
    protected String getFileName() {
        return "movies.db";
    }

    @Override
    protected Movie getObject(String object) {
        Optional<Movie> first =
                findAll()
                        .stream()
                        .filter(movie -> movie.getTitle().equals(object))
                        .findFirst();

        return first.orElse(null);
    }

}
