package org.joza.service;

import lombok.AllArgsConstructor;
import org.joza.entities.CircleCoordinates;
import org.joza.repository.CoordinatesRepository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

public class CoordinatesService {

    // takes as parameter the repository, so the consoleUI inputs reach the repository
    private final CoordinatesRepository coordinatesRepository;

    // all the methods here are used in the consoleUI and connect to
    // the repository (which is responsible for database insertions)


    public void addCoordinates(CircleCoordinates coordinates){

        coordinatesRepository.addCoordinates(coordinates);
    }

    public List<CircleCoordinates> getAllCircles(){

        return coordinatesRepository.getAllCircles();
    }

    public CircleCoordinates getCoordinates(UUID id){

        return coordinatesRepository.getCoordinates(id);
    }
}
