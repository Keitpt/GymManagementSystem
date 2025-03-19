package gymmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=GymManagementSystem;encrypt=false;trustServerCertificate=true";
    private static final String USER = "sa"; // Thay đổi tên người dùng
    private static final String PASSWORD = "123456789"; // Thay đổi mật khẩu

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println("Connection to the database failed.");
            e.printStackTrace();
        }
        return connection;
    }
}