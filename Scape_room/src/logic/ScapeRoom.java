package logic;

import exceptions.NoCluesException;
import exceptions.NoDecoItemsException;
import exceptions.NoRoomsException;
import management.*;
import utils.Helper;

public class ScapeRoom {

    private final String NAME;
    private static ScapeRoom instance;
    private InventoryManager inventoryManager;
    private final InvoiceManager invoiceManager;
    private final CustomerManager customerManager;
    private final RoomManager roomManager;

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
        this.roomManager.createRoom();
    }

    public void newClue(){
        this.inventoryManager.createClue();
    }

    public void addClueToRoom(){
        this.inventoryManager.addClueToRoom();
    }

    public void newDeco(){
        this.inventoryManager.createDeco();
    }

    public void addDecoToRoom(){
        this.inventoryManager.addDecoToRoom();
    }

    public void showInventory() {
        try {
            this.roomManager.showRoomsWithPrice();
            this.inventoryManager.showClues();
            this.inventoryManager.showDecos();
        } catch (NoRoomsException | NoDecoItemsException | NoCluesException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeElements(){
        int opt = Helper.readInt("Which element do you want to remove:\n" +
                "1. Room.\n" +
                "2. Clue.\n" +
                "3. Decoration object.");
        switch (opt){
            case 1:
                try{
                    this.inventoryManager.showRooms();
                    this.roomManager.removeRoom();
                }catch (NoRoomsException e){
                    System.err.println(e.getMessage());
                }
                break;
            case 2:
                this.inventoryManager.removeClue(); //exception??
                break;
            case 3:
                this.inventoryManager.removeDeco(); //exception??
                break;
        }
    }

    public void accountManagement(){
        this.invoiceManager.invoiceMenu();
    }

    public void newCustomer() {
        this.customerManager.createCustomer();
    }

    public void customerMenu(){
        this.customerManager.customerMenu();
    }




}