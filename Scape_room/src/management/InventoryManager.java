package management;

import DAO.ConnectionDB;
import DAO.interfaces.implementations.DAOItemImpl;
import classes.*;
import classes.items.Clue;
import classes.items.DecoItem;
import classes.items.creator.ClueCreator;
import classes.items.creator.DecoItemCreator;
import exceptions.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InventoryManager {

    //singleton
    private static InventoryManager instance;
    private DAOItemImpl daoItem;


    private InventoryManager() {
        this.daoItem = new DAOItemImpl();
    } //com que està buit, si cal s'elimina

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }
    public void createClue(){
        ClueCreator clueCreator = new ClueCreator();
        Clue newClue = (Clue) clueCreator.createItem();
        this.daoItem.addClue(newClue);
        this.daoItem.add(newClue);
        //hem creat nova clue amb el ceator, s'afegeix a la sql clues i a la taula items a posteriori.
    }
    public void createDeco(){
        DecoItemCreator decoItemCreator = new DecoItemCreator();
        DecoItem newDeco = (DecoItem) decoItemCreator.createItem();
        this.daoItem.addDeco(newDeco);
        this.daoItem.add(newDeco);
    }
    public void showClues() throws NoCluesException{
        List<Clue> listedClues = this.daoItem.showClue();

        if(listedClues.isEmpty()) {
            throw new NoCluesException("There are no registered clues.");
        }

        System.out.println("\nRegistered clues:");
        listedClues.stream().map(c -> "ID: " + c.getId() +
                ", Name: " + c.getName() +
                ", Price: " + c.getPrice() +
                ", Category: " + c.getCategory()).forEach(System.out::println);
        System.out.println("Total number of clues: " + listedClues.size());
        System.out.println("Total clue's price: " + listedClues.stream().mapToDouble(Clue::getPrice).sum());
    }

    public void showAvailableClues() throws NoCluesException{
        List<Clue> listedAvailableClues = this.daoItem.showClueAvailable();

        if(listedAvailableClues.isEmpty()) {
            throw new NoCluesException("There are no registered clues.");
        }

        System.out.println("\nRegistered clues:");
        listedAvailableClues.stream().map(c -> "ID: " + c.getId() +
                ", Name: " + c.getName() +
                ", Price: " + c.getPrice() +
                ", Category: " + c.getCategory()).forEach(System.out::println);
        System.out.println("Total number of clues: " + listedAvailableClues.size());
        System.out.println("Total clue's price: " + listedAvailableClues.stream().mapToDouble(Clue::getPrice).sum());
    }

    public void showDecos() throws NoDecoItemsException{
        List<DecoItem> listedDecos = this.daoItem.showDeco();

        if(listedDecos.isEmpty()) {
            throw new NoDecoItemsException("There are no registered decoration items.");
        }

        System.out.println("\nRegistered decorations:");
        listedDecos.stream().map(d -> "ID: " + d.getId() +
                ", Name: " + d.getName() +
                ", Price: " + d.getPrice() +
                ", Material: " + d.getMaterial()).forEach(System.out::println);
        System.out.println("Total number of decorations: " + listedDecos.size());
        System.out.println("Total decoration's price: " + listedDecos.stream().mapToDouble(DecoItem::getPrice).sum());
    }

    public void showAvailableDecos() throws NoDecoItemsException{
        List<DecoItem> listedAvailableDecos = this.daoItem.showDecoAvailable();

        if(listedAvailableDecos.isEmpty()) {
            throw new NoDecoItemsException("There are no registered decoration items.");
        }

        System.out.println("\nRegistered decorations:");
        listedAvailableDecos.stream().map(d -> "ID: " + d.getId() +
                ", Name: " + d.getName() +
                ", Price: " + d.getPrice() +
                ", Material: " + d.getMaterial()).forEach(System.out::println);
        System.out.println("Total number of decorations: " + listedAvailableDecos.size());
        System.out.println("Total decoration's price: " + listedAvailableDecos.stream().mapToDouble(DecoItem::getPrice).sum());
    }

    public void addClueToRoom(Room room, Clue clue){
        ConnectionDB connection = new ConnectionDB();
        String sql = "UPDATE item SET item.room_id = ?, available = ? WHERE name_item = ?";
        //intentar agafar el id de la clue insertada per consola i buscarla pel seu id.
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1, room.getId());
            stmt.setBoolean(2, false);
            stmt.setString(3, clue.getName());
            stmt.executeUpdate();
            System.out.println("Clue successfully updated.");

        } catch (SQLException e) {
            System.out.println("Error updating the clue item in the database: " + e.getMessage());
        }
    }
   /*public Item addClueToRoom() throws NoRoomsException, NoCluesException {
        DAOItemImpl daoItem = new DAOItemImpl();
        //Mostrem les clues.
        showClues();
        String clue_name = Helper.readString("Write down the name of the clue:");

        //Busquem la clue pel name i ens retorna una clue.
        daoItem.findClue();

        //Busquem la room pel name i ens retorna l'id.
        DAORoomImpl daoRoom = new DAORoomImpl();
        RoomManager roomManager = RoomManager.getInstance();
        roomManager.showRooms();
        String roomId = daoRoom.findRoom();

        String sql = "UPDATE item SET room_id = ? WHERE name_item = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1, roomId);
            stmt.setString(2, clue_name);
            stmt.executeUpdate();
            System.out.println("Clue successfully updated.");

        } catch (SQLException e) {
            System.out.println("Error updating the clue item in the database: " + e.getMessage());
        }
        return foundClue;
    }

    public void addToRoomMenu() throws NoCluesException, NoRoomsException {
        int opt;
        DAOItemImpl daoItem = new DAOItemImpl();
        do{
            opt = Helper.readInt("Which item do you want to add to a room:\n" +
                    "1. Clue.\n" +
                    "2. Decoration item.\n" +
                    "0. Exit");
            switch (opt){
                case 1:
                addClueToRoom();
               // daoItem.updateAvailable(addClueToRoom());
                    break;
            }
        }while(opt != 0);
    }*/

}
