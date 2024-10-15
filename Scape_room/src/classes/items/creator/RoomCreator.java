package classes.items.creator;

import classes.items.Item;
import classes.items.Room;
import enums.*;

public class RoomCreator extends ItemCreator {

    //aprofitar els creators per demanar les dades a l'usuari??

    @Override
    public Item createItem() {
        String name = "";
        double price = 0d;
        Level level = null;
        Theme theme = null;
        String completionTime = null;
        return new Room(name, price, level, theme, completionTime);
    }
}
