import entities.CircleCoordinates;
import entities.CircleData;
import service.CoordinatesService;
import service.DataService;

import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {

    // all methods connecting to runMenu() method are private for there is no need
    // for them to be accessed outside of this class independently

    // fields

    private final CoordinatesService coordinatesService;

    private final DataService dataService;

    private final Scanner scanner;

    // constructor

    public ConsoleUI(CoordinatesService coordinatesService, DataService dataService){

        this.coordinatesService = coordinatesService;
        this.dataService = dataService;
        this.scanner = new Scanner(System.in);
    }

    public void runMenu(){

        boolean appIsRunning = true;

        while (appIsRunning){

            titleMenu(); // title menu will be displayed and a scanner will take an integer value

            int choice = scanner.nextInt();

            switch (choice){

                case 1:
                    createCircle();
                    break;

                case 2:
                    showAllCircles();
                    break;

                case 3:
                    getCoordinatesOfCircle();
                    break;

                case 4:
                    getRadiusOfCircle();
                    break;

                case 5:
                    getAreaOfCircle();
                    break;

                case 6:
                    getPerimeterOfCircle();
                    break;

                case 7:
                    getEquationOfCircle();
                    break;

                case 8:
                    moveCircle();
                    break;

                case 9:
                    resizeRadiusOfCircle();
                    break;

                case 10:
                    deleteCircle();
                    break;

                case 11:
                    appIsRunning = false;
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Please insert a valid choice number from 1 - 10.");
                    scanner.nextInt();
                    break;
            }
        }
    }

    private void titleMenu(){

        System.out.println();
        System.out.println("○ ○ ○ EQUATION OF CIRCLE CALCULATOR APP ○ ○ ○\n");

        System.out.println("What would you like to do?\n");

        System.out.println("1 --> create a new circle...");
        System.out.println("2 --> show all circles...");
        System.out.println("3 --> get coordinates of circle");
        System.out.println("4 --> get radius of circle...");
        System.out.println("5 --> get area of circle...");
        System.out.println("6 --> get perimeter of circle...");
        System.out.println("7 --> get equation of circle...");
        System.out.println("8 --> move a circle...");
        System.out.println("9 --> resize the radius of a circle...");
        System.out.println("10 --> delete a circle...");
        System.out.println("11 --> exit program...");

        System.out.println("\nEnter your choice:");

    }

    // option 1
    private void createCircle(){

        System.out.println("\n~~~ CREATE A NEW CIRCLE~~~\n");

        System.out.println("Insert X coordinate for the center of the circle:");
        double center_x = scanner.nextDouble();

        System.out.println("Insert Y coordinate for the center of the circle:");
        double center_y = scanner.nextDouble();

        System.out.println("Insert X coordinate for a point in the circle:");
        double point_x = scanner.nextDouble();

        System.out.println("Insert Y coordinate for the same point in the circle:");
        double point_y = scanner.nextDouble();

        UUID id = UUID.randomUUID();

        // adding coordinates to circle_coordinates table

        CircleCoordinates coordinates = new CircleCoordinates();

        coordinates.setId(id);
        coordinates.setCenterX(center_x);
        coordinates.setCenterY(center_y);
        coordinates.setPointX(point_x);
        coordinates.setPointY(point_y);

        coordinatesService.addCoordinates(coordinates);

        System.out.println("\nCircle was saved to database.");

        // after adding a new circle in the circle_coordinates table, the circle_data table needs to be filled too

        CircleData data = new CircleData();

        UUID dataId = UUID.randomUUID();

        // formula for radius, area & perimeter

        double radius = Math.sqrt( Math.pow((coordinates.getCenterX() - coordinates.getPointX()), 2)
                + Math.pow((coordinates.getCenterY() - coordinates.getPointY()), 2) ); // SQRT[(x-x0)^2 + (y-y0)^2)]

        double area = Math.PI * Math.pow(radius, 2);

        double perimeter = Math.PI * 2 * radius;

        // adding fields to circle_data table

        data.setId(dataId);
        data.setCoordinates(coordinates);
        data.setRadius(radius);
        data.setArea(area);
        data.setPerimeter(perimeter);

        dataService.addAllData(data);

        System.out.println("Your circle's ID is: "+ coordinates.getId());
    }

    // option 2
    private void showAllCircles(){

        for(CircleCoordinates coordinates : coordinatesService.getAllCircles()){

            System.out.println
                (coordinates.getId() +" --> center C("+ coordinates.getCenterX() +", "+ coordinates.getCenterY()
                +") point P("+ coordinates.getPointX() +", "+ coordinates.getPointY() +")");
        }

        System.out.println("\nThese are all the circles saved in the database.\n");
    }

    // option 3
    private void getCoordinatesOfCircle() {

        System.out.println("\n~~~ GET CIRCLE COORDINATES ~~~\n");

        System.out.println("Enter circle ID:");

        String idFromString = scanner.next();
        UUID id;

        try {

            id = UUID.fromString(idFromString);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format. Please enter a valid UUID.");
            return;
        }

        CircleCoordinates coordinates = coordinatesService.getCoordinates(id);

        System.out.println("circle coordinates: center C(" + coordinates.getCenterX() + ", " +
                coordinates.getCenterY() + "); point P(" + coordinates.getPointX() + ", " + coordinates.getPointY() + ")");
    }

    // option 4
    private void getRadiusOfCircle(){

        System.out.println("\n~~~ GET CIRCLE RADIUS ~~~\n");

        System.out.println("Enter circle ID:");

        String idFromString = scanner.next();
        UUID id;

        try {

            id = UUID.fromString(idFromString);

        } catch (IllegalArgumentException e) {

            System.out.println("Invalid UUID format. Please enter a valid UUID.");
            return;
        }

        double radius = dataService.getRadius(id);

        System.out.println("\nThe radius of the circle is "+ radius +" units.");
    }

    // option 5
    private void getAreaOfCircle(){

        System.out.println("\n~~~ GET AREA OF CIRCLE ~~~\n");

        System.out.println("Enter circle ID:");

        String idFromString = scanner.next();
        UUID id = null;

        try {

            id = UUID.fromString(idFromString);

        } catch (IllegalArgumentException e){

            System.out.println("Invalid UUID format. Please enter a valid UUID.");
        }

        double area = dataService.getArea(id);

        System.out.println("\nThe area of the circle is "+ area +" square units.");
    }

    // option 5
    private void getPerimeterOfCircle(){

        System.out.println("\n~~~ GET PERIMETER OF CIRCLE ~~~\n");

        System.out.println("Enter circle ID:");

        String idFromString = scanner.next();
        UUID id = null;

        try {

            id = UUID.fromString(idFromString);

        } catch (IllegalArgumentException e){

            System.out.println("Invalid UUID format. Please enter a valid UUID.");
        }

        double perimeter = dataService.getPerimeter(id);

        System.out.println("\nThe perimeter of the circle is "+ perimeter +" units.");
    }

    // option 7
    private void getEquationOfCircle(){

        System.out.println("\n~~~ GET EQUATION OF CIRCLE \n");

        System.out.println("Enter circle ID:");

        String idFromString = scanner.next();
        UUID id = null;

        try {

            id = UUID.fromString(idFromString);

        } catch (IllegalArgumentException e){

            System.out.println("Invalid UUID format. Please enter a valid UUID.");
        }

        CircleCoordinates coordinates = coordinatesService.getCoordinates(id);

        double radius = dataService.getRadius(id);

        double centerX = coordinates.getCenterX();
        double centerY = coordinates.getCenterY();

        System.out.println("The equation of this circle is:\n");

        if(centerX == 0 && centerY == 0) {

            String equation = "x^2 + y^2 = "+ Math.pow(radius, 2);
            System.out.println(equation);

        } else if (centerX == 0){

            // these nested if statements exist for the sole purpose of turning double negatives to positives

            if(centerY < 0){

                centerY = Math.abs(centerY);

                String equation = "x^2 + (y + " + centerY +")^2 = "+ Math.pow(radius, 2);
                System.out.println(equation);

            } else {

                String equation = "x^2 + (y - " + centerY + ")^2 = " + Math.pow(radius, 2);
                System.out.println(equation);
            }

        } else if (centerY == 0){

            if(centerX < 0){

                centerX = Math.abs(centerX);

                String equation = "(x + "+ centerX +")^2 + y^2 = "+ Math.pow(radius, 2);
                System.out.println(equation);

            } else {

                String equation = "(x - " + centerX + ")^2 + y^2 = " + Math.pow(radius, 2);
                System.out.println(equation);
            }

        } else {

            if(centerX < 0 && centerY < 0){

                centerX = Math.abs(centerX);
                centerY = Math.abs(centerY);

                String equation = "(x + "+ centerX +")^2 + (y + " + centerY +")^2 = "+ Math.pow(radius, 2);
                System.out.println(equation);

            } else if(centerX < 0){

                centerX = Math.abs(centerX);

                String equation = "(x + "+ centerX +")^2 + (y - " + centerY +")^2 = "+ Math.pow(radius, 2);
                System.out.println(equation);

            } else if(centerY < 0){

                centerY = Math.abs(centerY);

                String equation = "(x - "+ centerX +")^2 + (y + " + centerY +")^2 = "+ Math.pow(radius, 2);
                System.out.println(equation);

            } else {
                String equation = "(x - "+ centerX +")^2 + (y - " + centerY +")^2 = "+ Math.pow(radius, 2);
                System.out.println(equation);
            }
        }
    }

    // option 8
    private void moveCircle(){

        System.out.println("\n~~~ MOVE A CIRCLE ~~~\n");

        System.out.println("Insert the ID of the circle which center you want to alter: ");

        String idFromString = scanner.next();
        UUID id = null;

        try {

            id = UUID.fromString(idFromString);

        } catch (IllegalArgumentException e){

            System.out.println("Invalid UUID format. Please enter a valid UUID.");
        }

        System.out.println("Insert the coordinates of the vector you want to move the circle with.");

        System.out.println("X coordinate: ");
        double vectorX = scanner.nextDouble();

        System.out.println("Y coordinate: ");
        double vectorY = scanner.nextDouble();

        coordinatesService.moveCircle(id, vectorX, vectorY);

        System.out.println("The circle was successfully moved according to the vector " +
                "("+ vectorX +", "+ vectorY +").");
    }

    // option 9
    private void resizeRadiusOfCircle(){

        System.out.println("\n~~~ RESIZE CIRCLE ~~~\n");

        System.out.println("Insert the ID of the circle you want to resize: ");

        String idFromString = scanner.next();
        UUID id = null;

        try {

            id = UUID.fromString(idFromString);

        } catch (IllegalArgumentException e){

            System.out.println("Invalid UUID format. Please enter a valid UUID.");
        }

        System.out.println("By which factor do you want to multiply the length of the radius?");
        double factor = scanner.nextDouble();

        dataService.resizeCircle(id, factor);
        coordinatesService.resizeCircle(id, factor);

        System.out.println("Circle was resized by a factor of "+ factor +".");
    }

    // option 10
    private void deleteCircle(){

        System.out.println("\n~~~ DELETE A CIRCLE ~~~\n");

    }
}
