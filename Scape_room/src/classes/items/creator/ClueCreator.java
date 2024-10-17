package classes.items.creator;

import classes.items.Clue;
import classes.items.Item;
import enums.Category;
import utils.Helper;

public class ClueCreator extends ItemCreator {

    @Override
    public Item createItem() {
        String name = "", estimatedTime = "";
        double price = 0d;
        int category = 0, quantity = 0;
        Category chosenCategory = null;
        //cal tota la prèvia???

        name = Helper.readString("Introduce the name of the clue:");
        price = Helper.readDouble("Introduce the price of the clue:");
        quantity = Helper.readInt("Introduce the total quantity of clues:");
        category = Helper.readInt("Choose a category:\n"
                + "1. Sensory"
                + "\n2. Alphabetical"
                + "\n3. Numerical"
                + "\n4. Combined");
        chosenCategory = Category.findByValue(category);

        return new Clue(name, price, quantity, estimatedTime, chosenCategory);
    }
}
