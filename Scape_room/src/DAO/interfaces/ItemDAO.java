package DAO.interfaces;

import classes.items.Clue;
import classes.items.DecoItem;
import classes.items.Item;

import java.util.List;

public interface ItemDAO extends DAO<Item> {
    void addClue(Clue clue);
    void addDeco(DecoItem deco);
    List<Clue> showClue();
    List<Item> showDeco();
    void addToRoom();
    //aquí mètodes específics que només afectin Item, que seran els mateixos per Clue i DecoItem

}
