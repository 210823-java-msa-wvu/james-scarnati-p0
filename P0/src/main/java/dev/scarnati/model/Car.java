package dev.scarnati.model;

public class Car {

    private int year;
    private String make;
    private String model;
    private String engine;
    private String color;
    private int miles;
    private int price;
    private int id;
    private boolean sold;
    public Car(){

    }

    public Car(int price, int id) {
        this.price = price;
        this.id = id;
    }

    public Car(int year, String make, String model, String engine, String color, int miles, int price, boolean sold) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.color = color;
        this.miles = miles;
        this.price = price;
        this.sold = sold;
    }

    public Car(int year, String make, String model, String engine, String color, int miles, int price, int id, boolean sold) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.color = color;
        this.miles = miles;
        this.price = price;
        this.id = id;
        this.sold = sold;
    }

    public Car(Integer id, Integer year, String make, String model, String color, String engine, Integer miles, Integer price) {

        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.engine = engine;
        this.miles = miles;
        this.price = price;

    }

    public Car(Integer id, Integer year, String make, String model, String color, String engine, Integer miles) {

        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.engine = engine;
        this.miles = miles;

    }
    public Car(Integer year, String make, String model, String color, String engine, Integer miles, Integer price) {

        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.engine = engine;
        this.miles = miles;
        this.price = price;

    }
    public Car(Integer year, String make, String model, String color, String engine, Integer miles) {

        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.engine = engine;
        this.miles = miles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "\n" + "Car: " + id + " " + color + " " + year + " " + make + " " + model + " has "
                + miles +" miles. and costs: " + price + ". Car is for sale " + !sold;
    }

}
