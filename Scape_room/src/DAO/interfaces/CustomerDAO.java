package DAO.interfaces;

import classes.customer.Customer;

public interface CustomerDAO extends DAO<Customer> {

    Customer findCustomerByEmail(String email);
    void updateCustomer(String email, String fieldName, String newValue);

}
