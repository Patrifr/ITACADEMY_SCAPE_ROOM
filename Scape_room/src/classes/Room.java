package classes;

import enums.Level;
import enums.Theme;
import java.util.UUID;

//import java.util.ArrayList;

public class Room {

    private String id;
    private String name;
    private double price;
    private Level level;
    private Theme theme;
    private String completionTime;
    //private ArrayList<Clue> clues;
    //private ArrayList<DecoItem> decorationItems;

    public Room(String name, double price, Level level, Theme theme, String completionTime) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.level = level;
        this.theme = theme;
        this.completionTime = completionTime;
        //this.clues = new ArrayList<Clue>();
        //this.decorationItems = new ArrayList<DecoItem>();
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

    /*public ArrayList<Clue> getClues() {
        return this.clues;
    }

    public ArrayList<DecoItem> getDecorationItems() {
        return this.decorationItems;
    }*/




    //cal??
    @Override
    public String toString() {
        return "Room{" +
                "level=" + level +
                ", theme=" + theme +
                ", completionTime='" + completionTime + '\'' +
                /*", clues=" + clues +
                ", decorationItems=" + decorationItems +*/
                '}';
    }
}
