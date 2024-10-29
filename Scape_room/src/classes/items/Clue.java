package classes.items;

import enums.Category;

public class Clue extends Item {

    private Category category;

    public Clue(){
    }

    public Clue(String name, double price, Category category) {
        super(name, price);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Clue{" +
                "category=" + category +
                '}';
    }
}
