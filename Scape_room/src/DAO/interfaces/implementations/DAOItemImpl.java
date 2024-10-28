package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.ItemDAO;
import classes.items.Clue;
import classes.items.DecoItem;
import classes.items.Item;
import enums.Category;
import enums.Material;
import exceptions.NoRoomsException;
import management.InventoryManager;
import utils.Helper;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class DAOItemImpl extends ConnectionDB implements ItemDAO {
    //a cadascun d'aquests mètodes es gestionen la connexió i els statements

    @Override
    public void add(Item item) {
        String sql = "INSERT INTO item (clue_id, deco_id, name_item, enabled, available, price) VALUES (?, ?, ?, ?, ?, ?)";
        ConnectionDB connection = new ConnectionDB();

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
        ConnectionDB connection = new ConnectionDB();

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
        ConnectionDB connection = new ConnectionDB();

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            // Asignar valores a los parámetros
            stmt.setString(1, deco.getId());
            stmt.setString(2, deco.getMaterial().getMaterialName());
            stmt.executeUpdate();
            System.out.println("Decoration successfully created.");

        } catch (SQLException e) {
            System.out.println("Error inserting the decoration item to the database: " + e.getMessage());
        }
    }

    @Override
    public List<Clue> showClueAvailable() {
        List<Clue> clues = null;
        String sql = "SELECT item.clue_id, item.name_item, item.price, clue.category FROM item INNER JOIN clue ON clue.id = item.clue_id WHERE item.available = 1 AND item.enabled = 1";
        ConnectionDB connection = new ConnectionDB();
        ResultSet rs = null;
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
    public List<Clue> showClue() {
        List<Clue> clues = null;
        String sql = "SELECT item.clue_id, item.name_item, item.price, clue.category FROM item INNER JOIN clue ON clue.id = item.clue_id WHERE item.enabled = 1";
        ConnectionDB connection = new ConnectionDB();
        ResultSet rs = null;
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
    public List<DecoItem> showDeco() {
        List<DecoItem> decos = null;
        String sql = "SELECT item.deco_id, item.name_item, item.price, deco.material FROM item INNER JOIN deco ON deco.id = item.deco_id WHERE item.enabled = 1";
        ConnectionDB connection = new ConnectionDB();

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            decos = new ArrayList<DecoItem>();
            ResultSet rs = stmt.executeQuery();
            
          while(rs.next()){
            DecoItem newDeco = new DecoItem();
            newDeco.setId(rs.getString("deco_id"));
            newDeco.setName(rs.getString("name_item"));
            newDeco.setPrice(rs.getDouble("price"));
            newDeco.setMaterial(Material.valueOf(rs.getString("material").toUpperCase()));
            decos.add(newDeco);
          }
          
        } catch (SQLException e) {
            System.out.println("Error extracting the data: " + e.getMessage());
        }
        return decos;
    }


    @Override
    public List<DecoItem> showDecoAvailable() {
        List<DecoItem> decos = null;
        String sql = "SELECT item.deco_id, item.name_item, item.price, deco.material FROM item INNER JOIN deco ON deco.id = item.deco_id WHERE item.available = 1 AND item.enabled = 1";
        ConnectionDB connection = new ConnectionDB();

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            decos = new ArrayList<DecoItem>();
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                DecoItem newDeco = new DecoItem();
                newDeco.setId(rs.getString("deco_id"));
                newDeco.setName(rs.getString("name_item"));
                newDeco.setPrice(rs.getDouble("price"));
                newDeco.setMaterial(Material.valueOf(rs.getString("material").toUpperCase()));
                decos.add(newDeco);
            }

        } catch (SQLException e) {
            System.out.println("Error extracting the data: " + e.getMessage());
        }
        return decos;
    }

    public Clue findClue(){
        //Checked: Funciona.
        String clueName = Helper.readString("Write down the name of the clue:");
        String sql = "SELECT item.clue_id, item.name_item, item.price, clue.category FROM item INNER JOIN clue ON clue.id = item.clue_id WHERE item.available = 1 AND item.enabled = 1 AND item.name_item = ?";
        ConnectionDB connection = new ConnectionDB();
        Clue newClue = null;

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1, clueName);
            ResultSet rs = stmt.executeQuery();

          while(rs.next()){
              newClue = new Clue();
              newClue.setId(rs.getString("clue_id"));
              newClue.setName(rs.getString("name_item"));
              newClue.setPrice(rs.getDouble("price"));
              newClue.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
          }

        } catch (SQLException e) {
            System.out.println("Error extracting the data: " + e.getMessage());
        }
        return newClue;
    }
    /*public void updateAvailable(Item item){
        //canviar el status de available
        String item_name = item.getName();
        String sql = "UPDATE item SET available = 0 WHERE name_item = ?";
        ConnectionDB connection = new ConnectionDB();

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1, item_name);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                item.setAvailable(rs.getBoolean("available"));
            }

        } catch (SQLException e) {
            System.out.println("Error updating the data: " + e.getMessage());
        }
    }*/

    //Enlazar la clue con la room y cambiarle el available.

    @Override
    public void addToRoom() throws NoRoomsException {

        //Mostrar per consola les clues
        //agafar la clue que vol pel nom
        //Mostrar la llista de rooms
        //adafar el nom de la room
        //insertar el room_id en la clue.room_id
    }

}

