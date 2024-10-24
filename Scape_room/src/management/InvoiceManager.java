package management;

import DAO.interfaces.implementations.DAOCustomerImpl;
import DAO.interfaces.implementations.DAOInvoiceImpl;
import classes.Invoice;

public class InvoiceManager {

    //private Invoice invoice;
    private static InvoiceManager instance;
    private DAOInvoiceImpl daoInvoice;
    private DAOCustomerImpl daoCustomer;

    private InvoiceManager(){
        this.daoInvoice = new DAOInvoiceImpl();
        this.daoCustomer = new DAOCustomerImpl();
    } //com que està buit, si cal s'elimina

    public static InvoiceManager getInstance() {
        if (instance == null){
            instance = new InvoiceManager();
        }
        else{
            System.out.println("----");
        }

        return instance;
    }

    public void createInvoice(){
        String id = "",customerId = "";
        double priceRoom = 0, IVA, totalPrice;
        Invoice invoice;
         //Como podemos instanciar el metodo enseñar y mostrar customers?

    }
    //Invoice Customers
    //Método para buscar customers y así asignarles la invoice.
    //Método para obtener todos los totalPrice de las invoices y sumarlos (totalPrice).
}
