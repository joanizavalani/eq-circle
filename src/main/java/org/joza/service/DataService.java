package org.joza.service;

import lombok.AllArgsConstructor;
import org.joza.entities.CircleCoordinates;
import org.joza.entities.CircleData;
import org.joza.repository.DataRepository;

@AllArgsConstructor

public class DataService {

    // takes as parameter the repository, so the consoleUI inputs reach the repository

    private DataRepository dataRepository;

    // all the methods here are used in the consoleUI and connect to
    // the repository (which is responsible for database insertions)

    public void addAllData(CircleData data){

        dataRepository.addAllData(data);

    }
}
