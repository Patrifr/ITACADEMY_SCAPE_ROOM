package classes.items.creator;

import classes.items.Clue;
import classes.items.Item;
import enums.Category;
import utils.Helper;

public class ClueCreator extends ItemCreator {

    @Override
    public Item createItem() {
        String name = "";
        final double PRICE = 5d;
        int category = 0;
        Category chosenCategory = null;

        name = Helper.readString("Introduce the name of the clue:");
        do {
            category = Helper.readInt("Choose a category:\n"
                    + "1. Sensory"
                    + "\n2. Alphabetical"
                    + "\n3. Numerical"
                    + "\n4. Combined");
            if(category < 1 || category > 4) {
                System.out.println("Please, choose a valid option: 1, 2, 3, 4.");
            } else {
                chosenCategory = Category.findByValue(category);
            }
        } while (category < 1 || category > 4);

        return new Clue(name, PRICE, chosenCategory);
    }
}
