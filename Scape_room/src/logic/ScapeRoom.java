package logic;

import management.InventoryManager;

public class ScapeRoom {

    private final String NAME;
    private static ScapeRoom instance;
    private InventoryManager inventoryManager;

    private ScapeRoom(String NAME) {
        this.NAME = NAME;
        this.inventoryManager = new InventoryManager();
    }

    public static ScapeRoom getInstance() {
        if (instance == null) {
            instance = new ScapeRoom("Escaping Java");
        }
        return instance;
    }

}
