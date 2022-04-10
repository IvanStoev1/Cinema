package com.company.cinema;

import com.company.communication.FIleIO;

import java.util.Optional;

public class ProjectionDao extends FIleIO<Projection> {

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
