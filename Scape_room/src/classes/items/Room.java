package classes.items;

import enums.Level;
import enums.Theme;

import java.util.ArrayList;

public class Room extends Item {

    private Level level;
    private Theme theme;
    private String completionTime;
    private ArrayList<Clue> clues;
    private ArrayList<DecoItem> decorationItems;

    public Room(String name, double price, Level level, Theme theme, String completionTime) {
        super(name, price);
        this.level = level;
        this.theme = theme;
        this.completionTime = completionTime;
        this.clues = new ArrayList<Clue>();
        this.decorationItems = new ArrayList<DecoItem>();
    }

    public Theme getTheme() {
        return theme;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public Level getLevel() {
        return this.level;
    }

    public ArrayList<Clue> getClues() {
        return this.clues;
    }

    public ArrayList<DecoItem> getDecorationItems() {
        return this.decorationItems;
    }



    //què fem amb aquest method??
    @Override
    public void commonMethod() {

    }

    //cal?? falten els atributs de super
    @Override
    public String toString() {
        return "Room{" +
                "level=" + level +
                ", theme=" + theme +
                ", completionTime='" + completionTime + '\'' +
                ", clues=" + clues +
                ", decorationItems=" + decorationItems +
                '}';
    }
}
