package assignment4.repositories;

import assignment4.db.DatabaseConnection;
import assignment4.entities.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class ReservationRepositoryImpl implements ReservationRepository {

    @Override
    public int save(Reservation r) {
        String sql = """
            INSERT INTO reservations(guest_id, room_id, start_date, end_date)
            VALUES (?, ?, ?, ?)
            RETURNING id
        """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, r.getGuestId());
            ps.setInt(2, r.getRoomId());
            ps.setDate(3, Date.valueOf(r.getStartDate()));
            ps.setDate(4, Date.valueOf(r.getEndDate()));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
            throw new SQLException("No id returned");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Reservation> findById(int id) {
        String sql = "SELECT * FROM reservations WHERE id = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Reservation(
                            rs.getInt("id"),
                            rs.getInt("guest_id"),
                            rs.getInt("room_id"),
                            rs.getDate("start_date").toLocalDate(),
                            rs.getDate("end_date").toLocalDate()
                    ));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // overlap for [startDate, endDate)
    @Override
    public boolean hasOverlap(int roomId, LocalDate startDate, LocalDate endDate) {
        String sql = """
            SELECT 1
            FROM reservations
            WHERE room_id = ?
              AND start_date < ?
              AND end_date > ?
            LIMIT 1
        """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, roomId);
            ps.setDate(2, Date.valueOf(endDate));
            ps.setDate(3, Date.valueOf(startDate));

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cancelById(int id) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
