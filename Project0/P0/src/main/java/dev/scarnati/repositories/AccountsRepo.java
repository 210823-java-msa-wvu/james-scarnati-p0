package dev.scarnati.repositories;


import dev.scarnati.model.Accounts;



import dev.scarnati.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsRepo implements CrudInterface<Accounts> {

    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    @Override
    public Accounts getById(Integer id) {


        // try-with-resources -> a way to initialize a resource that will then be closed after we're done with it
        try (Connection conn = cu.getConnection()) {
            String sql = "select * from \"Dealership\".Accounts where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql); // Setting up our SQL statement in this way helps prevent SQL Injection Attacks
            ps.setInt(1, id); // parameter Indexes start from 1 (NOT 0)

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Accounts(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Accounts> getAll() {

        List<Accounts> accounts = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"Dealership\".Accounts";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                accounts.add(new Accounts(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getString("email")

                ));


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }


    // Create
    @Override
    public Boolean add(Accounts a) {

        try (Connection conn = cu.getConnection()) {

            String sql = "insert into \"Dealership\".Accounts(id, username, pass, email) values (default, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, a.getUsername());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getEmail());


            return ps.executeUpdate() != 0;


        } catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    // Update - this will eventually become a PUT Http Request
    @Override
    public Boolean update(Accounts account) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update \"Dealership\".Accounts set pass = ?  where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, account.getPassword());
            ps.setInt(2, account.getId());

            return ps.executeUpdate() != 0;



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Accounts getByUsername(String username)  {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"Dealership\".Accounts where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Accounts(
                        rs.getString("username"),
                        rs.getString("pass")
                );
            }

        } catch (SQLException e) {
           e.printStackTrace();
        }
        return null;
    }
    public Accounts getAccountByUsername(String username) throws SQLException {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"Dealership\".Accounts where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Accounts(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }

        }
        return null;
    }

    public Accounts getAccountByEmail(String email) {
        try (Connection conn = cu.getConnection()) {

            String sql = "select * from \"Dealership\".Accounts where email = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Accounts(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }

        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Accounts checkByUsernameCustomer(String username) {


        Accounts C = new Accounts();

        try (Connection conn = cu.getConnection()) {
            String sql = "select a.email, c.email from \"Dealership\".Accounts a inner join \"Dealership\".Customer c on c.email = a.email where a.username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Accounts(
                        rs.getString(1),
                        rs.getString(2)
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Accounts checkByUsernameSales(String username) {



        try (Connection conn = cu.getConnection()) {
            String sql = "select a.username, a.pass, e.email, e.pos from \"Dealership\".Accounts a inner join \"Dealership\".Employee e on e.email = a.email where a.username = ? and e.pos = 'Sales'";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Accounts(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Accounts checkByUsernameManagement(String username) {



        try (Connection conn = cu.getConnection()) {
            String sql = "select a.username, a.pass, e.email, e.pos from \"Dealership\".Accounts a inner join \"Dealership\".Employee e on e.email = a.email where a.username = ? and e.pos = 'Manager'";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Accounts(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
            return null;
}






    // Delete
    @Override
    public Boolean delete(Integer id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "delete from \"Dealership\".Accounts where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);


            return ps.executeUpdate() != 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
