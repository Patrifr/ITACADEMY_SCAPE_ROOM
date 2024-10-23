package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.InvoiceDAO;
import classes.Invoice;
import classes.customer.Customer;

import java.util.List;

public class DAOInvoiceImpl extends ConnectionDB implements InvoiceDAO {

    //a cadascun d'aquests mètodes es gestionen la connexió i els statements

    @Override
    public void add(Invoice invoice) {

    }

    @Override
    public List<Invoice> showData() {
        List<Invoice> invoices = null;
        return invoices;

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }
}
