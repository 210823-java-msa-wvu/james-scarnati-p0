package dev.scarnati.service;

import dev.scarnati.model.Accounts;
import dev.scarnati.model.Car;
import dev.scarnati.model.Customer;
import dev.scarnati.repositories.AccountsRepo;
import dev.scarnati.repositories.CarRepo;
import dev.scarnati.repositories.CrudInterface;
import dev.scarnati.repositories.CustomerRepo;


import java.util.List;
import java.util.Objects;

//Methods used in the Login and Main menu accessible by all users
public class UserServices {
    CrudInterface<Accounts> crudInterface = new AccountsRepo();
    CrudInterface<Customer> customerCrudInterface = new CustomerRepo();
    CrudInterface<Car> carCrudInterface = new CarRepo();
    AccountsRepo accountsRepo = new AccountsRepo();
//checks login credentials
    public boolean login(String username, String password) {


        Accounts a = accountsRepo.getByUsername(username);
        if (a != null) {
            return username.equals(a.getUsername()) && password.equals(a.getPassword());
        }
        return false;
    }

    //Creates an Account in the Accounts Table
    public boolean add(String username, String password, String email) {
        Accounts a = new Accounts(username, password, email);
        return crudInterface.add(a);

    }
    //finds a car by the cars id from the database
    public Car getCarById(Integer id){

        return this.carCrudInterface.getById(id);

    }
    //Creates a Customer in the Customer Table
    public boolean add(String firstName, String lastName, String email, String phoneNumber) {
        Customer c = new Customer(firstName, lastName, email, phoneNumber);
       return customerCrudInterface.add(c);

    }

    //Lists all cars
    public List<Car> getAll() {
        return this.carCrudInterface.getAll();
    }





//Checks to see if the user can access the customer screen
    public boolean enterCustomer(String username) {


        Accounts a = accountsRepo.checkByUsernameCustomer(username);
        return a != null;
    }
    //checks a user by username to see if they can access the sales menu
    public boolean enterSales(String username) {


        Accounts a = accountsRepo.checkByUsernameSales(username);
        if (a != null) {
            return Objects.equals(a.getPos(), "Sales");
        }
        return false;
    }
//Checks a user by username to see if they can access the management menu
    public boolean enterManagement(String username) {


        Accounts a = accountsRepo.checkByUsernameManagement(username);
        if (a != null) {
            return Objects.equals(a.getPos(), "Manager");
        }
        return false;
    }
}