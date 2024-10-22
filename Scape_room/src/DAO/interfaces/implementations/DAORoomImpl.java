package DAO.interfaces.implementations;

import DAO.ConnectionDB;
import DAO.interfaces.RoomDAO;
import classes.Room;
import enums.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DAORoomImpl extends ConnectionDB implements RoomDAO {

    //a cadascun d'aquests mètodes es gestionen la connexió i els statements

    @Override
    public void add(Room newRoom) {
        ConnectionDB connection = new ConnectionDB();
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
