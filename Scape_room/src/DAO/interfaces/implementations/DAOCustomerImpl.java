package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.CustomerDAO;
import classes.customer.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAOCustomerImpl extends ConnectionDB implements CustomerDAO {

    @Override
    public void add(Customer newCustomer) {
        ConnectionDB connection = new ConnectionDB();
        String sql = "INSERT INTO customer (id, customer_name, customer_surname, email, phone) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            // Asignar valores a los parámetros
            stmt.setString(1,newCustomer.getId());
            stmt.setString(2, newCustomer.getName());
            stmt.setString(3, newCustomer.getSurname());
            stmt.setString(4, newCustomer.getEmail());
            stmt.setString(5,newCustomer.getPhoneNumber());
            stmt.executeUpdate();
            System.out.println("Customer successfully created.");
        } catch (SQLException e) {
            System.out.println("Error inserting the customer to the database: " + e.getMessage());
        }
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
                String surname = rs.getString("customer_surname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                customers.add(new Customer(name, surname, email, phone));
            }

        } catch (SQLException e) {
            System.out.println("Error extracting the data: " + e.getMessage());
        }
        return customers;

    }

    @Override
    public void update() {

    }

    public Customer findCustomerByEmail(String email) {
        Customer customer = null;
        String sql = "SELECT id, customer_name, customer_surname, email, phone FROM customer WHERE email = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setId(rs.getString("id"));
                    customer.setName(rs.getString("customer_name"));
                    customer.setSurname(rs.getString("customer_surname"));
                    customer.setEmail( rs.getString("email"));
                    customer.setPhoneNumber(rs.getString("phone"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding customer: " + e.getMessage());
        }
        return customer;
    }

    public void updateCustomer(String email, String fieldName, String newValue) {
        List<String> allowedFields = Arrays.asList("customer_name", "customer_surname", "email", "phone");
        if (!allowedFields.contains(fieldName)) {
            throw new IllegalArgumentException("Invalid field name for update.");
        }

        String sql = "UPDATE customer SET " + fieldName + " = ? WHERE email = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newValue);
            stmt.setString(2, email);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("No customer found with the given email.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating customer " + fieldName + ": " + e.getMessage());
        }
    }

    @Override
    public void remove() {

    }
}
