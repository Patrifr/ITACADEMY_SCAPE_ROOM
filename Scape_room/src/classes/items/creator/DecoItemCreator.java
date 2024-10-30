package classes.items.creator;

import classes.items.DecoItem;
import classes.items.Item;
import enums.Material;
import utils.Helper;

public class DecoItemCreator extends ItemCreator {

    @Override
    public Item createItem() {
        String name = "";
        final double PRICE = 12.99d;
        int material = 0;
        Material chosenMaterial = null;

        name = Helper.readString("Introduce the name of the decoration item:");
        do {
            material = Helper.readInt("Choose a material:\n"
                    + "1. Wood"
                    + "\n2. Glass"
                    + "\n3. Plastic"
                    + "\n4. Metal");
            if(material < 1 || material > 4) {
                System.out.println("Please, choose a valid option: 1, 2, 3, 4.");
            } else {
                chosenMaterial = Material.findByValue(material);
            }
        } while (material < 1 || material > 4);

        return new DecoItem(name, PRICE, chosenMaterial);
    }
}
