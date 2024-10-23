package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.ItemDAO;
import classes.Room;
import classes.customer.Customer;
import classes.items.Item;
import enums.Level;
import enums.Theme;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOItemImpl extends ConnectionDB implements ItemDAO {

    //a cadascun d'aquests mètodes es gestionen la connexió i els statements

    @Override
    public void add(Item item) {

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
}
