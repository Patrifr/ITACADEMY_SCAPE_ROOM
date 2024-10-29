package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.InvoiceDAO;
import classes.Invoice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOInvoiceImpl extends ConnectionDB implements InvoiceDAO {

    @Override
    public void add(Invoice invoice) {

        ConnectionDB connection = new ConnectionDB();
        String sql = "INSERT INTO invoice (id, customer_id, total_price) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1,invoice.getId());
            stmt.setString(2, invoice.getCustomerId());
            stmt.setDouble(3, invoice.getTotalPrice());
            stmt.executeUpdate();
            System.out.println("Invoice successfully created.");
        } catch (SQLException e) {
            System.out.println("Error inserting the invoice to the database: " + e.getMessage());
        }
    }

    @Override
    public List<Invoice> showData() {
        ConnectionDB connection = new ConnectionDB();
        List<Invoice> invoices = null;
        try (PreparedStatement stmt = connection.getConnection().prepareStatement("SELECT * FROM invoice")){
            invoices = new ArrayList<Invoice>();
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String id = rs.getString("id");
                String customerId = rs.getString("customer_id");
                double totalPrice = rs.getDouble("total_price");
                invoices.add(new Invoice(id, customerId, totalPrice));
            }

        } catch (SQLException e) {
            System.out.println("Error extracting the data: " + e.getMessage());
        }
        return invoices;
    }

}
