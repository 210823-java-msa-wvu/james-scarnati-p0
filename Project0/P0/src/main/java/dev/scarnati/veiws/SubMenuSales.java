package dev.scarnati.veiws;

import dev.scarnati.service.exceptions.InvalidSelectionException;
import dev.scarnati.model.Car;
import dev.scarnati.service.SalesServices;
import dev.scarnati.service.UserServices;


import java.sql.SQLException;
import java.util.Scanner;

import static dev.scarnati.veiws.LoginMenu.Username;

public class SubMenuSales {
    static UserServices userServices = new UserServices();
    static SalesServices salesServices = new SalesServices();
    public static void display() throws InvalidSelectionException, SQLException {
        boolean checkManagement = userServices.enterManagement(Username);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.println("(1) Sell Car");
            System.out.println("(2) View Cars");
            System.out.println("(3) Update Car Price");
            System.out.println("(4)Managers menu (Management Only!)");
            System.out.println("(0) Logout");

            int choice = scanner.nextInt();
            try {
                salesServices.check(choice);
            }catch (InvalidSelectionException e){
                System.out.println("Custom Exception Caught: Invalid input: " + choice);
            }
                switch (choice) {
                    case 1:
                        //updates the boolean determining the status of the
                        // car as sold or not
                        System.out.println("Enter Car Id:  ");
                        int id = scanner.nextInt();
                        Car car = userServices.getCarById(id);
                        System.out.println("is this car being sold (true or false)?");
                        boolean sell = scanner.nextBoolean();
                        car.setSold(sell);
                        boolean sellCar = salesServices.update(car);
                        if (sellCar) {
                            System.out.println(userServices.getCarById(id));
                        } else {
                            System.out.println("Car can not be sold!");
                        }
                    case 2:
                        // lists all cars in the database
                        System.out.println(userServices.getAll());
                        break;
                    case 3:
                        //changes the price of a car by the
                        // associated id of the car
                        System.out.println("Enter Car Id: ");
                        int id2 = scanner.nextInt();
                        Car car2 = userServices.getCarById(id2);
                        System.out.println("Enter New Price: ");
                        int price = scanner.nextInt();
                        car2.setPrice(price);
                        boolean priceChange = salesServices.update(car2);
                        if (priceChange) {
                            System.out.println(userServices.getCarById(id2));
                        } else {
                            System.out.println("Car Could Not be Updated!");
                        }
                        break;
                    case 4:
                        //Allows  the manager to return to the
                        // management menu while restricting sales
                        //employees access
                        if (checkManagement) {
                            SubMenuManager.display();
                        } else {
                            System.out.println("You do not have access to this menu!");
                        }
                        break;
                    case 0:
                        //returns the user to the login menu
                        running = false;
                        LoginMenu.display();
                        break;
                    default:
                        break;
            }


        }
        scanner.close();
    }
}
