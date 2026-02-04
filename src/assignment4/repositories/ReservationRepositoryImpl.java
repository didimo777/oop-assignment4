package assignment4.repositories;

import assignment4.db.DatabaseConnection;
import assignment4.entities.Reservation;

import java.sql.*;
import java.util.Optional;

public class ReservationRepositoryImpl implements ReservationRepository {

    @Override
    public void save(Reservation r) {
        String sql = """
            INSERT INTO reservations(guest_id, room_id, start_date, end_date)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, r.getGuestId());
            ps.setInt(2, r.getRoomId());
            ps.setDate(3, Date.valueOf(r.getStartDate()));
            ps.setDate(4, Date.valueOf(r.getEndDate()));
            ps.executeUpdate();

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
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new Reservation(
                                rs.getInt("id"),
                                rs.getInt("guest_id"),
                                rs.getInt("room_id"),
                                rs.getDate("start_date").toLocalDate(),
                                rs.getDate("end_date").toLocalDate()
                        )
                );
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



