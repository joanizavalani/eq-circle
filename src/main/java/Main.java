import org.hibernate.Session;
import configuration.HibernateUtility;
import repository.CoordinatesRepository;
import repository.DataRepository;
import service.CoordinatesService;
import service.DataService;

public class Main {
    public static void main(String[] args) {

        // simple circle calculator app: stores data (coordinates, radius) in a MySQL database
        // user is able to get radius, area and perimeter of circle through a console UI.
        // other functionalities: get equation, move a circle, resize a circle, delete a circle etc.
        // technologies: Hibernate framework, Maven Build, IntelliJ IDEA, lombok

        Session session = HibernateUtility.getSessionFactory().openSession();

        CoordinatesRepository coordinatesRepository = new CoordinatesRepository(session);
        DataRepository dataRepository = new DataRepository(session);

        CoordinatesService coordinatesService = new CoordinatesService(coordinatesRepository);
        DataService dataService = new DataService(dataRepository);

        ConsoleUI consoleUI = new ConsoleUI(coordinatesService, dataService);
        consoleUI.runMenu();

        session.close();
    }


}