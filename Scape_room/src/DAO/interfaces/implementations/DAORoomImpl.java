package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.RoomDAO;
import classes.Room;
import exceptions.NoRoomsException;
import utils.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAORoomImpl extends ConnectionDB implements RoomDAO {
    private static ConnectionDB connection = new ConnectionDB();
    private static ResultSet rs = null;

    //a cadascun d'aquests mètodes es gestionen la connexió i els statements
    @Override
    public void findRoom(Room room) throws NoRoomsException {
        String room_name = "";
        String room_id = "";
        String sql = "SELECT id FROM room WHERE room_name = ?";
        room_name = Helper.readString("Introduce the room's name:");

        try(PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, room_name);
            rs = stmt.executeQuery();
            while(rs.next()){
                room_id = rs.getString("id");
                if(!rs.next()){
                    throw new NoRoomsException("Error. Room not found.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void add(Room newRoom) {
        String sql = "INSERT INTO room (id, room_name, complete_time, lvl, theme, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)){
            // Asignar valores a los parámetros
            stmt.setString(1, newRoom.getId());
            stmt.setString(2, newRoom.getName());
            stmt.setString(3, newRoom.getCompletionTime());
            stmt.setString(4, newRoom.getLevel().getLevelName());
            stmt.setString(5, newRoom.getTheme().getThemeName());
            stmt.setDouble(6, newRoom.getPrice());
            // Ejecutar el comando SQL
            stmt.executeUpdate();
            System.out.println("Room successfully created.");
        } catch (SQLException e) {
            System.out.println("Error inserting the room to the database: " + e.getMessage());
        } /*finally {
            connection.closeConnection();
        }*/

    }
    /*public void add(String name, String completionTime, Level chosenLevel, Theme chosenTheme, double price) {

        ConnectionDB connection = new ConnectionDB();
        connection.getConnection();

        String sql = "INSERT INTO room (room_name, complete_time, lvl, theme, price) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

            // Asignar valores a los parámetros
            //stm.setString(1, id);
            stmt.setString(1, name);
            stmt.setString(2, completionTime);
            stmt.setString(3, chosenLevel.getLevelName());
            stmt.setString(4, chosenTheme.getName());
            stmt.setDouble(5, price);
            // Ejecutar el comando SQL
            stmt.executeUpdate();
            System.out.println("Room successfully created.");
        } catch (SQLException e) {
            System.out.println("Error inserting the room to the database: " + e.getMessage());
        } finally {
            connection.closeConnection();
        }

    }*/

    @Override
    public void showData() {

    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }


}
