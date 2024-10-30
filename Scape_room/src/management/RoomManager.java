package management;

import DAO.interfaces.implementations.DAORoomImpl;
import classes.Room;
import enums.*;
import exceptions.NoRoomsException;
import utils.Helper;

import java.util.List;

public class RoomManager {

    private static RoomManager instance;
    private final DAORoomImpl daoRoom;

    private RoomManager() {
        this.daoRoom = new DAORoomImpl();
    }

    public static RoomManager getInstance() {
        if (instance == null) {
            instance = new RoomManager();
        }
        return instance;
    }

    public void createRoom() {
        String name = "", completionTime = "", id = "";
        double price = 0d;
        int level = 0, theme = 0;
        Level chosenLevel = null;
        Theme chosenTheme = null;
        Room newRoom = null;

        name = Helper.readString("Introduce the name of the room:");
        price = Helper.readDouble("Introduce the price of the room:");
        do {
            level = Helper.readInt("Choose the difficulty: 1, 2 or 3");
            if(level < 1 || level > 3) {
                System.out.println("Please, choose a valid option: 1, 2, 3.");
            } else {
                chosenLevel = Level.findByValue(level);
            }
        } while(level < 1 || level > 3);
        do {
            theme = Helper.readInt("Choose a main theme for the room:\n"
                    + "1. Adventure"
                    + "\n2. Mystery"
                    + "\n3. Humour"
                    + "\n4. History"
                    + "\n5. Science");
            if(theme < 1 || theme > 5) {
                System.out.println("Please, choose a valid option: 1, 2, 3, 4, 5.");
            } else {
                chosenTheme = Theme.findByValue(theme);
            }
        } while(theme < 1 || theme > 5);
        completionTime = Helper.readString("Introduce the completion time of this room:");
        newRoom = new Room(name, price, chosenLevel, chosenTheme, completionTime);

        this.daoRoom.add(newRoom);
    }

    public void showRoomsWithPrice() throws NoRoomsException {
        List<Room> listedRooms = this.daoRoom.showData();

        if(listedRooms.isEmpty()) {
            throw new NoRoomsException("There are no registered rooms.");
        }

        System.out.println("Registered rooms:");
        listedRooms.stream().map(r -> "ID: " + r.getId() +
                ", Name: " + r.getName() +
                ", Level: " + r.getLevel().getLevelName() +
                ", Theme: " + r.getTheme().getThemeName() +
                ", Completion time: " + r.getCompletionTime() +
                ", Price: " + r.getPrice()).forEach(System.out::println);
        System.out.println("Total number of rooms: " + listedRooms.size());
        System.out.println("Total price: " + listedRooms.stream().mapToDouble(Room::getPrice).sum());

    }
    public void removeRoom() throws NoRoomsException {
        Room room = daoRoom.findRoom();
        daoRoom.removeRoom(room);
    }

}
