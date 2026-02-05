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
    private static final String PASSWORD = "#?!gPY*T4a7FjbQ";// ← DATABASE PASSWORD
    private DatabaseConnection() {
        // no instances
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}