package org.joza.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joza.entities.CircleData;

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
}
