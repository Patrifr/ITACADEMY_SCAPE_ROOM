package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.ItemDAO;
import classes.items.Clue;
import classes.items.DecoItem;
import classes.Room;
import classes.items.Item;
import enums.Level;
import enums.Theme;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class DAOItemImpl extends ConnectionDB implements ItemDAO {
    private static ConnectionDB connection = new ConnectionDB();
    private static ResultSet rset = null;
    //a cadascun d'aquests mètodes es gestionen la connexió i els statements

    @Override
    public void add(Item item) {
        String sql = "INSERT INTO item (clue_id, deco_id, name_item, enabled, available, quantity) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            // Asignar valores a los parámetros
            stmt.setString(3, item.getName());
            stmt.setBoolean(4, item.isEnabled());
            stmt.setBoolean(5, item.isAvailable());
            stmt.setInt(6, item.getQuantity());
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
        ConnectionDB connection = new ConnectionDB();
        List<Item> items = null;

        //Diferenciar el tipus d'item a l'hora de mostrar-lo

        /*try (PreparedStatement stmt = connection.getConnection().prepareStatement("SELECT * FROM item")){
            items = new ArrayList<Item>();
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Room room = new Room();
                room.setId(rs.getString("id"));
                room.setName(rs.getString("room_name"));
                room.setCompletionTime(rs.getString("complete_time"));
                room.setLevel(Level.valueOf(rs.getString("lvl").toUpperCase()));
                room.setTheme(Theme.valueOf(rs.getString("theme").toUpperCase()));
                room.setPrice(rs.getDouble("price"));
                items.add(room);
            }

        } catch (SQLException e) {
            System.out.println("Error extracting the data: " + e.getMessage());
        } /*finally {
            connection.closeConnection();
        }*/


        /*public List<Room> showData() {
            ConnectionDB connection = new ConnectionDB();
            List<Room> rooms = null;

            try (PreparedStatement stmt = connection.getConnection().prepareStatement("SELECT * FROM room")){
                rooms = new ArrayList<Room>();
                ResultSet rs = stmt.executeQuery();

                while(rs.next()) {
                    Room room = new Room();
                    room.setId(rs.getString("id"));
                    room.setName(rs.getString("room_name"));
                    room.setCompletionTime(rs.getString("complete_time"));
                    room.setLevel(Level.valueOf(rs.getString("lvl").toUpperCase()));
                    room.setTheme(Theme.valueOf(rs.getString("theme").toUpperCase()));
                    room.setPrice(rs.getDouble("price"));
                    rooms.add(room);
                }

            } catch (SQLException e) {
                System.out.println("Error extracting the data: " + e.getMessage());
            } /*finally {
            connection.closeConnection();
        }*/
            return items;

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
        String sql = "INSERT INTO clue (id, category) VALUES (?, ?)";

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
}

