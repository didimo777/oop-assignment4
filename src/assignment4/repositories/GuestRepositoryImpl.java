package assignment4.repositories;

import assignment4.db.DatabaseConnection;
import assignment4.entities.Guest;

import java.sql.*;
import java.util.Optional;

public class GuestRepositoryImpl implements GuestRepository {

    @Override
    public int save(Guest guest) {
        String sql = "INSERT INTO guests(name, email) VALUES (?, ?) RETURNING id";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, guest.getName());
            ps.setString(2, guest.getEmail());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
            throw new SQLException("No id returned");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Guest> findById(int id) {
        String sql = "SELECT * FROM guests WHERE id = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Guest(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email")
                    ));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Guest> findByEmail(String email) {
        String sql = "SELECT * FROM guests WHERE email = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Guest(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email")
                    ));
                }
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
