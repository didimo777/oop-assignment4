package assignment4.AccauntingComponent.repositories;

import assignment4.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ReportingRepositoryImpl implements ReportingRepository {

    @Override
    public double totalPaid(LocalDate from, LocalDate to) {
        String sql = """
            SELECT COALESCE(SUM(p.amount), 0) AS total
            FROM payments p
            JOIN reservations r ON r.id = p.reservation_id
            WHERE p.status = 'PAID'
              AND r.start_date >= ?
              AND r.end_date <= ?
        """;

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(from));
            ps.setDate(2, Date.valueOf(to));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getDouble("total");
                return 0.0;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
