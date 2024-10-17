package classes;

import classes.customer.Customer;

public class Invoice {

    private String id;
    private Customer customer;
    private double priceRooms;
    private double priceClues;
    private double priceDecoItems; //PriceItem
    private double IVA;
    private double totalPrice;

    public Invoice(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
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

    //Llamamos a getPrice de Clue
    public double getPriceClues() {
        return priceClues;
    }

    public void setPriceClues(double priceClues) {
        this.priceClues = priceClues;
    }

    //Llamamos a getPrice de DecoItems
    public double getPriceDecoItems() {
        return priceDecoItems;
    }

    public void setPriceDecoItems(double priceDecoItems) {
        this.priceDecoItems = priceDecoItems;
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
        totalPrice = priceRooms + priceClues + priceDecoItems;
        return totalPrice;
    }

    //Total price de la room?
    public void setTotalPrice(double totalPrice) {
        double finalPrice = totalPrice / 2; //Ejemplo calculo de precio a cobrar al cliente final.
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Invoice ticket: \n" +
                "Invoice id: " + id + '\n' +
                "Customer name: " + customer + '\n' +
                "Escape room price: " + priceRooms + '\n' +
                "IVA: " + IVA + '\n' +
                "Total price: " + totalPrice;
    }
}
