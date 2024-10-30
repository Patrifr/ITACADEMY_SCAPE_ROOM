package management;

import DAO.interfaces.implementations.DAOItemImpl;
import DAO.interfaces.implementations.DAORoomImpl;
import classes.*;
import classes.items.Clue;
import classes.items.DecoItem;
import classes.items.creator.ClueCreator;
import classes.items.creator.DecoItemCreator;
import exceptions.*;

import java.util.List;

public class InventoryManager {

    private static InventoryManager instance;
    private final DAOItemImpl daoItem;
    private final DAORoomImpl daoRoom;


    private InventoryManager() {
        this.daoItem = new DAOItemImpl();
        this.daoRoom = new DAORoomImpl();
    }

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
    }

    public void addClueToRoom(){
        try{
            this.showAvailableClues();
            Clue clue = daoItem.findClue();
            this.showRooms();
            Room room = daoRoom.findRoom();
            daoItem.addClueToRoom(room, clue);
        }catch (NoCluesException | NoRoomsException e){
            System.out.println(e.getMessage());
        }
    }

    public void createDeco(){
        DecoItemCreator decoItemCreator = new DecoItemCreator();
        DecoItem newDeco = (DecoItem) decoItemCreator.createItem();
        this.daoItem.addDeco(newDeco);
        this.daoItem.add(newDeco);
    }

    public void addDecoToRoom(){
        try{
            this.showAvailableDecos();
            DecoItem deco = daoItem.findDeco();
            this.showRooms();
            Room room = daoRoom.findRoom();
            daoItem.addDecoToRoom(room, deco);
        }catch (NoRoomsException | NoDecoItemsException e){
            System.out.println(e.getMessage());
        }
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

    public void removeClue(){
        try{
            this.showClues();
            Clue clue = daoItem.findClue();
            daoItem.removeClue(clue);
        }catch (NoCluesException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeDeco(){
        try{
            this.showDecos();
            DecoItem deco = daoItem.findDeco();
            daoItem.removeDeco(deco);
        }catch (NoDecoItemsException e){
            System.out.println(e.getMessage());
        }
    }
}
