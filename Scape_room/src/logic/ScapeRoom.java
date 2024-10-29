package logic;

import DAO.interfaces.implementations.DAOItemImpl;
import DAO.interfaces.implementations.DAORoomImpl;
import classes.Room;
import classes.items.Clue;
import exceptions.NoCluesException;
import exceptions.NoDecoItemsException;
import exceptions.NoRoomsException;
import management.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            roomManager.showRooms();
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
        DAOItemImpl daoItem = new DAOItemImpl();
        DAORoomImpl daoRoom = new DAORoomImpl();

        try{
            inventoryManager.showAvailableClues();
            Clue clue = daoItem.findClue();
            roomManager.showRooms();
            Room room = daoRoom.findRoom();
            inventoryManager.addClueToRoom(room, clue);
        }catch (NoCluesException | NoRoomsException e){
            System.out.println(e.getMessage());
        }





    }

    //necessita els mètodes que es criden a Menu


    /*private void newRoom() {
        String name = "";
        int level = 0;
        Level chosenLevel = null;

        name = Helper.readString("Introduce the room's name:");
        level = Helper.readInt("Choose the difficulty: 1, 2 or 3");
        chosenLevel = Level.findByValue(level);

        Room room = new Room(name, chosenLevel);

        try {
            this.inventory.addRoom(room);
            System.out.println("Room successfully created.");
        } catch(DuplicatedRoomException e) {
            System.err.println(e.getMessage());
        }

    }

    private void addClues() {
        String name = "";
        int clueType = 0;
        Room roomFound = null;

       name = Helper.readString("Please enter the name of your room:");
       roomFound = this.inventory.findRoom(name);

        if(roomFound == null) {
            System.out.println("Sorry, this room is not in the system.");
        } else {
            clueType = Helper.readInt("What kind of clue do you need: 1. Numerical, 2. Alphabetical, 3. Visual?");

            switch (clueType) {
                case 1:
                    Clue numClue = this.inventory.getInventoryClues().get(0);
                    roomFound.getClues().add(numClue);
                    roomFound.setTotalPrice(roomFound.getTotalPrice() + numClue.getPrice());
                    System.out.println("A numerical clue has been added to your room.");
                    break;
                case 2:
                    Clue alphClue = this.inventory.getInventoryClues().get(1);
                    roomFound.getClues().add(alphClue);
                    roomFound.setTotalPrice(roomFound.getTotalPrice() + alphClue.getPrice());
                    System.out.println("An alphabetical clue has been added to your room.");
                    break;
                case 3:
                    Clue visClue = this.inventory.getInventoryClues().get(2);
                    roomFound.getClues().add(visClue);
                    roomFound.setTotalPrice(roomFound.getTotalPrice() + visClue.getPrice());
                    System.out.println("A visual clue has been added to your room.");
                    break;
            }
        }

    }

    private void addDecoItems() {
        String name = "";
        Room roomFound = null;
        DecorationItem selectedItem = null;

        name = Helper.readString("Please enter the name of your room:");
        roomFound = this.inventory.findRoom(name);

        if(roomFound == null) {
            System.out.println("Sorry, this room is not in the system.");
        } else {
            ArrayList<DecorationItem> availableItems = this.inventory.getInventoryDecoItems();
            Random random = new Random();
            selectedItem = availableItems.get(random.nextInt(availableItems.size()));
            roomFound.getDecorationItems().add(selectedItem);
            roomFound.setTotalPrice(roomFound.getTotalPrice() + selectedItem.getPrice());
            System.out.println("A new decoration has been added to your room.");
        }
    }

    private void showInventory() {
        try {
            this.inventory.showRooms();
        } catch (NoRoomsException e) {
            System.err.println(e.getMessage());
        }

        try {
            this.inventory.showClues();
        } catch (NoCluesException e) {
            System.err.println(e.getMessage());
        }

        try {
            this.inventory.showDecoItems();
        } catch (NoDecoItemsException e) {
            System.err.println(e.getMessage());
        }
    }

    private void removeFromInventory() {
        int removal = 0;
        removal = Helper.readInt("What do you want to remove from the inventory? 1. Room, 2. Clue, 3. Decoration item");

        switch(removal) {
            case 1:
                try {
                    this.inventory.removeRoom();
                    System.out.println("The first room found in the inventory has been removed.");
                } catch (NoRoomsException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    this.inventory.removeClue();
                    System.out.println("The first clue found in the inventory has been removed.");
                } catch (NoCluesException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 3:
                try {
                    this.inventory.removeDecoItem();
                    System.out.println("The first decoration item found in the inventory has been removed.");
                } catch (NoDecoItemsException e) {
                    System.err.println(e.getMessage());
                }
                break;
        }*/

}
