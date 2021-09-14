package dev.scarnati.repositories;


import dev.scarnati.model.Employee;
import dev.scarnati.service.exceptions.InvalidSelectionException;
import dev.scarnati.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo implements CrudInterface<Employee> {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
    @Override
    public Employee getById(Integer id) {


        // try-with-resources -> a way to initialize a resource that will then be closed after we're done with it
        try (Connection conn = cu.getConnection()) {
            String sql = "select * from \"Dealership\".Employee where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql); // Setting up our SQL statement in this way helps prevent SQL Injection Attacks
            ps.setInt(1, id); // parameter Indexes start from 1 (NOT 0)

            ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Employee employee = new Employee(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)

            );
            return employee;
        }

        } catch ( SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Employee> getAll() {

        List<Employee> Employees = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"Dealership\".Employee order by id asc";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee a = new Employee(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("pos")
                );

                Employees.add(a);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Employees;
    }


    // Create
    @Override
    public Boolean add(Employee employee) {

        try (Connection conn = cu.getConnection()) {

            String sql = "insert into \"Dealership\".Employee values (default, ?, ?, ?, ?, ?) ";

            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getPhoneNumber());
            ps.setString(5, employee.getTitle());

            return ps.executeUpdate() != 0;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Update - this will eventually become a PUT Http Request
    @Override
    public Boolean update(Employee employee) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update \"Dealership\".Employee set phoneNumber = ?, lastName = ? where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getPhoneNumber());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getId());

            return ps.executeUpdate() != 0;

        }catch ( SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    // Delete
    @Override
    public Boolean delete(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from \"Dealership\".Employee where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() != 0;
        }catch ( SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

}


