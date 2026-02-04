package assignment4.repositories;

import assignment4.db.DatabaseConnection;
import assignment4.entities.Room;

import java.sql.*;
import java.util.*;

public class RoomRepositoryImpl implements RoomRepository {

    @Override
    public Optional<Room> findById(int id) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        new Room(rs.getInt("id"),
                                rs.getString("number"),
                                rs.getBoolean("available"))
                );
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (Connection c = DatabaseConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                rooms.add(
                        new Room(rs.getInt("id"),
                                rs.getString("number"),
                                rs.getBoolean("available"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }
}
