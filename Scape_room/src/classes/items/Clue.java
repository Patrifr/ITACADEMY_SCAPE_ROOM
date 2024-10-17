package classes.items;

import enums.Category;

public class Clue extends Item {

    private String estimatedTime;
    private Category category;

    public Clue(String name, double price, int quantity, String estimatedTime, Category category) {
        super(name, price, quantity);
        //this.addIva();
        this.estimatedTime = estimatedTime;
        this.category = category;
    }

    public String getEstimatedTime() {
        return this.estimatedTime;
    }

    public Category getCategory() {
        return category;
    }

    //cal??
    /*private double addIva() {
        final double IVA = 0.10;
        super.setPrice((super.getPrice() * IVA) + super.getPrice());
        return super.getPrice();
    }*/

    //què fem amb aquest method??
    @Override
    public void commonMethod() {

    }

    //cal?? falten els atributs de super
    @Override
    public String toString() {
        return "Clue{" +
                "estimatedTime='" + estimatedTime + '\'' +
                ", category=" + category +
                '}';
    }
}
