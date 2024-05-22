package repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import entities.CircleData;
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

            String query = "SELECT d FROM CircleData d WHERE d.coordinates.id = :coordinatesId";

            data = session.createQuery(query, CircleData.class)
                    .setParameter("coordinatesId", coordinatesId)
                    .uniqueResult();

            transaction.commit();

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

            String query = "SELECT d FROM CircleData d WHERE d.coordinates.id = :coordinatesId";

            data = session.createQuery(query, CircleData.class)
                    .setParameter("coordinatesId", coordinatesId)
                    .uniqueResult();

            transaction.commit();

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

            String query = "SELECT d FROM CircleData d WHERE d.coordinates.id = :coordinatesId";

            data = session.createQuery(query, CircleData.class)
                    .setParameter("coordinatesId", coordinatesId)
                    .uniqueResult();

            transaction.commit();

        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        }

        assert data != null;
        return data.getPerimeter();

    }

    public void resizeCircle(UUID coordinatesId, double factor){

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            String query = "UPDATE CircleData d " +
                    "SET d.radius = d.radius * :factor, " +
                    "d.area = d.area * (:factor * :factor), " +
                    "d.perimeter = d.perimeter * :factor " +
                    "WHERE d.coordinates.id = :coordinatesId";

            Query<?> updateQuery = session.createQuery(query);

            updateQuery.setParameter("coordinatesId", coordinatesId);
            updateQuery.setParameter("factor", factor);

            updateQuery.executeUpdate();

            transaction.commit();
            session.clear(); // clears the cache of the coordinates table prior to the change that was just commited

        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        }

    }
}
