package logic;

import DAO.interfaces.implementations.DAOItemImpl;
import DAO.interfaces.implementations.DAORoomImpl;
import classes.Room;
import classes.items.Clue;
import classes.items.DecoItem;
import exceptions.NoCluesException;
import exceptions.NoDecoItemsException;
import exceptions.NoRoomsException;
import management.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Helper;

public class ScapeRoom {

    private final String NAME;
    private static ScapeRoom instance;
    private InventoryManager inventoryManager;
    private InvoiceManager invoiceManager;
    private CustomerManager customerManager;
    private RoomManager roomManager;
    private static final Logger log = LoggerFactory.getLogger(ScapeRoom.class);

    private ScapeRoom(String NAME) {
        this.NAME = NAME;
        this.inventoryManager = InventoryManager.getInstance();
        this.invoiceManager = InvoiceManager.getInstance();
        this.customerManager = CustomerManager.getInstance();
        this.roomManager = RoomManager.getInstance();
    }

    public static ScapeRoom getInstance() {
        if (instance == null) {
            instance = new ScapeRoom("Escaping Java");
        }
        return instance;
    }

    public void newRoom() {
        roomManager.createRoom();
    }
    public void newClue(){
        inventoryManager.createClue();
    }
    public void newDeco(){
        inventoryManager.createDeco();
    }
    public void showInventory() {
        try {
            roomManager.showRoomsWithPrice();
            inventoryManager.showClues();
            inventoryManager.showDecos();
        } catch (NoRoomsException | NoDecoItemsException | NoCluesException e) {
            System.out.println(e.getMessage());
        }

        //fer el mateix amb clue i decoItem

    }

    public void newCustomer() { //CREAR CUSTOMER
        this.customerManager.createCustomer();
    }

    public void customerMenu(){
        this.customerManager.customerMenu();
    }

    public void accountManagement(){
        this.invoiceManager.invoiceMenu();
    }

    //Metodos item
    public void addClueToRoom(){
        this.inventoryManager.addClueToRoom();
    }
    public void addDecoToRoom(){
        this.inventoryManager.addDecoToRoom();
    }
    public void removeItems(){
        int opt = Helper.readInt("Which item do you want to remove:\n" +
                "1. Room.\n" +
                "2. Clue.\n" +
                "3. Decoration object.");
        switch (opt){
            case 1:
               try{
                   inventoryManager.showRooms();
                   this.roomManager.removeRoom();
               }catch (NoRoomsException e){
                   System.err.println(e.getMessage());
               }
                break;
            case 2:
                this.inventoryManager.removeClue();
                break;
            case 3:
                this.inventoryManager.removeDeco();
                break;
        }
    }

    //Remove room
}