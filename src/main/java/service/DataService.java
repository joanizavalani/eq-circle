package service;

import lombok.AllArgsConstructor;

import entities.CircleData;
import repository.DataRepository;

import java.util.UUID;

@AllArgsConstructor

public class DataService {

    // takes as parameter the repository, so the consoleUI inputs reach the repository

    private DataRepository dataRepository;

    // all the methods here are used in the consoleUI and connect to
    // the repository (which is responsible for database insertions)

    public void addAllData(CircleData data){

        dataRepository.addAllData(data);
    }

    public double getRadius(UUID coordinatesId){

        return dataRepository.getRadius(coordinatesId);
    }

    public double getArea(UUID coordinatesId){

        return dataRepository.getArea(coordinatesId);
    }

    public double getPerimeter(UUID coordinatesId){

        return dataRepository.getPerimeter(coordinatesId);
    }

    public void resizeCircle(UUID coordinatesId, double factor){

        dataRepository.resizeCircle(coordinatesId, factor);
    }

    public void deleteCircle(UUID coordinatesId){

        dataRepository.deleteCircle(coordinatesId);
    }

}
