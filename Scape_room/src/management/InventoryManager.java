package management;

import DAO.interfaces.implementations.DAOItemImpl;
import DAO.interfaces.implementations.DAORoomImpl;
import classes.*;
import classes.items.Clue;
import classes.items.DecoItem;
import classes.items.Item;
import classes.items.creator.ClueCreator;
import classes.items.creator.DecoItemCreator;
import enums.Level;
import enums.Material;
import enums.Theme;
import exceptions.*;
import logic.ScapeRoom;
import utils.Helper;

import java.util.ArrayList;
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

        System.out.println("Registered clues:");
        listedClues.stream().map(c -> "ID: " + c.getId() +
                ", Name: " + c.getName() +
                ", Price: " + c.getPrice() +
                ", Category: " + c.getCategory()).forEach(System.out::println);
        System.out.println("Total number of rooms: " + listedClues.size());

    }
}
