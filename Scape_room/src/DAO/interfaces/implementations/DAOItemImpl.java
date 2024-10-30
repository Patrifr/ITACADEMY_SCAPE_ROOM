package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.ItemDAO;
import classes.Room;
import classes.items.Clue;
import classes.items.DecoItem;
import classes.items.Item;
import enums.Category;
import enums.Material;
import exceptions.NoCluesException;
import exceptions.NoDecoItemsException;
import utils.Helper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DAOItemImpl extends ConnectionDB implements ItemDAO {

    @Override
    public void add(Item item) {
        String sql = "INSERT INTO item (clue_id, deco_id, name_item, enabled, available, price) VALUES (?, ?, ?, ?, ?, ?)";
        ConnectionDB connection = new ConnectionDB();

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
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
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting the item to the database: " + e.getMessage());
        }
    }

    @Override
    public List<Item> showData() {
        System.out.println("Clues: \n");
        this.showClue();
        System.out.println("Decoration objects: \n");
        this.showDeco();
        return null;
    }

    @Override
    public void addClue(Clue clue) {
        String sql = "INSERT INTO clue (id, category) VALUES (?, ?)";
        ConnectionDB connection = new ConnectionDB();

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
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
            stmt.setString(1, deco.getId());
            stmt.setString(2, deco.getMaterial().getMaterialName());
            stmt.executeUpdate();
            System.out.println("Decoration successfully created.");
        } catch (SQLException e) {
            System.out.println("Error inserting the decoration item to the database: " + e.getMessage());
        }
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

    @Override
    public void addClueToRoom(Room room, Clue clue){
        ConnectionDB connection = new ConnectionDB();
        String sql = "UPDATE item SET item.room_id = ?, available = ? WHERE clue_id = ? AND available = 1";
        String sql2 = "UPDATE room SET room.price = ? WHERE room.id = ? AND enabled = 1";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1, room.getId());
            stmt.setBoolean(2, false);
            stmt.setString(3, clue.getId());
            stmt.executeUpdate();
            System.out.println("Clue successfully added.");

        } catch (SQLException e) {
            System.out.println("Error updating the clue item in the database: " + e.getMessage());
        }
        try(PreparedStatement stmt = connection.getConnection().prepareStatement(sql2)){
            stmt.setDouble(1, clue.getPrice() + room.getPrice());
            stmt.setString(2, room.getId());
            stmt.executeUpdate();

        }catch (SQLException e) {
            System.out.println("Error updating the price room in the database: " + e.getMessage());
        }
    }

    @Override
    public void addDecoToRoom(Room room, DecoItem deco){
        ConnectionDB connection = new ConnectionDB();
        String sql = "UPDATE item SET item.room_id = ?, available = ? WHERE deco_id = ? AND available = 1";
        String sql2 = "UPDATE room SET room.price = ? WHERE room.id = ? AND enabled = 1";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1, room.getId());
            stmt.setBoolean(2, false);
            stmt.setString(3, deco.getId());
            stmt.executeUpdate();
            System.out.println("Decoration item successfully added.");

        } catch (SQLException e) {
            System.out.println("Error updating the decoration item in the database: " + e.getMessage());
        }
        try(PreparedStatement stmt = connection.getConnection().prepareStatement(sql2)){
            stmt.setDouble(1, deco.getPrice() + room.getPrice());
            stmt.setString(2, room.getId());
            stmt.executeUpdate();

        }catch (SQLException e) {
            System.out.println("Error updating the price room in the database: " + e.getMessage());
        }
    }

    @Override
    public void removeClue(Clue clue){
        ConnectionDB connection = new ConnectionDB();
        String sql = "UPDATE item SET item.room_id = ?, available = ?, enabled = ? WHERE clue_id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1, null);
            stmt.setBoolean(2, false);
            stmt.setBoolean(3, false);
            stmt.setString(4, clue.getId());
            stmt.executeUpdate();
            System.out.println("Clue item successfully removed.");

        } catch (SQLException e) {
            System.out.println("Error removing the clue item from the database: " + e.getMessage());
        }
    }

    @Override
    public void removeDeco(DecoItem deco){
        ConnectionDB connection = new ConnectionDB();
        String sql = "UPDATE item SET item.room_id = ?, available = ?, enabled = ? WHERE deco_id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1, null);
            stmt.setBoolean(2, false);
            stmt.setBoolean(3, false);
            stmt.setString(4, deco.getId());
            stmt.executeUpdate();
            System.out.println("Decoration item successfully removed.");

        } catch (SQLException e) {
            System.out.println("Error removing the decoration item from the database: " + e.getMessage());
        }
    }

    @Override
    public Clue findClue() throws NoCluesException {
        Clue clue = null;
        String clueName = Helper.readString("Write the name of the clue: ");
        String sql = "SELECT item.clue_id, item.name_item, item.price, clue.category FROM item INNER JOIN clue ON clue.id = item.clue_id WHERE item.enabled = 1 AND item.name_item = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clueName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    clue = new Clue();
                    clue.setId(rs.getString("clue_id"));
                    clue.setName(rs.getString("name_item"));
                    clue.setPrice(rs.getDouble("price"));
                    clue.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
                } else if(!rs.next()) {
                throw new NoCluesException("Error. Clue not found. Please, try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding customer: " + e.getMessage());
        }
        return clue;
    }

    @Override
    public DecoItem findDeco() throws NoDecoItemsException {
        String decoName = Helper.readString("Write down the name of the decoration item:");
        String sql = "SELECT item.deco_id, item.name_item, item.price, deco.material FROM item INNER JOIN deco ON deco.id = item.deco_id WHERE item.enabled = 1 AND item.name_item = ?";
        ConnectionDB connection = new ConnectionDB();
        DecoItem newDeco = null;

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1, decoName);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                newDeco = new DecoItem();
                newDeco.setId(rs.getString("deco_id"));
                newDeco.setName(rs.getString("name_item"));
                newDeco.setPrice(rs.getDouble("price"));
                newDeco.setMaterial(Material.valueOf(rs.getString("material").toUpperCase()));
            } else if(!rs.next()) {
                throw new NoDecoItemsException("Error. Decoration object not found. Please, try again.");
            }

        } catch (SQLException e) {
            System.out.println("Error extracting the data: " + e.getMessage());
        }
        return newDeco;
    }

}

