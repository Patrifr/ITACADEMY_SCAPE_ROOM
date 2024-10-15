package classes.items.creator;

import classes.items.Clue;
import classes.items.Item;
import enums.Category;

public class ClueCreator extends ItemCreator {

    //aprofitar els creators per demanar les dades a l'usuari??

    @Override
    public Item createItem() {
        String name = "";
        double price = 0d;
        String estimatedTime = "";
        Category category = null;
        return new Clue(name, price, estimatedTime, category);
    }
}
