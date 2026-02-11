package assignment4.db;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String URL="jdbc:postgresql://aws-1-ap-northeast-1.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USER = "postgres.msiaqolredhmdtrvbpmp";
    private static final String PASSWORD;

    static {
        try (InputStream input = DatabaseConnection.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found in resources");
            }

            Properties props = new Properties();
            props.load(input);

            PASSWORD = props.getProperty("db_password");

            if (PASSWORD == null || PASSWORD.isBlank()) {
                throw new RuntimeException("db.password is missing in config.properties");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load database config", e);
        }
    }

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
