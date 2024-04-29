package org.joza.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.joza.entities.CircleCoordinates;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class CoordinatesRepository {

    private final Session session;
    private Transaction transaction;

    // method connects to createCircle() in consoleUI
    public void addCoordinates(CircleCoordinates coordinates){

        try {

            UUID id = coordinates.getId();
            double centerX = coordinates.getCenterX();
            double centerY = coordinates.getCenterY();
            double pointX = coordinates.getPointX();
            double pointY = coordinates.getPointY();

            session.save(coordinates);
            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) { transaction.rollback(); }
            e.printStackTrace();

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    // method connects to showAllCircles() in consoleUI
    public List<CircleCoordinates> getAllCircles(){

        try {

            String query = "select c from CircleCoordinates c";

            Query<CircleCoordinates> allCircles = session.createQuery(query, CircleCoordinates.class);

            transaction.commit();

            return allCircles.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





}