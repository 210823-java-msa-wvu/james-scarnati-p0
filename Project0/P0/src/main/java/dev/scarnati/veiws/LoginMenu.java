package dev.scarnati.veiws;



import java.sql.SQLException;
import java.util.Scanner;

import dev.scarnati.service.exceptions.InvalidSelectionException;

import dev.scarnati.service.UserServices;


public class LoginMenu {
    public static String Username;
    static UserServices userServices = new UserServices();

    public static void display() throws InvalidSelectionException, SQLException {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        boolean start1 = true;
        boolean start2 = true;

            while (running) {
                System.out.println("(1) Log In");
                System.out.println("(2) Create Account");
                System.out.println("(3) View Cars");
                System.out.println("(0) Exit");

                int choice;
                do {
                    System.out.println("Enter Choice:  ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("That's not a number! Please enter a number: ");
                        scanner.next();
                    }
                    choice = scanner.nextInt();
                }while(choice < 0);
                try {
                    userServices.check(choice);
                }catch (InvalidSelectionException e){
                    System.out.println("Custom Exception Caught: Invalid input: " + choice);
                }

                switch (choice) {

                    case 1:
                        //log in function
                        scanner.nextLine();
                        System.out.println("Enter Username: ");
                        String username = scanner.nextLine();
                        Username = username;
                        System.out.println("Enter Password: ");
                        String password = scanner.nextLine();
                        boolean signInResponse = userServices.login(username, password);
                        //Checks username and password to see if they match the database
                        if (signInResponse) {
                            System.out.println("Login Successful");
                            MainMenu.display();
                        } else {
                            System.out.println("Incorrect username or password! " +
                                    "Please try again!");
                        }

                        break;

                    case 2:
                        //account creation
                        scanner.nextLine();
                        System.out.println("Enter First Name: ");
                        String firstName = scanner.nextLine();
                        while(start1){
                        if (firstName.matches(".*\\d.*")) {
                            System.out.println("first name contains digits \n Please Re-enter first name: ");
                            firstName = scanner.nextLine();
                        } else {
                            start1 = false;

                        }
                    }
                        System.out.println("Enter Last Name: ");
                        String lastName = scanner.nextLine();
                        while(start2) {
                            if (lastName.matches(".*\\d.*")) {
                                System.out.println("last name contains digits \n Please Re-enter last name: ");
                                lastName = scanner.nextLine();
                            } else {
                                start2 = false;

                            }
                        }
                        System.out.println("Enter e-mail: ");
                        String email = scanner.nextLine();
                        System.out.println("Enter Phone Number: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.println("Pick a Username: ");
                        username = scanner.nextLine();
                        System.out.println("Enter a Password: ");
                        password = scanner.nextLine();
                        boolean accountCreation = userServices.add(username, password, email);
                        //checks to see if the account info was updated
                        if (accountCreation) {
                            System.out.println("Account Created!");
                            boolean customerCreation = userServices.add(firstName, lastName, email, phoneNumber);
                            //checks to see if the customer was added to the database
                            if (customerCreation) {
                                System.out.println("Customer Created!");
                            } else {
                                System.out.println("Error creating Customer! Please try again!");
                            }
                        } else {
                            System.out.println("Error creating account! Please try again!");
                        }


                        break;

                    case 3:
                        //pulls and prints all cars
                        System.out.println(userServices.getAll());
                        break;
                    case 0:
                        //ends the program
                        running = false;
                        System.exit(0);
                        break;
                    default:
                        break;
                }




            }

        scanner.close();
    }
}
