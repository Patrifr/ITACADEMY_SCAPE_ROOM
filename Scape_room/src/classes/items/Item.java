package classes.items;

import java.util.UUID;

public abstract class Item {

    private String id;
    private String name;
    private double price;
    private boolean enabled;
    private boolean available;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.enabled = true; //comença true per defecte
        this.available = true; //comença true per defecte
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public abstract void commonMethod();



}
