package DAO.interfaces;

import classes.Room;
import classes.items.Clue;
import classes.items.DecoItem;
import classes.items.Item;
import exceptions.NoRoomsException;

import java.util.List;

public interface ItemDAO extends DAO<Item> {
    void addClue(Clue clue);
    void addDeco(DecoItem deco);
    List<Clue> showClue();
    List<Clue> showClueAvailable();
    List<DecoItem> showDeco();
    List<DecoItem> showDecoAvailable();
    void addClueToRoom(Room room, Clue clue);
    void addDecoToRoom(Room room, DecoItem deco);
    public void removeClue(Clue clue);
    void addToRoom()throws NoRoomsException;
    //aquí mètodes específics que només afectin Item, que seran els mateixos per Clue i DecoItem

}
