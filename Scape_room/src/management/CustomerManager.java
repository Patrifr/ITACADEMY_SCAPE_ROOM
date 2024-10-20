package management;

public class CustomerManager {

    //singleton
    private static CustomerManager instance;

    public CustomerManager() {
    } //com que està buit, si cal s'elimina

    public static CustomerManager getInstance() {
        if (instance == null) {
            instance = new CustomerManager();
        }
        return instance;
    }
}
