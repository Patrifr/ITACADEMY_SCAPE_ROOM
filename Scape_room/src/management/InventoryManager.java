package management;

import DAO.ConnectionDB;
import DAO.interfaces.implementations.DAOItemImpl;
import DAO.interfaces.implementations.DAORoomImpl;
import classes.*;
import classes.items.Clue;
import classes.items.DecoItem;
import classes.items.creator.ClueCreator;
import classes.items.creator.DecoItemCreator;
import exceptions.*;
import utils.Helper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InventoryManager {

    //singleton
    private static InventoryManager instance;
    private DAOItemImpl daoItem;
    private DAORoomImpl daoRoom;


    private InventoryManager() {
        this.daoItem = new DAOItemImpl();
        this.daoRoom = new DAORoomImpl();
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
    }
    public void showRooms() throws NoRoomsException {
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
    }

    public void addClueToRoom(){
        try{
            showAvailableClues();
            Clue clue = daoItem.findClue();
            showRooms();
            Room room = daoRoom.findRoom();
            daoItem.addClueToRoom(room, clue);
        }catch (NoCluesException | NoRoomsException e){
            System.out.println(e.getMessage());
        }
    }
    public void addDecoToRoom(){
        try{
            showAvailableDecos();
            DecoItem deco = daoItem.findDeco();
            showRooms();
            Room room = daoRoom.findRoom();
            daoItem.addDecoToRoom(room, deco);
        }catch (NoRoomsException | NoDecoItemsException e){
            System.out.println(e.getMessage());
        }
    }
    public void removeClue(){
        try{
            showClues();
            Clue clue = daoItem.findClue();
            daoItem.removeClue(clue);
        }catch (NoCluesException e){
            System.out.println(e.getMessage());
        }
    }
    public void removeDeco(){
        try{
            showDecos();
            DecoItem deco = daoItem.findDeco();
            daoItem.removeDeco(deco);
        }catch (NoDecoItemsException e){
            System.out.println(e.getMessage());
        }
    }
}


    /*public void removeClue(Clue clue){
        ConnectionDB connection = new ConnectionDB();
        String sql = "UPDATE item SET item.room_id = null, available = ?, enable = ? WHERE clue_id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setBoolean(1, false);
            stmt.setBoolean(2, false);
            stmt.setString(3, clue_id);
            stmt.executeUpdate();
            System.out.println("Clue removed successfully.");

        }catch (SQLException e) {
            System.out.println("Error removing the clue item from the database: " + e.getMessage());
        }
    }*/

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
