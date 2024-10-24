package management;

import DAO.interfaces.implementations.DAOCustomerImpl;
import classes.customer.Customer;
import classes.customer.NewsletterManager;
import utils.Helper;

import java.util.List;
import java.util.Scanner;

public class CustomerManager {

    //singleton
    private static CustomerManager instance;
    DAOCustomerImpl daoCustomer = new DAOCustomerImpl();

    private CustomerManager() {
    } //com que està buit, si cal s'elimina

    public static CustomerManager getInstance() {
        if (instance == null) {
            instance = new CustomerManager();
        }
        return instance;
    }

    public void createCustomer() {
        String id = "", name = "", email = "", phoneNumber = "";
        Customer newCustomer;
        DAOCustomerImpl daoCustomer;

        name = Helper.readString("Introduce the name of the customer: ");
        email = Helper.readEmail("Introduce the email of the customer: ");
        phoneNumber = Helper.readString("Introduce the phone number: ");
        newCustomer = new Customer(name,email,phoneNumber);

        daoCustomer = new DAOCustomerImpl();
        daoCustomer.add(newCustomer);

    }

    public void showCustomers(){
        List<Customer> customers = daoCustomer.showData();

        if (customers.isEmpty()) {
            throw new IllegalStateException("There's no customers in the data base.");
        }

        System.out.println("List of customers:");
        for (Customer customer : customers) {
            System.out.println("ID" + customer.getId()
                    + "Name: " + customer.getName()
                    + " Email: " + customer.getEmail()
                    + "Phone Number: " + customer.getPhoneNumber() + "\n");
        }

    }

    public void customerMenu(){
        showCustomers();

        String customerName = Helper.readString("Introduce the name of the customer for the managment: ");
        Customer customer = daoCustomer.findCustomerByName(customerName);
        int opt;
        NewsletterManager newsletterManager = new NewsletterManager();

        if (customer == null) {
            System.out.println("Customer not found.");
        }else {

            do {
                opt = Helper.readInt("1. Add customer to the newsletter. \n" +
                        "2. Unsubscribe customer of the newsletter. \n" +
                        "3. Send newsletter. \n" +
                        "4. Diploma expedition. \n" +
                        "5. Give a gift \n" +
                        "0. Exit.");
                switch (opt) {
                    case 1:
                        newsletterManager.subscribe(customer);
                        break;
                    case 2:
                        newsletterManager.unsubscribe(customer);
                        break;
                    case 3:
                        String content = Helper.readString("Write the content of the newsletter: ");
                        newsletterManager.sendNewsletter(content);
                        break;
                    case 4:
                        String certificate = Helper.readString("Write the name of the Scape room for the certificate: ");
                        customer.setCertificate(certificate);
                        customer.getCertificate();
                        break;
                    case 5:
                        String gift = Helper.readString("Write the name of the gift: ");
                        customer.setGifts(gift);
                        customer.getGifts();
                        break;
                    default:
                        System.out.println("Please, select a valid option of the menu.");
                }
            } while (opt != 0);
            {
                System.out.println("You logged out of customer menú.");
            }
        }
    }
}
