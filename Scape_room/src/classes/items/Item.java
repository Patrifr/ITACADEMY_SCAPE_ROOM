package classes.items;

import java.util.UUID;

public abstract class Item {

    private String id;
    private String name;
    private double price;
    private boolean enabled;
    private boolean available;

    public Item (){
    }

    public Item(String name, double price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.enabled = true;
        this.available = true;
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

    public void setId(String id) {
        this.id = id;
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

}
