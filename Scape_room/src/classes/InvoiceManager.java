package classes;

public class InvoiceManager {

    //private Invoice invoice;
    private static InvoiceManager instance;

    private InvoiceManager(){
    }

    public static InvoiceManager getSingletonInstance() {
        if (instance == null){
            instance = new InvoiceManager();
        }
        else{
            System.out.println("----");
        }

        return instance;
    }
}
