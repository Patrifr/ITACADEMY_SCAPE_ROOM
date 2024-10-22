package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.CustomerDAO;
import classes.customer.Customer;

public class DAOCustomerImpl extends ConnectionDB implements CustomerDAO {

    //a cadascun d'aquests mètodes es gestionen la connexió i els statements

    @Override
    public void add(Customer newCustomer) {

    }

    @Override
    public void showData() {

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }
}
