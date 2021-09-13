package dev.scarnati.repositories;

import dev.scarnati.model.Accounts;
import dev.scarnati.model.Car;
import dev.scarnati.model.Customer;
import dev.scarnati.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dev.scarnati.veiws.LoginMenu.Username;

public class CustomerRepo implements CrudInterface<Customer> {
    private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    @Override
    public Customer getById(Integer id) {


        // try-with-resources -> a way to initialize a resource that will then be closed after we're done with it
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"Dealership\".customer where id = ?";



            PreparedStatement ps = conn.prepareStatement(sql); // Setting up our SQL statement in this way helps prevent SQL Injection Attacks

            ps.setInt(1, id); // parameter Indexes start from 1 (NOT 0)

            ResultSet rs = ps.executeQuery();


                Customer customer = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
    return customer;
            }


        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Customer> getAll() {

        List<Customer> Customers = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"Dealership\".Customer";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer a = new Customer(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber")

                );

                Customers.add(a);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Customers;
    }


    // Create
    @Override
    public Boolean add(Customer a) {

        try (Connection conn = cu.getConnection()) {

            String sql = "insert into \"Dealership\".Customer(id, firstName, lastName, email, phoneNumber) values (default, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, a.getFirstName());
            ps.setString(2, a.getLastName());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getPhoneNumber());

            return ps.executeUpdate() != 0;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Update - this will eventually become a PUT Http Request
    @Override
    public Boolean update(Customer customer) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update \"Dealership\".Customer set carId = ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, customer.getCarId());
            ps.setInt(2, customer.getId());


            return ps.executeUpdate() != 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete
    @Override
    public Boolean delete(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from \"Dealership\".Customer where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() != 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean buyACar(Integer carId){
        try (Connection conn = cu.getConnection()) {

            String sql = "Update \"Dealership\".Customer set caId = ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, carId);

            ps.executeUpdate();


            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Customer getCustomerByUsername(String username){

        try (Connection conn = cu.getConnection()) {
            String sql = "select c.id, c.firstName, c.lastName, c.email, c.phoneNumber, c.carId from \"Dealership\".Accounts a inner join \"Dealership\".Customer c on c.email = a.email where a.username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }

}
