package org.joza.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.joza.entities.CircleCoordinates;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

public class CoordinatesRepository {

    private final Session session;

    public void addCoordinates(CircleCoordinates coordinates){

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(coordinates);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // method connects to showAllCircles() in consoleUI
    public List<CircleCoordinates> getAllCircles(){

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String query = "select c from CircleCoordinates c";
            Query<CircleCoordinates> allCircles = session.createQuery(query, CircleCoordinates.class);
            List<CircleCoordinates> result = allCircles.getResultList();
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    // method to get coordinates
    public CircleCoordinates getCoordinates(UUID id) {

        Transaction transaction = null;

        CircleCoordinates coordinates = session.get(CircleCoordinates.class, id);

        if (coordinates.getId() != null) {

            try {
                transaction = session.beginTransaction();
                session.persist(coordinates);
                transaction.commit();

            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }


        } else {
            throw new RuntimeException("Coordinates not found");
        }


        return coordinates;
    }
}





