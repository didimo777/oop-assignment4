package assignment4.repositories;

import assignment4.db.DatabaseConnection;
import assignment4.entities.Payment;

import java.sql.*;

public class PaymentRepositoryImpl implements PaymentRepository {

    @Override
    public int save(Payment p) {
        String sql = "INSERT INTO payments(reservation_id, amount, status) VALUES (?, ?, ?) RETURNING id";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, p.getReservationId());
            ps.setDouble(2, p.getAmount());
            ps.setString(3, p.getStatus());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
            throw new SQLException("No id returned");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
