package classes.items.creator;

import classes.items.DecoItem;
import classes.items.Item;
import enums.Material;

public class DecoItemCreator extends ItemCreator {

    //aprofitar els creators per demanar les dades a l'usuari??

    @Override
    public Item createItem() {
        String name = "";
        double price = 0d;
        Material material = null;
        return new DecoItem(name, price, material);
    }
}
