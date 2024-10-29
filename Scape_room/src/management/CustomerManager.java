package management;

import DAO.interfaces.implementations.DAOCustomerImpl;
import classes.customer.Customer;
import classes.customer.NewsletterManager;
import utils.Helper;
import java.util.List;

public class CustomerManager {

    private static CustomerManager instance;
    DAOCustomerImpl daoCustomer = new DAOCustomerImpl();
    NewsletterManager newsletterManager = new NewsletterManager();

    private CustomerManager() {
    }

    public static CustomerManager getInstance() {
        if (instance == null) {
            instance = new CustomerManager();
        }
        return instance;
    }

    public void createCustomer() {
        String name = "", surname = "", email = "", phoneNumber = "";
        Customer newCustomer;
        DAOCustomerImpl daoCustomer;

        name = Helper.readString("Introduce the name of the customer: ");
        surname = Helper.readString("Introduce the surname of the customer: ");
        email = Helper.readEmail("Introduce the email of the customer: ");
        phoneNumber = Helper.readString("Introduce the phone number: ");
        newCustomer = new Customer(name, surname, email, phoneNumber);

        daoCustomer = new DAOCustomerImpl();
        daoCustomer.add(newCustomer);

    }

    public void showCustomers() {
        List<Customer> customers = daoCustomer.showData();

        if (customers.isEmpty()) {
            throw new IllegalStateException("There's no customers in the data base.");
        }

        System.out.println("List of customers: ");
        for (Customer customer : customers) {
            System.out.println("ID: " + customer.getId()
                    + " Name: " + customer.getName()
                    + " Surname: " + customer.getSurname()
                    + " Email: " + customer.getEmail()
                    + " Phone Number: " + customer.getPhoneNumber() + "\n");
        }

    }

    private void modifyData(Customer customer){
        int opt;
        String newValue;
        String originalEmail = customer.getEmail();

        do {
            opt = Helper.readInt("What data do you want to modify? \n" +
                    "1. Name \n" +
                    "2. Surname \n" +
                    "3. Email \n" +
                    "4. Phone Number \n" +
                    "0. Exit.");

            switch (opt) {
                case 0:
                    System.out.println("Logging out ouf modify data.");
                    break;
                case 1:
                    newValue = Helper.readString("Introduce the new name: ");
                    customer.setName(newValue);
                    daoCustomer.updateCustomer(originalEmail, "customer_name", newValue);
                    break;
                case 2:
                    newValue = Helper.readString("Introduce the new surname: ");
                    customer.setSurname(newValue);
                    daoCustomer.updateCustomer(originalEmail, "customer_surname", newValue);
                    break;
                case 3:
                    newValue = Helper.readEmail("Introduce the new email: ");
                    daoCustomer.updateCustomer(originalEmail, "email", newValue);
                    customer.setEmail(newValue);
                    break;
                case 4:
                    newValue = Helper.readString("Introduce the new phone number: ");
                    customer.setPhoneNumber(newValue);
                    daoCustomer.updateCustomer(originalEmail, "phone", newValue);
                    break;

            }
        }while (opt != 0);
    }

    private void menu() {
        int opt;
        String customerEmail = Helper.readEmail("Introduce the email of the customer for the management: ");
        Customer customer = daoCustomer.findCustomerByEmail(customerEmail);
        if (customer == null) {
            System.out.println("Customer not found.");
        } else {
            do {
                opt = Helper.readInt("1. Add customer to the newsletter. \n" +
                        "2. Unsubscribe customer of the newsletter. \n" +
                        "3. Send newsletter. \n" +
                        "4. Diploma expedition. \n" +
                        "5. Give a gift \n" +
                        "6. Modify customer data \n" +
                        "0. Exit.");
                switch (opt) {
                    case 0:
                        System.out.println("You logged out of customer menú.");
                        break;
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
                        System.out.println(customer.getCertificate());
                        break;
                    case 5:
                        String gift = Helper.readString("Write the name of the gift: ");
                        customer.setGifts(gift);
                        System.out.println(customer.getGifts());
                        break;
                    case 6:
                        modifyData(customer);
                        break;
                    default:
                        System.err.println("Please select an option of the menu.");
                }
            } while (opt != 0);
        }
    }

    public void customerMenu() {
        String answer;
        do {
            answer = Helper.readString("For the customer management, you need to introduce the email of the customer." +
                    "Do you want to see the list of customer to select one? (YES/NO)");
            if (answer.equalsIgnoreCase("YES")) {
                showCustomers();
                menu();
            } else if (answer.equalsIgnoreCase("NO")) {
                menu();
            }
        }while (!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No")) ;{
        }
    }
}

