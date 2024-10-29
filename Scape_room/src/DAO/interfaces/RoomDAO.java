package DAO.interfaces;

import classes.Room;
import exceptions.NoRoomsException;

public interface RoomDAO extends DAO<Room> {

    //aquí mètodes específics que només afectin Room
    //void add(String name, String completionTime, Level chosenLevel, Theme chosenTheme, double price);
    //void add(Room newRoom);
    //void showRooms();
    Room findRoom() throws NoRoomsException;
    void removeRoom(Room room);
}
