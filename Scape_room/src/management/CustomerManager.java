package management;

import DAO.interfaces.implementations.DAOCustomerImpl;
import classes.customer.Customer;
import classes.customer.NewsletterManager;
import utils.Helper;
import java.util.List;

public class CustomerManager {

    private static CustomerManager instance;
    private final DAOCustomerImpl daoCustomer = new DAOCustomerImpl();
    private final NewsletterManager newsletterManager = new NewsletterManager();

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

        name = Helper.readString("Introduce the name of the customer: ");
        surname = Helper.readString("Introduce the surname of the customer: ");
        email = Helper.readEmail("Introduce the email of the customer: ");
        phoneNumber = Helper.readString("Introduce the phone number: ");
        newCustomer = new Customer(name, surname, email, phoneNumber);

        this.daoCustomer.add(newCustomer);
    }

    public void customerMenu() {
        String answer;
        do {
            answer = Helper.readString("For the customer management, you need to introduce the email of the customer." +
                    " Do you want to see the list of customer to select one? (YES/NO)");
            if (answer.equalsIgnoreCase("YES")) {
                this.showCustomers();
                this.menu();
            } else if (answer.equalsIgnoreCase("NO")) {
                this.menu();
            }
        }while (!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No")) ;{
        }
    }

    private void showCustomers() {
        List<Customer> customers = this.daoCustomer.showData();

        if (customers.isEmpty()) {
            throw new IllegalStateException("There are no customers in the database.");
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

    private void menu() {
        int opt;
        String customerEmail = Helper.readEmail("Introduce the email of the customer for the management: ");
        Customer customer = this.daoCustomer.findCustomerByEmail(customerEmail);
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
                        System.out.println("You logged out of customer menu.");
                        break;
                    case 1:
                        this.newsletterManager.subscribe(customer);
                        break;
                    case 2:
                        this.newsletterManager.unsubscribe(customer);
                        break;
                    case 3:
                        String content = Helper.readString("Write the content of the newsletter: ");
                        this.newsletterManager.sendNewsletter(content);
                        break;
                    case 4:
                        String certificate = Helper.readString("Write the name of the room for the certificate: ");
                        customer.setCertificate(certificate);
                        System.out.println(customer.getCertificate());
                        break;
                    case 5:
                        String gift = Helper.readString("Write the name of the gift: ");
                        customer.setGifts(gift);
                        System.out.println(customer.getGifts());
                        break;
                    case 6:
                        this.modifyData(customer);
                        break;
                    default:
                        System.out.println("Please select an option of the menu.");
                }
            } while (opt != 0);
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
                    this.daoCustomer.updateCustomer(originalEmail, "customer_name", newValue);
                    break;
                case 2:
                    newValue = Helper.readString("Introduce the new surname: ");
                    customer.setSurname(newValue);
                    this.daoCustomer.updateCustomer(originalEmail, "customer_surname", newValue);
                    break;
                case 3:
                    newValue = Helper.readEmail("Introduce the new email: ");
                    this.daoCustomer.updateCustomer(originalEmail, "email", newValue);
                    customer.setEmail(newValue);
                    break;
                case 4:
                    newValue = Helper.readString("Introduce the new phone number: ");
                    customer.setPhoneNumber(newValue);
                    this.daoCustomer.updateCustomer(originalEmail, "phone", newValue);
                    break;

            }
        }while (opt != 0);
    }


}

