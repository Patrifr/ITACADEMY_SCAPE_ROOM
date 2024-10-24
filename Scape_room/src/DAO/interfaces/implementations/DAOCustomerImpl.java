package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.CustomerDAO;
import classes.customer.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCustomerImpl extends ConnectionDB implements CustomerDAO {

    @Override
    public void add(Customer newCustomer) {
        ConnectionDB connection = new ConnectionDB();
        String sql = "INSERT INTO customer (id, customer_name, email, phone) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            // Asignar valores a los parámetros
            stmt.setString(1,newCustomer.getId());
            stmt.setString(2, newCustomer.getName());
            stmt.setString(3, newCustomer.getEmail());
            stmt.setString(4,newCustomer.getPhoneNumber());
            stmt.executeUpdate();
            System.out.println("Customer successfully created.");
        } catch (SQLException e) {
            System.out.println("Error inserting the customer to the database: " + e.getMessage());
        } /*finally {
            connection.closeConnection();
        }*/

    }

    @Override
    public List<Customer> showData() {
        ConnectionDB connection = new ConnectionDB();
        List<Customer> customers = null;

        try (PreparedStatement stmt = connection.getConnection().prepareStatement("SELECT * FROM customer")){
            customers = new ArrayList<Customer>();
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                //Customer customer = new Customer();
                String id = rs.getString("id");
                String name = rs.getString("customer_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                customers.add(new Customer(name, email, phone));
            }

        } catch (SQLException e) {
            System.out.println("Error extracting the data: " + e.getMessage());
        } /*finally {
            connection.closeConnection();
        }*/
        return customers;

    }

    public Customer findCustomerByName(String name) {
        Customer customer = null;
        String sql = "SELECT id, customer_name, email, phone FROM customer WHERE customer_name = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("id");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    customer = new Customer(name, email, phone);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding customer: " + e.getMessage());
        }
        return customer;
    }

    @Override
    public void update() {
    //Si sobra tiempo modificar datos usuario
    }

    @Override
    public void remove() {

    }
}
