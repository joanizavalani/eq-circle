package org.joza;

import org.joza.entities.CircleCoordinates;
import org.joza.service.CoordinatesService;
import org.joza.service.DataService;

import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {

    // all methods connecting to runMenu() method are private for there is no need
    // for them to be accessed outside of this class independently

    // fields

    private final CoordinatesService coordinatesService;

    private final DataService dataService;

    private final Scanner scanner;

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

        System.out.println("\n~~~ CREATE A NEW CIRCLE~~\n");

        System.out.println("Insert X coordinate for the center of the circle:");
        double center_x = scanner.nextDouble();

        System.out.println("Insert Y coordinate for the center of the circle:");
        double center_y = scanner.nextDouble();

        System.out.println("Insert X coordinate for a point in the circle:");
        double point_x = scanner.nextDouble();

        System.out.println("Insert Y coordinate for the same point in the circle:");
        double point_y = scanner.nextDouble();

        UUID id = UUID.randomUUID();
        System.out.println("Your ID for this circle is: "+ id);

        CircleCoordinates coordinates = new CircleCoordinates();

        coordinates.setId(id);
        coordinates.setCenterX(center_x);
        coordinates.setCenterY(center_y);
        coordinates.setPointX(point_x);
        coordinates.setPointY(point_y);

        coordinatesService.addCoordinates(coordinates);

        System.out.println("\nCircle was saved to database.");

    }

    private void showAllCircles(){

        for(CircleCoordinates coordinates : coordinatesService.getAllCircles()){

            System.out.println
                (coordinates.getId() +" --> CENTER c("+ coordinates.getCenterX() +", "+ coordinates.getCenterY()
                +") POINT p("+ coordinates.getPointX() +", "+ coordinates.getPointY() +")");
        }

        System.out.println("\nThese are all the circles saved in your database.\n");

    }

    private void getCoordinatesOfCircle(){

        System.out.println("GET CIRCLE COORDINATES\n");

        System.out.println("Enter location ID:");
        UUID id = UUID.fromString(scanner.nextLine());

    }

    private void getRadiusOfCircle(){

        System.out.println("GET CIRCLE RADIUS\n");
    }

    private void getAreaOfCircle(){

        System.out.println("GET AREA OF CIRCLE\n");

    }

    private void getPerimeterOfCircle(){

        System.out.println("GET PERIMETER OF CIRCLE\n");

    }

    private void getEquationOfCircle(){

        System.out.println("GET EQUATION OF CIRCLE\n");

    }

    private void moveCircle(){

        System.out.println("MOVE A CIRCLE\n");

    }

    private void resizeRadiusOfCircle(){

        System.out.println("RESIZE RADIUS OF CIRCLE\n");

    }

    private void deleteCircle(){


        System.out.println("DELETE A CIRCLE\n");

    }
}
