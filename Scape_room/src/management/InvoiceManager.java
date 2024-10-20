package management;

public class InvoiceManager {

    //private Invoice invoice;
    private static InvoiceManager instance;

    private InvoiceManager(){
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
}
