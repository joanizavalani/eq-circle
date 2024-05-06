package org.joza;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joza.configuration.HibernateUtility;
import org.joza.repository.CoordinatesRepository;
import org.joza.service.CoordinatesService;
import org.joza.service.DataService;

public class Main {
    public static void main(String[] args) {

        // simple circle calculator app: stores data (coordinates, radius) in a mySQL database
        // user will be able to get radius, area and perimeter of circle through a consoleUI.
        // other functionalities: move a circle, resize radius, etc.
        // frameworks: Hibernate

        Session session = HibernateUtility.getSessionFactory().openSession();

        CoordinatesRepository coordinatesRepository = new CoordinatesRepository(session);

        CoordinatesService coordinatesService = new CoordinatesService(coordinatesRepository);
        DataService dataService = new DataService();

        ConsoleUI consoleUI = new ConsoleUI(coordinatesService, dataService);
        consoleUI.runMenu();

        session.close();
    }


}