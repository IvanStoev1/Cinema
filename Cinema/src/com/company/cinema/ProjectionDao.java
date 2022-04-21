package com.company.cinema;

import com.company.communication.FIleIO;

import java.util.Optional;

public class ProjectionDao extends FIleIO<Projection> {

    private static ProjectionDao instance;

    private ProjectionDao(){

    }

    public static ProjectionDao getInstance(){
        if (instance == null){
            instance = new ProjectionDao();
        }
        return instance;
    }

    @Override
    protected String getFileName() {
        return "projections.db";
    }

    @Override
    protected Projection getObject(String object) {
        Optional<Projection> first =
                findAll()
                        .stream()
                        .filter(projection -> projection.getMovieTitle().equals(object))
                        .findFirst();

        return first.get();
    }
}
