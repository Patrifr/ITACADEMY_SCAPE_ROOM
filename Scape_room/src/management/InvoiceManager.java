package management;

import DAO.interfaces.implementations.DAOCustomerImpl;
import DAO.interfaces.implementations.DAOInvoiceImpl;
import classes.Invoice;
import classes.customer.Customer;
import utils.Helper;

import java.util.List;

public class InvoiceManager {

    private static InvoiceManager instance;
    private DAOInvoiceImpl daoInvoice;
    private DAOCustomerImpl daoCustomer;

    private InvoiceManager(){
        this.daoInvoice = new DAOInvoiceImpl();
        this.daoCustomer = new DAOCustomerImpl();
    }

    public static InvoiceManager getInstance() {
        if (instance == null){
            instance = new InvoiceManager();
        }
        return instance;
    }

    public void createInvoice() {
        String answer;
        do {
            answer = Helper.readString("You must choose a customer and introduce the email to make an invoice. " +
                    "Do you want to see the list of the customers? (YES/NO)");
            if (answer.equalsIgnoreCase("Yes")) {
                System.out.println("Here is the list of customers: ");
                List<Customer> customers = daoCustomer.showData();
                if (customers.isEmpty()) {
                    System.out.println("There's no customers in the list.");
                } else {
                    System.out.println("Customers:");
                    for (Customer customer : customers) {
                        System.out.println("ID: " + customer.getId() + "  Name: " + customer.getName()
                                + " Email: " + customer.getEmail() + " Phone:" + customer.getPhoneNumber() + "\n");
                    }
                    setInvoice();
                }
            } else if (answer.equalsIgnoreCase("No")) {
                setInvoice();
            }
            else{
                System.out.println("Please, try again writing 'yes' or 'no'.");
            }
        } while (!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No"));{
        }

    }

    public void setInvoice(){
        Invoice invoice;
        String customerEmail = Helper.readEmail("Please, enter the email of the customer to make the invoice: ");
        Customer selectedCustomer = daoCustomer.findCustomerByEmail(customerEmail);
        if (selectedCustomer == null) {
            System.out.println("Customer not found.");
        } else {
            double priceRooms = Helper.readDouble("Please introduce the price of the escape room: ");
            invoice = new Invoice(selectedCustomer.getId(), priceRooms);
            daoInvoice.add(invoice);
            System.out.println("Invoice :\n" + invoice);
        }
    }

    public void calculateTotalProfits() {
        List<Invoice> invoices = daoInvoice.showData();
        double totalProfits = 0;

        for (Invoice invoice : invoices) {
            totalProfits += invoice.getTotalPrice();
        }

        System.out.println("Total profits: " + totalProfits + " €.");
    }

    public void invoiceMenu(){
        int opt;
        opt = Helper.readInt("Accounts management menu: \n" +
                "1. Create a new invoice/ticket to a customer. \n" +
                "2. Calculate total profits.");

        switch (opt){
            case 1:
                createInvoice();
                break;
            case 2:
                calculateTotalProfits();
                break;
        }
    }

}
