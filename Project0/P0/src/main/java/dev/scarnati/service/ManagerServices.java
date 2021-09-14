package dev.scarnati.service;


import dev.scarnati.model.Accounts;
import dev.scarnati.model.Car;
import dev.scarnati.model.Employee;
import dev.scarnati.repositories.AccountsRepo;
import dev.scarnati.repositories.CarRepo;
import dev.scarnati.repositories.CrudInterface;
import dev.scarnati.repositories.EmployeeRepo;
import dev.scarnati.service.exceptions.InvalidSelectionException;

import java.util.List;

public class ManagerServices {

    CrudInterface<Employee> employeeCrudInterface = new EmployeeRepo();
    CrudInterface<Car> carCrudInterface = new CarRepo();
    CrudInterface<Accounts> accountsCrudInterface = new AccountsRepo();
    AccountsRepo accountsRepo = new AccountsRepo();
// adds a new employee to the database (hiring)
    public boolean add(String firstName, String lastName, String email, String phoneNumber, String title)  {
        Employee e = new Employee(firstName, lastName, email, phoneNumber, title);
        return employeeCrudInterface.add(e);

    }
    //removes an employee from the database aka firing
    public boolean delete(Integer id) {
        return employeeCrudInterface.delete(id);

    }

//displays all employees in the database
    public List<Employee> getAll() {
        return this.employeeCrudInterface.getAll();
    }
//removes a car from the database
    public boolean deleteCar(Integer id) {

        return carCrudInterface.delete(id);

    }
//adds a new car to the database
    public boolean addCar(Integer year, String make, String model, String engine, String color, Integer miles, Integer price) {

        Car car = new Car(year, make, model, engine, color, miles, price, false);
       return carCrudInterface.add(car);

    }
//changes the last name or phone number of an employee
    public boolean update(Employee employee) {
        return employeeCrudInterface.update(employee);

    }
    //finds an employee by their corresponding id number
    public Employee getEmployeeById(Integer id){

        return this.employeeCrudInterface.getById(id);

    }
    public Accounts getAccountByEmail(String email){
        return this.accountsRepo.getAccountByEmail(email);
    }
    public boolean deleteAccount(Integer id) {
        return accountsCrudInterface.delete(id);

    }
    public void check(Integer choice) throws InvalidSelectionException {

        if (choice < 8 && choice >= 0) {
            return;
        }
        throw new InvalidSelectionException("Invalid Input: " + choice);
    }
}

