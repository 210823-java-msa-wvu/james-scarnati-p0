package dev.scarnati.service;

import dev.scarnati.model.Car;
import dev.scarnati.repositories.CarRepo;
import dev.scarnati.repositories.CrudInterface;

public class SalesServices {
    CrudInterface<Car> carCrudInterface = new CarRepo();

//changes the price of a car
    public boolean update(Car car){
       return carCrudInterface.update(car);

    }

}

