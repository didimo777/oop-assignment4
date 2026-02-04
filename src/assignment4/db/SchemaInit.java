package assignment4.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class SchemaInit {
    private SchemaInit() {}

    public static void ensureSchema() {
        try (Connection c = DatabaseConnection.getConnection();
             Statement st = c.createStatement()) {

            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS guests (
                    id SERIAL PRIMARY KEY,
                    name TEXT NOT NULL,
                    email TEXT NOT NULL UNIQUE
                )
            """);
            st.executeUpdate("CREATE UNIQUE INDEX IF NOT EXISTS guests_email_uq ON guests(email)");

            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS rooms (
                    id SERIAL PRIMARY KEY,
                    number TEXT NOT NULL UNIQUE,
                    available BOOLEAN NOT NULL DEFAULT TRUE,
                    room_type TEXT NOT NULL DEFAULT 'STANDARD'
                )
            """);
            st.executeUpdate("CREATE UNIQUE INDEX IF NOT EXISTS rooms_number_uq ON rooms(number)");

            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS reservations (
                    id SERIAL PRIMARY KEY,
                    guest_id INT NOT NULL REFERENCES guests(id),
                    room_id INT NOT NULL REFERENCES rooms(id),
                    start_date DATE NOT NULL,
                    end_date DATE NOT NULL
                )
            """);

            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS payments (
                    id SERIAL PRIMARY KEY,
                    reservation_id INT NOT NULL REFERENCES reservations(id),
                    amount NUMERIC(10,2) NOT NULL,
                    status TEXT NOT NULL
                )
            """);

            st.executeUpdate("""
                INSERT INTO rooms(number, available, room_type, price)
                    VALUES ('101', TRUE, 'STANDARD', 50.00),
                            ('102', TRUE, 'SUITE', 120.00),
                            ('D1',  TRUE, 'DORM_BED', 25.00)
                    ON CONFLICT (number) DO NOTHING
            """);


        } catch (SQLException e) {
            throw new RuntimeException("Schema init failed: " + e.getMessage(), e);
        }
    }
}
