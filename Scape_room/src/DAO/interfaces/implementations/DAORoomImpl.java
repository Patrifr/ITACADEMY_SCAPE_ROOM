package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.RoomDAO;
import classes.Room;
import enums.Level;
import enums.Theme;
import exceptions.NoRoomsException;
import utils.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAORoomImpl extends ConnectionDB implements RoomDAO {

    @Override
    public Room findRoom() throws NoRoomsException {
        ConnectionDB connection = new ConnectionDB();
        ResultSet rs = null;
        Room room = null;
        String room_name = "";
        String sql = "SELECT * FROM room WHERE room_name = ? AND enabled = 1";

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
        String sql = "INSERT INTO room (id, room_name, complete_time, lvl, theme, price, enabled, escape_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setString(1, newRoom.getId());
            stmt.setString(2, newRoom.getName());
            stmt.setString(3, newRoom.getCompletionTime());
            stmt.setString(4, newRoom.getLevel().getLevelName());
            stmt.setString(5, newRoom.getTheme().getThemeName());
            stmt.setDouble(6, newRoom.getPrice());
            stmt.setBoolean(7, newRoom.isEnabled());
            stmt.setString(8, "1");
            stmt.executeUpdate();
            System.out.println("Room successfully created.");
        } catch (SQLException e) {
            System.err.println("Error inserting the room to the database: " + e.getMessage());
        }

    }

    @Override
    public List<Room> showData() {
        List<Room> rooms = null;
        ConnectionDB connection = new ConnectionDB();
        try (PreparedStatement stmt = connection.getConnection().prepareStatement("SELECT * FROM room WHERE enabled = 1")){
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
        }
        return rooms;

    }
    public void removeRoom(Room room){
        ConnectionDB connection = new ConnectionDB();
        String sql = "UPDATE room SET enabled = ? WHERE room.id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            stmt.setBoolean(1, false);
            stmt.setString(2, room.getId());
            stmt.executeUpdate();
            System.out.println("Room successfully removed.");

        } catch (SQLException e) {
            System.out.println("Error removing the room from the database: " + e.getMessage());
        }
    }


    @Override
    public void update() {

    }

    @Override
    public void remove() {
    }

}
