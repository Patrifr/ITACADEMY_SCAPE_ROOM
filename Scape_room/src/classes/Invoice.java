package classes;

import java.util.UUID;

public class Invoice {
    private String id;
    private String customerId;
    private double priceRooms;
    private double IVA = 0.21;
    private double totalPrice;

    public Invoice(String customerID, double priceRooms) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerID;
        this.priceRooms = priceRooms;
        this.totalPrice = calculateTotalPrice();
    }

    public Invoice(String id, String customerID, double totalPrice) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerID;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public double getPriceRooms() {
        return priceRooms;
    }

    public void setPriceRooms(double priceRooms) {
        this.priceRooms = priceRooms;
    }

    public double getIVA() {
        return IVA;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateTotalPrice() {
        this.totalPrice = this.priceRooms + (this.priceRooms * this.IVA);
        return this.totalPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Invoice id nº: " + id + '\n' +
                "Escape room price: " + priceRooms + '\n' +
                "IVA: " + IVA + '\n' +
                "Total price: " + totalPrice;
    }
}
