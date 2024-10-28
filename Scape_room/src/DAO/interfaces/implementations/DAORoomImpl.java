package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.RoomDAO;
import classes.Room;
import classes.customer.Customer;
import enums.Level;
import enums.Theme;
import exceptions.NoRoomsException;
import utils.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAORoomImpl extends ConnectionDB implements RoomDAO {

    //a cadascun d'aquests mètodes es gestionen la connexió i els statements
    @Override
    public Room findRoom() throws NoRoomsException {
        //No funciona
        ConnectionDB connection = new ConnectionDB();
        ResultSet rs = null;
        Room room = null;
        String room_name = "";
        String sql = "SELECT * FROM room WHERE room_name = ?";

        room_name = Helper.readString("Introduce the room's name:");

        try(PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, room_name);
            rs = stmt.executeQuery();

            if(rs.next()){
                room = new Room();
                room.setId(rs.getString("id"));
                room.setPrice(rs.getDouble("price"));
                room.setLevel(Level.valueOf(rs.getString("lvl").toUpperCase()));
                room.setTheme(Theme.valueOf(rs.getString("theme").toUpperCase()));
                room.setCompletionTime(rs.getString("complete_time"));
            }else if(!rs.next()){
                    throw new NoRoomsException("Error. Room not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error while trying to connect to the database." + e.getMessage());
        }
        return room;
    }

    @Override
    public void add(Room newRoom) {
        ConnectionDB connection = new ConnectionDB();
        String sql = "INSERT INTO room (id, room_name, complete_time, lvl, theme, price, escape_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            // Asignar valores a los parámetros
            stmt.setString(1, newRoom.getId());
            stmt.setString(2, newRoom.getName());
            stmt.setString(3, newRoom.getCompletionTime());
            stmt.setString(4, newRoom.getLevel().getLevelName());
            stmt.setString(5, newRoom.getTheme().getThemeName());
            stmt.setDouble(6, newRoom.getPrice());
            stmt.setString(7, "1");
            // Ejecutar el comando SQL
            stmt.executeUpdate();
            System.out.println("Room successfully created.");
        } catch (SQLException e) {
            System.err.println("Error inserting the room to the database: " + e.getMessage());
        } /*finally {
            connection.closeConnection();
        }*/

    }

    @Override
    public List<Room> showData() {
        List<Room> rooms = null;
        ConnectionDB connection = new ConnectionDB();
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
        return rooms;

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }


}
