package management;

import DAO.interfaces.implementations.DAORoomImpl;
import classes.Room;
import enums.*;
import utils.Helper;

public class RoomManager {

    public void createRoom() {
        String name = "", completionTime = "", id = "";
        double price = 0d;
        int level = 0, theme = 0;
        Level chosenLevel = null;
        Theme chosenTheme = null;
        Room newRoom = null;
        DAORoomImpl daoRoom = null;
        //cal tota la prèvia???

        name = Helper.readString("Introduce the name of the room:");
        price = Helper.readDouble("Introduce the price of the room:");
        level = Helper.readInt("Choose the difficulty: 1, 2 or 3");
        chosenLevel = Level.findByValue(level);
        theme = Helper.readInt("Choose a main theme for the room:\n"
                + "1. Adventure"
                + "\n2. Mystery"
                + "\n3. Humour"
                + "\n4. History"
                + "\n5. Science");
        chosenTheme = Theme.findByValue(theme);
        completionTime = Helper.readString("Introduce the completion time of this room:");
        newRoom = new Room(name, price, chosenLevel, chosenTheme, completionTime);

        daoRoom = new DAORoomImpl();
        daoRoom.add(newRoom);

    }

}
