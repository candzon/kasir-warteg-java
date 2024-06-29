package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLConnection {
    private static final String DATABASE = "jdbc:mysql://localhost:3306/db_warteg";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Register the driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver tidak tersedia", e);
        }
        return DriverManager.getConnection(DATABASE, USER, PASSWORD);
    }
}
