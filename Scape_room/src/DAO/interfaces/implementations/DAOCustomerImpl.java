package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.CustomerDAO;
import classes.customer.Customer;

import java.util.List;

public class DAOCustomerImpl extends ConnectionDB implements CustomerDAO {

    //a cadascun d'aquests mètodes es gestionen la connexió i els statements

    @Override
    public void add(Customer newCustomer) {

    }

    @Override
    public List<Customer> showData() {
        List<Customer> customers = null;
        return customers;

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }
}
