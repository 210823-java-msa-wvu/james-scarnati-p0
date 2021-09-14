package dev.scarnati.veiws;


import dev.scarnati.model.Accounts;
import dev.scarnati.service.exceptions.InvalidSelectionException;
import dev.scarnati.model.Employee;
import dev.scarnati.service.ManagerServices;
import dev.scarnati.service.UserServices;


import java.sql.SQLException;
import java.util.Scanner;

public class SubMenuManager {
    static UserServices userServices = new UserServices();
    static ManagerServices managerServices = new ManagerServices();

    public static void display() throws InvalidSelectionException, SQLException {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        boolean start1 = true;
        boolean start2 = true;
        boolean start3 = true;


        while (running) {
            System.out.println("(1) Sales Menu");
            System.out.println("(2) Hire An Employee");
            System.out.println("(3) Fire An Employee");
            System.out.println("(4) Get All Employees");
            System.out.println("(5) Add A Car");
            System.out.println("(6) Update Employee Info");
            System.out.println("(7) Remove A Car");
            System.out.println("(0) Log-out");

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
                managerServices.check(choice);
            } catch (InvalidSelectionException e) {
                System.out.println("Custom Exception Caught: Invalid input: " + choice);
            }
            switch (choice) {
                case 1:
                    //bring the manager to the sales menu
                    SubMenuSales.display();
                    break;
                case 2:
                    //Hires an employee by taking in data and submitting it
                    //to the database
                    scanner.nextLine();
                    System.out.println("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    while (start1) {
                        if (firstName.matches(".*\\d.*")) {
                            System.out.println("last name contains digits \n Please Re-enter last name: ");
                            firstName = scanner.nextLine();
                        } else {
                            start1 = false;

                        }
                    }
                    System.out.println("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    while (start2) {
                        if (lastName.matches(".*\\d.*")) {
                            System.out.println("last name contains digits \n Please Re-enter last name: ");
                            lastName = scanner.nextLine();
                        } else {
                            start2 = false;

                        }
                    }
                    String email = (firstName.charAt(0) + lastName + "@dealership.com").toLowerCase();
                    System.out.println("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    String username = (firstName.charAt(0) + lastName).toLowerCase();
                    System.out.println("Enter a Password: ");
                    String password = scanner.nextLine();
                    String title = "Sales";
                    boolean accountCreation = userServices.add(username, password, email);
                    boolean employeeCreation = managerServices.add(firstName, lastName, email, phoneNumber, title);
                    if (accountCreation) {
                        System.out.println("Account Created!");
                        if (employeeCreation) {
                            System.out.println("Employee Created!");
                        } else {
                            System.out.println("Error creating Employee! Please try again!");
                        }
                    } else {
                        System.out.println("Error creating account! Please try again!");
                    }


                    break;
                case 3:
                    //'fires' an employee by removing them from the
                    //database using their id number as a reference
                    scanner.nextLine();
                    int id;
                    do {
                        System.out.println("Enter Employee Id: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("That's not a number! Please enter a number: ");
                            scanner.next();
                        }
                        id = scanner.nextInt();
                    }while(id < 0);
                    Employee a = managerServices.getEmployeeById(id);
                    String emaile = a.getEmail();
                    Accounts account = managerServices.getAccountByEmail(emaile);
                    int ida = account.getId();
                    boolean fire = managerServices.delete(id);
                    boolean delete = managerServices.deleteAccount(ida);
                    System.out.println(delete);
                    if (fire) {
                        System.out.println("Employee was fired!");
                    } else {
                        System.out.println("Employee could not be fired!");
                    }
                    if (delete) {
                        System.out.println("Employee Account was deleted!");
                    } else {
                        System.out.println("Employee Account was not deleted!");
                    }
                    break;
                case 4:
                    //lists all the employees in the database
                    System.out.println(managerServices.getAll());
                    break;
                case 5:
                    //adds a car to the database by taking user inputs
                    //and submitting them to the database as an object
                    scanner.nextLine();
                    int year;
                    do {
                        System.out.println("Enter Car Year: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("That's not a number! Please enter a number: ");
                            scanner.next();
                        }
                        year = scanner.nextInt();
                    }while(year < 0);
                    System.out.println("Enter Car Year: ");

                    scanner.nextLine();
                    System.out.println("Enter make: ");
                    String make = scanner.nextLine();
                    System.out.println("Enter Model: ");
                    String model = scanner.nextLine();
                    System.out.println("Enter Engine");
                    String engine = scanner.nextLine();
                    System.out.println("Enter color: ");
                    String color = scanner.nextLine();
                    int miles;
                    do {
                        System.out.println("Enter miles: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("That's not a number! Please enter a number: ");
                            scanner.next();
                        }
                        miles = scanner.nextInt();
                    }while(miles < 0);


                    System.out.println("Enter the price: ");
                    int price = scanner.nextInt();

                    boolean addCar = managerServices.addCar(year, make, model, engine, color, miles, price);
                    if (addCar) {
                        System.out.println("Car added!");
                    } else {
                        System.out.println("Car was not added! Try again!");
                    }
                    break;
                case 6:
                    //allows the manager to change the last name or phone
                    // number of an employee
                    int i;
                    do {
                        System.out.println("Enter Employee Id: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("That's not a number! Please enter a number: ");
                            scanner.next();
                        }
                        i = scanner.nextInt();
                    }while(i < 0);

                    Employee e = managerServices.getEmployeeById(i);
                    scanner.nextLine();
                    System.out.println("Enter employee Phone Number: ");
                    String pNumber = scanner.nextLine();
                    System.out.println("Enter Employee Last Name: ");
                    String lName = scanner.nextLine();
                    while (start3) {
                        if (lName.matches(".*\\d.*")) {
                            System.out.println("last name contains digits \n Please Re-enter last name: ");
                            lName = scanner.nextLine();
                        } else {
                            start3 = false;
                        }
                    }
                    e.setPhoneNumber(pNumber);
                    e.setLastName(lName);
                    boolean updateEmployee = managerServices.update(e);
                    if (updateEmployee) {
                        System.out.println(managerServices.getEmployeeById(i));
                    } else {
                        System.out.println("Employee could not be updated!");
                    }
                    break;
                case 7:
                    //removes a car from the database by the id that is
                    // entered
                    scanner.nextLine();
                    int ii;
                    do {
                        System.out.println("Enter Car Id: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("That's not a number! Please enter a number: ");
                            scanner.next();
                        }
                        ii = scanner.nextInt();
                    }while(ii < 0);

                        boolean removeCar = managerServices.deleteCar(ii);
                        if (removeCar) {
                            System.out.println("Car removed!");
                        } else {
                            System.out.println("Car could not be deleted!");
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





