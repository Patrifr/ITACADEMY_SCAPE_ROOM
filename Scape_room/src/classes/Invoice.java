package classes;

import classes.customer.Customer;

public class Invoice {

    private String id;
    private String customerId;
    private Customer customer;
    private double priceRooms;
    private double IVA;
    private double totalPrice;

    //Que pasamos por contrsuctor??
    public Invoice(String id, String customerId) {
        this.id = id;
        this.customerId = customer.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //Llamamos a getPrice de room
    public double getPriceRooms() {
        return priceRooms;
    }

    public void setPriceRooms(double priceRooms) {
        this.priceRooms = priceRooms;
    }

    //IVA del 21%?
    public double getIVA() {
        IVA = 0.21;
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    //Pensar método para calcular precio total para cliente.
    public double getTotalPrice() {
        double finalPrice = totalPrice * 0.21; //Calculo con el IVA
        this.totalPrice = finalPrice;//???
        return finalPrice;
    }

    //Total price de la room?
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Invoice ticket: \n" +
                "Invoice id: " + id + '\n' +
                "Customer name: " + customer + '\n' +
                "Escape room price: " + priceRooms + '\n' +
                "IVA: " + IVA + '\n' +
                "Total price: " + getTotalPrice();
    }
}
