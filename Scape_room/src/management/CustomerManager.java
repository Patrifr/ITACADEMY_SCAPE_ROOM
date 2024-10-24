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

        name = Helper.readString("Introduce the name of the customer:");
        email = Helper.readString("Introduce the email of the customer:");
        phoneNumber = Helper.readString("Introduce the phone number:");
        newCustomer = new Customer(name,email,phoneNumber);

        daoCustomer = new DAOCustomerImpl();
        daoCustomer.add(newCustomer);

        //Per cridar el métode: - S'ha de posar a ScapeRoom?
        //public void newCustomer() {
            //CustomerManager manager = CustomerManager.getInstance();
            //        manager.createCustomer();
       // }

    }

    public void showCustomers(){
        //Imprimir la lista de los clientes y después permitir seleccionarlo?
        DAOCustomerImpl daoCustomer = new DAOCustomerImpl();

        // Obtener la lista de clientes
        List<Customer> customers = daoCustomer.showData();

        if (customers.isEmpty()) {
            throw new IllegalStateException("There's no customers in the data base.");
        }

        // Imprimir todos los clientes
        System.out.println("List of customers:");
        for (Customer customer : customers) {
            System.out.println("ID" + customer.getId()
                    + "Name: " + customer.getName()
                    + " Email: " + customer.getEmail()
                    + "Phone Number: " + customer.getPhoneNumber() + "\n");
        }

        //Seleccionar customer por nombre con menú de acciones:
        String customerName = Helper.readString("Introduce the name of the customer: ");
        Customer customer = daoCustomer.findCustomerByName(customerName);
        NewsletterManager newsletterManager = new NewsletterManager();

        if (customer == null) {
            System.out.println("Customer not found.");
        }else{
            //Añadir bucle do while?
            int opt = Helper.readInt("1. Add customer to the newsletter. \n" +
                    "2. Unsubscribe customer of the newsletter. \n" +
                    "3. Send newsletter" +
                    "4. Diploma expedition. \n" +
                    "5. Create an invoice");
            //FALTA OPCIÓN GIFTS.
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
                    //Como haremos las facturas?
                    break;
                default:
                    System.out.println("Please, select a valid option of the menu.");
            }
        }

    }
}
