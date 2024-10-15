package logic;

import management.InventoryManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class ScapeRoom {

    //és la duplicació d'Escape; haurem d'eliminar un dels dos

    private final String NAME;
    private static ScapeRoom instance;
    private InventoryManager inventoryManager;
    //necessita també IncoiveManager
    //private static final Logger log = LoggerFactory.getLogger(ScapeRoom.class);

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
