package dev.scarnati.service;

import dev.scarnati.model.Car;
import dev.scarnati.repositories.CarRepo;
import dev.scarnati.repositories.CrudInterface;
import dev.scarnati.service.exceptions.InvalidSelectionException;

public class SalesServices {
    CrudInterface<Car> carCrudInterface = new CarRepo();

//changes the price of a car
    public boolean update(Car car){
       return carCrudInterface.update(car);

    }

    public void check(Integer choice) throws InvalidSelectionException {

        if (choice < 5 && choice >= 0) {
            return;
        }
        throw new InvalidSelectionException("Invalid Input: " + choice);
    }
}

