package classes.items.creator;

import classes.items.DecoItem;
import classes.items.Item;
import enums.Material;
import utils.Helper;

public class DecoItemCreator extends ItemCreator {

    @Override
    public Item createItem() {
        String name = "";
        double price = 0d;
        int material = 0, quantity = 0;
        Material chosenMaterial = null;
        //cal tota la prèvia???

        name = Helper.readString("Introduce the name of the decoration item:");
        price = Helper.readDouble("Introduce the price of the decoration item:");
        quantity = Helper.readInt("Introduce the total quantity of clues:");
        material = Helper.readInt("Choose a material:\n"
                + "1. Wood"
                + "\n2. Glass"
                + "\n3. Plastic"
                + "\n4. Metal");
        chosenMaterial = Material.findByValue(material);

        return new DecoItem(name, price, quantity, chosenMaterial);
    }
}
