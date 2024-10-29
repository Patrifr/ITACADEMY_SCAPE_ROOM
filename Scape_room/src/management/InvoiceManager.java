package management;

import DAO.interfaces.implementations.DAOCustomerImpl;
import DAO.interfaces.implementations.DAOInvoiceImpl;
import DAO.interfaces.implementations.DAORoomImpl;
import classes.Invoice;
import classes.Room;
import classes.customer.Customer;
import exceptions.NoRoomsException;
import utils.Helper;

import java.util.List;

public class InvoiceManager {

    private static InvoiceManager instance;
    private DAOInvoiceImpl daoInvoice;
    private DAOCustomerImpl daoCustomer;
    private DAORoomImpl daoRoom;

    private InvoiceManager(){
        this.daoInvoice = new DAOInvoiceImpl();
        this.daoCustomer = new DAOCustomerImpl();
        this.daoRoom = new DAORoomImpl();
    }

    public static InvoiceManager getInstance() {
        if (instance == null){
            instance = new InvoiceManager();
        }
        return instance;
    }

    public void createInvoice(){
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

    public void setInvoice()  {
        String customerEmail = Helper.readEmail("Please, enter the email of the customer to make the invoice: ");
        Customer selectedCustomer = daoCustomer.findCustomerByEmail(customerEmail);
        if (selectedCustomer == null) {
            System.out.println("Customer not found.");
        } else {
            roomInvoice(selectedCustomer);
        }
    }

    public void roomInvoice(Customer selectedCustomer){
        Invoice invoice;
        String answer;
        do {
            answer = Helper.readString("Do you want to see the room list? (YES/NO)");
            if (answer.equalsIgnoreCase("YES")) {
                    try{
                       showRooms();
                        Room room = daoRoom.findRoom();
                        invoice = new Invoice(selectedCustomer.getId(), room.getPrice());
                        daoInvoice.add(invoice);
                        System.out.println("Invoice :\n" + invoice);
                    }catch (NoRoomsException e){
                        System.out.println(e.getMessage());
                    }
            } else if (answer.equalsIgnoreCase("NO")) {
                try{
                    Room room = daoRoom.findRoom();
                    invoice = new Invoice(selectedCustomer.getId(), room.getPrice());
                    daoInvoice.add(invoice);
                    System.out.println("Invoice :\n" + invoice);
                }catch (NoRoomsException e){
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Please, write YES / NO.");
            }
        } while (!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No"));
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


    public void showRooms() throws NoRoomsException {
        List<Room> listedRooms = daoRoom.showData();

        if(listedRooms.isEmpty()) {
            throw new NoRoomsException("There are no registered rooms.");
        }

        System.out.println("Registered rooms:");
        listedRooms.stream().map(r -> "ID: " + r.getId() +
                ", Name: " + r.getName() +
                ", Level: " + r.getLevel().getLevelName() +
                ", Theme: " + r.getTheme().getThemeName() +
                ", Completion time: " + r.getCompletionTime() +
                ", Price: " + r.getPrice()).forEach(System.out::println);
    }

}
