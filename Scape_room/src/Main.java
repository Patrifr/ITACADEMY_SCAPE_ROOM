
import DAO.ConnectionDB;
import DAO.interfaces.implementations.DAOItemImpl;
import exceptions.NoCluesException;
import exceptions.NoRoomsException;
import logic.Menu;
import logic.ScapeRoom;
import management.InventoryManager;
import management.RoomManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws NoRoomsException, NoCluesException {
        Menu.start();
 
    }
}
