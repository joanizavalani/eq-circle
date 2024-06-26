package repository;

import lombok.AllArgsConstructor;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import entities.CircleCoordinates;

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

            String query = "SELECT c FROM CircleCoordinates c";
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
                assert transaction != null;
                transaction.rollback();
                e.printStackTrace();
            }

        } else {
            throw new RuntimeException("Coordinates not found");
        }

        return coordinates;
    }

    // method to move center of circle
    public void moveCircle(UUID id, double movedX, double movedY){

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            String query = "UPDATE CircleCoordinates c " +
                    "SET c.centerX = c.centerX + :movedX, c.centerY = c.centerY+ :movedY, " +
                    "c.pointX = c.pointX + :movedX, c.pointY = c.pointY + :movedY " +
                    "WHERE c.id = :id";

            Query<?> updateQuery = session.createQuery(query);

            updateQuery.setParameter("movedX", movedX);
            updateQuery.setParameter("movedY", movedY);
            updateQuery.setParameter("id", id);
            updateQuery.executeUpdate();

            transaction.commit();
            session.clear(); // this little piece of Hibernate syntax clears the cache of the coordinates table
                             // prior to the change that was just commited

        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    // method to resize circle
    public void resizeCircle(UUID id, double factor){

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            String query = "UPDATE CircleCoordinates c " +
                    "SET c.pointX = c.centerX + :factor * (c.pointX - c.centerX), " +
                    "c.pointY = c.centerY + :factor * (c.pointY - c.centerY) " +
                    "WHERE c.id = :id";

            // coordinates of point need to be altered, since the circle expanded by a factor of the variable factor

            Query<?> updateQuery = session.createQuery(query);

            updateQuery.setParameter("id", id);
            updateQuery.setParameter("factor", factor);
            updateQuery.executeUpdate();

            transaction.commit();
            session.clear();

        } catch (Exception e){
            if(transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    // method to delete circle
    public void deleteCircle(UUID id){

        Transaction transaction = null;
        CircleCoordinates coordinates = null;

        try {

            transaction = session.beginTransaction();

            String query = "DELETE FROM CircleCoordinates c WHERE c.id = :id";

            session.createQuery(query).setParameter("id", id).executeUpdate();

            transaction.commit();
            session.clear();

        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}





