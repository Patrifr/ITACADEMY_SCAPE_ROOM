package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.ItemDAO;
import classes.items.Clue;
import classes.items.DecoItem;
import classes.Room;
import classes.items.Item;
import classes.items.creator.ClueCreator;
import classes.items.creator.DecoItemCreator;
import enums.Category;
import enums.Level;
import enums.Material;
import enums.Theme;
import utils.Helper;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class DAOItemImpl extends ConnectionDB implements ItemDAO {
    private static ConnectionDB connection = new ConnectionDB();
    private static ResultSet rs = null;
    //a cadascun d'aquests mètodes es gestionen la connexió i els statements

    @Override
    public void add(Item item) {
        String sql = "INSERT INTO item (clue_id, deco_id, name_item, enabled, available, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            // Asignar valores a los parámetros
            stmt.setString(3, item.getName());
            stmt.setBoolean(4, item.isEnabled());
            stmt.setBoolean(5, item.isAvailable());
            stmt.setDouble(6, item.getPrice());
            if(item instanceof Clue){
                stmt.setString(1, item.getId());
                stmt.setString(2, null);
            }else if(item instanceof DecoItem){
                stmt.setString(2, item.getId());
                stmt.setString(1, null);
            }
            // Ejecutar el comando SQL
            stmt.executeUpdate();
            System.out.println("item successfully created.");
        } catch (SQLException e) {
            System.out.println("Error inserting the item to the database: " + e.getMessage());
        }
    }

    @Override
    public List<Item> showData() {
        System.out.println("Clues: \n");
        showClue();
        //Diferenciar el tipus d'item a l'hora de mostrar-lo
    return null;
    }



    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void addClue(Clue clue) {
        String sql = "INSERT INTO clue (id, category) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            // Asignar valores a los parámetros
            stmt.setString(1, clue.getId());
            stmt.setString(2, clue.getCategory().getCategoryName());
            stmt.executeUpdate();
            System.out.println("Clue successfully created.");
        } catch (SQLException e) {
            System.out.println("Error inserting the Clue to the database: " + e.getMessage());
        }
    }

    @Override
    public void addDeco(DecoItem deco) {
        String sql = "INSERT INTO deco (id, material) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            // Asignar valores a los parámetros
            stmt.setString(1, deco.getId());
            stmt.setString(2, deco.getMaterial().getMaterialName());
            stmt.executeUpdate();
            System.out.println("Clue successfully created.");

        } catch (SQLException e) {
            System.out.println("Error inserting the Clue to the database: " + e.getMessage());
        }
    }

    @Override
    public List<Clue> showClue() {
        List<Clue> clues = null;
        String sql = "SELECT item.clue_id, item.name_item, item.price, clue.category FROM item INNER JOIN clue ON clue.id = item.clue_id WHERE item.available = 1";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            clues = new ArrayList<Clue>();
            rs = stmt.executeQuery();

            while(rs.next()){
                Clue newClue = new Clue();
                newClue.setId(rs.getString("clue_id"));
                newClue.setName(rs.getString("name_item"));
                newClue.setPrice(rs.getDouble("price"));
                newClue.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
                clues.add(newClue);
            }

        } catch (SQLException e) {
            System.out.println("Error extracting the data: " + e.getMessage());
        }
        return clues;
    }

    @Override
    public List<Item> showDeco() {
        List<Item> decos = null;
        DecoItemCreator decoItemCreator = new DecoItemCreator();
        String sql = "SELECT item.deco_id, item.name_item, item.price, deco.material FROM item INNER JOIN deco ON deco.id = item.deco_id WHERE item.available = 1";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            decos = new ArrayList<Item>();
            ResultSet rs = stmt.executeQuery();

            DecoItem newDeco = (DecoItem) decoItemCreator.createItem();
            newDeco.setId(rs.getString("deco_id"));
            newDeco.setName(rs.getString("name_item"));
            newDeco.setPrice(rs.getDouble("price"));
            newDeco.setMaterial(Material.valueOf(rs.getString("material").toUpperCase()));
            decos.add(newDeco);

        } catch (SQLException e) {
            System.out.println("Error extracting the data: " + e.getMessage());
        }
        return decos;
    }

    @Override
    public void addToRoom() {
        //Mostrar per consola les clues
        //agafar la clue que vol pel nom
        //Mostrar la llista de rooms
        //adafar el nom de la room
        //insertar el room_id en la clue.room_id
    }
}

