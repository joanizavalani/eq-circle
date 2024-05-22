import org.hibernate.Session;
import configuration.HibernateUtility;
import repository.CoordinatesRepository;
import repository.DataRepository;
import service.CoordinatesService;
import service.DataService;

public class Main {
    public static void main(String[] args) {

        // simple circle calculator app: stores data (coordinates, radius) in a mySQL database
        // user will be able to get radius, area and perimeter of circle through a consoleUI.
        // other functionalities: move a circle, resize radius, etc.
        // frameworks & libraries: Hibernate framework, lombok

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