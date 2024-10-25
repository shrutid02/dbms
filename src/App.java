package App;
import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String DB_URL = "jdbc:mysql://127.0.0.1:3306/dbms";
        String DB_USER = "root";
        String DB_PASSWORD = "password";

        try {
            // Establish a connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

        addStudent();
    }

    public static void addStudent() {
        String DB_URL = "jdbc:mysql://127.0.0.1:3306/dbms";
        String DB_USER = "root";
        String DB_PASSWORD = "password";

        String sql = "SELECT * from students";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery(); // Execute the SELECT statement

            while (rs.next()) {
                String id = rs.getString("student_id");
                String name = rs.getString("full_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");

                System.out.println("ID: " + id + ", Name: " + name + ", Username: " + username +
                        ", Password: " + password + ", Email: " + email);
            }
            System.out.println("Student added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}