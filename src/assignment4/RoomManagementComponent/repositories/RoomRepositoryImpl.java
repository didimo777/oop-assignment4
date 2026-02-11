package assignment4.RoomManagementComponent.repositories;

import assignment4.db.DatabaseConnection;
import assignment4.RoomManagementComponent.entities.Room;
import assignment4.RoomManagementComponent.entities.RoomType;
import assignment4.RoomManagementComponent.pattern.RoomFactory;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class RoomRepositoryImpl implements RoomRepository {

    @Override
    public Optional<Room> findById(int id) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRoom(rs));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> findAll() {
        String sql = "SELECT * FROM rooms ORDER BY id";
        List<Room> out = new ArrayList<>();

        try (Connection c = DatabaseConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) out.add(mapRoom(rs));
            return out;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> findAvailable(LocalDate startDate, LocalDate endDate) {
        String sql = """
            SELECT r.*
            FROM rooms r
            WHERE r.available = true
              AND NOT EXISTS (
                SELECT 1
                FROM reservations res
                WHERE res.room_id = r.id
                  AND res.start_date < ?
                  AND res.end_date > ?
              )
            ORDER BY r.id
        """;
        List<Room> out = new ArrayList<>();

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(endDate));
            ps.setDate(2, Date.valueOf(startDate));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(mapRoom(rs));
            }
            return out;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Room mapRoom(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String number = rs.getString("number");
        boolean available = rs.getBoolean("available");
        String typeStr = rs.getString("room_type");
        RoomType type = RoomType.valueOf(typeStr.toUpperCase(Locale.ROOT));
        return RoomFactory.create(type, id, number, available);
    }
}
