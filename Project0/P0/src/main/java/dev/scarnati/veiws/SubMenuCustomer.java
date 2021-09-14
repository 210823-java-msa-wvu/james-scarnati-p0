package dev.scarnati.veiws;

import dev.scarnati.service.exceptions.InvalidSelectionException;
import dev.scarnati.model.Accounts;
import dev.scarnati.model.Car;
import dev.scarnati.model.Customer;

import dev.scarnati.service.CustomerServices;
import dev.scarnati.service.UserServices;

import java.sql.SQLException;
import java.util.Scanner;

import static dev.scarnati.veiws.LoginMenu.Username;

public class SubMenuCustomer {
    static UserServices userServices = new UserServices();
    static CustomerServices customerServices = new CustomerServices();

    public static void display() throws InvalidSelectionException, SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1) View Cars");
            System.out.println("2) Buy a Car");
            System.out.println("3) View Your Cars");
            System.out.println("4) Change Password");
            System.out.println("0) Logout");

            int choice = scanner.nextInt();
            try {
                customerServices.check(choice);
            }catch (InvalidSelectionException e){
                System.out.println("Custom Exception Caught: Invalid input: " + choice);
            }
            switch (choice) {
                case 1:
                    //pulls all the cars and prints them out
                    System.out.println(userServices.getAll());
                    break;
                case 2:
                    //allows the customer to buy a car by pulling the car and
                    //associating the cars id to the customer
                    System.out.println("Enter Car Id:  ");
                    int id = scanner.nextInt();
                    Car car = userServices.getCarById(id);
                    Customer c = customerServices.getCustomerByUsername(Username);
                    c.setCarId(car.getId());
                    boolean buyCar = customerServices.buyCar(c);
                    if (buyCar) {
                        System.out.println("Congratulations you bought the Car!");
                    } else {
                        System.out.println("Unfortunately the sale could not be completed at this time.");
                    }
                    break;
                case 3:
                    //pulls the car associated to the customer
                    Customer customer = customerServices.getCustomerByUsername(Username);
                    if (customer.getCarId() != null) {
                        int carId = customer.getCarId();
                        Car cc = userServices.getCarById(carId);
                        if (cc != null) {
                            System.out.println(userServices.getCarById(carId));
                        }
                    } else {
                        System.out.println("You do not own a car sold buy us.");
                    }

                    break;
                case 4:
                    //Changes the password by pulling
                    // the users account info up
                    //and requesting a new password
                    // then submits that
                    // change to the database
                    scanner.nextLine();
                    Accounts accounts = customerServices.getByUsername(Username);
                    System.out.println(accounts);
                    if (accounts.getId() != null) {
                        System.out.println("Enter new password: ");
                        String password = scanner.nextLine();
                        accounts.setPassword(password);
                        boolean changePass = customerServices.changePass(accounts);
                        if (changePass) {
                            System.out.println("Password Changed!");
                        }
                    } else {
                        System.out.println("Password not changed.");
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
    }
}
