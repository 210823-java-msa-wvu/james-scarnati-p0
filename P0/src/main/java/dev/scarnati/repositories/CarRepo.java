package dev.scarnati.repositories;

import dev.scarnati.model.Car;
import dev.scarnati.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepo implements CrudInterface<Car>{

    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    // Read
    @Override
    public Car getById(Integer Id) {
        int id, year, price, miles;
        String model, make, engine, color;

        // try-with-resources -> a way to initialize a resource that will then be closed after we're done with it
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"Dealership\".car where id = ?";



            PreparedStatement ps = conn.prepareStatement(sql); // Setting up our SQL statement in this way helps prevent SQL Injection Attacks

            ps.setInt(1, Id); // parameter Indexes start from 1 (NOT 0)

            ResultSet rs = ps.executeQuery();


                    while(rs.next()) {
                        id = (rs.getInt("id"));
                        year = (rs.getInt("year"));
                        make = (rs.getString("make"));
                        model = (rs.getString("model"));
                        engine = (rs.getString("engine"));
                        color = (rs.getString("color"));
                        miles = (rs.getInt("miles"));
                        price = (rs.getInt("price"));
                        boolean sold = (rs.getBoolean("sold"));
                        Car cars = new Car( year, make, model, color, engine, miles, price, id,sold);

                        return cars;
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return null;
    }


    @Override
    public List<Car> getAll() {

        List<Car> Cars = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"Dealership\".car order by id asc;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cars.add(new Car(
                        rs.getInt("year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("engine"),
                        rs.getString("color"),
                        rs.getInt("miles"),
                        rs.getInt("price"),
                        rs.getInt("id"),
                        rs.getBoolean("sold")
                ));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Cars;
    }


    // Create
    @Override
    public Boolean add(Car a) {

        try (Connection conn = cu.getConnection()) {

            String sql = "insert into \"Dealership\".car (id, year, make, model, color," +
                    " engine, miles, price, sold) values (default, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, a.getYear());
            ps.setString(2, a.getMake());
            ps.setString(3, a.getModel());
            ps.setString(4, a.getColor());
            ps.setString(5, a.getEngine());
            ps.setInt(6, a.getMiles());
            ps.setInt(7,a.getPrice());
            ps.setBoolean(8, a.isSold());

            return ps.executeUpdate() != 0;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Update - this will eventually become a PUT Http Request
    @Override
    public Boolean update(Car car) {
        try (Connection conn = cu.getConnection()) {
       //determines if the car is being sold or the price is being changed
        if(car.isSold()) {
            String sql2 = "update \"Dealership\".car set price = ?, sold = ? where id = ?";

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, car.getPrice());
            ps2.setBoolean(2, car.isSold());
            ps2.setInt(3, car.getId());

            return ps2.executeUpdate() != 0;
        }
        else if (car.isSold() == false){
            String sql2 = "update \"Dealership\".car set price = ? where id = ?";

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, car.getPrice());

            ps2.setInt(2, car.getId());

            return ps2.executeUpdate() != 0;

        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete
    @Override
    public Boolean delete(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from \"Dealership\".car where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() != 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
