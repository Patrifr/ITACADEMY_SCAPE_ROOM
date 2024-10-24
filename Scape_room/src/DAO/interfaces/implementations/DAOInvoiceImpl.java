package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.InvoiceDAO;
import classes.Invoice;
import classes.customer.Customer;
import utils.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOInvoiceImpl extends ConnectionDB implements InvoiceDAO {

    //a cadascun d'aquests mètodes es gestionen la connexió i els statements
    //Recuperar precios Invoice = precio ganancias.
    //Precio decoItem = 10€ (PRECIO ADICIONAL)
    //Precio clues = 5€ (PRECIO ADICIONAL)

    @Override
    public void add(Invoice invoice) {

        ConnectionDB connection = new ConnectionDB();
        String sql = "INSERT INTO invoice (id, customer_id, total_price) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1,invoice.getId());
            stmt.setString(2, invoice.getCustomer().getId());
            stmt.setDouble(3, invoice.getTotalPrice());
            stmt.executeUpdate();
            System.out.println("Invoice successfully created.");
        } catch (SQLException e) {
            System.out.println("Error inserting the invoice to the database: " + e.getMessage());
        } /*finally {
            connection.closeConnection();
        }*/

    }

    @Override
    public List<Invoice> showData() { //ShowTotalProfits
        ConnectionDB connection = new ConnectionDB();
        List<Invoice> invoices = null;
        try (PreparedStatement stmt = connection.getConnection().prepareStatement("SELECT * FROM invoice")){
            invoices = new ArrayList<Invoice>();
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                //Customer customer = new Customer();
                String id = rs.getString("id");
                String customerId = rs.getString("customer_id");
                String totalPrice = rs.getString("total_price");
                invoices.add(new Invoice(id,customerId));//Como construimos un obj invoice?
            }

        } catch (SQLException e) {
            System.out.println("Error extracting the data: " + e.getMessage());
        } /*finally {
            connection.closeConnection();
        }*/

        return invoices;

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }
}
