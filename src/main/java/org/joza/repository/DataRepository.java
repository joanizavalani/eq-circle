package org.joza.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joza.entities.CircleCoordinates;
import org.joza.entities.CircleData;

import java.util.UUID;

@AllArgsConstructor

public class DataRepository {

    private final Session session;

    public void addAllData(CircleData data){

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();
            session.save(data);
            transaction.commit();

        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public double getRadius(UUID coordinatesId) {

        Transaction transaction = null;
        CircleData data = null;

        try {

            transaction = session.beginTransaction();

            String query = "select d from CircleData d where d.coordinates.id = :coordinatesId";

            data = session.createQuery(query, CircleData.class)
                    .setParameter("coordinatesId", coordinatesId)
                    .uniqueResult();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        assert data != null;
        return data.getRadius();
    }

    public double getArea(UUID coordinatesId) {

        Transaction transaction = null;

        CircleData data = null;

        try {

            transaction = session.beginTransaction();

            String query = "select d from CircleData d where d.coordinates.id = :coordinatesId";

            data = session.createQuery(query, CircleData.class)
                    .setParameter("coordinatesId", coordinatesId)
                    .uniqueResult();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        assert data != null;
        return data.getArea();
    }

    public double getPerimeter(UUID coordinatesId){

        Transaction transaction = null;
        CircleData data = null;

        try {

            transaction = session.beginTransaction();

            String query = "select d from CircleData d where d.coordinates.id = :coordinatesId";

            data = session.createQuery(query, CircleData.class)
                    .setParameter("coordinatesId", coordinatesId)
                    .uniqueResult();

        } catch (Exception e){

            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        }

        assert data != null;
        return data.getPerimeter();
    }
}
