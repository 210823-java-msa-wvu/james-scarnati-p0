package dev.scarnati.veiws;


import dev.scarnati.service.exceptions.InvalidSelectionException;

import dev.scarnati.service.UserServices;


import java.sql.SQLException;
import java.util.Scanner;

import static dev.scarnati.veiws.LoginMenu.Username;

public class MainMenu {

    private static UserServices userServices = new UserServices();

    public static void display() throws InvalidSelectionException, SQLException {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        boolean checkCustomer = userServices.enterCustomer(Username);
        boolean checkSales = userServices.enterSales(Username);
        boolean checkManagement = userServices.enterManagement(Username);

        System.out.println("Welcome Please select your Account type!");
        while (running) {
            System.out.println("1) Customer");
            System.out.println("2) Sales");
            System.out.println("3) Management");
            System.out.println("0) Logout");

            int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        //Checks users account type to see if they can access the customer menu
                        if (checkCustomer) {
                            SubMenuCustomer.display();
                        } else {
                            System.out.println("Customers Only!");
                        }
                        break;
                    case 2:
                        //Checks to see if the user is an employee and in sales
                        if (checkSales) {
                            SubMenuSales.display();
                        } else {
                            System.out.println("You do not have access to this menu!");
                        }
                        break;
                    case 3:
                        //check to see if the user is the manager
                        if (checkManagement) {
                            SubMenuManager.display();
                        } else {
                            System.out.println("You do not have access to this menu!");
                        }
                        break;
                    case 0:
                        //returns to the login menu
                        running = false;
                        LoginMenu.display();
                        break;
                    default:
                        throw new InvalidSelectionException("Invalid Input!");
                }


        }
        scanner.close();
    }
}