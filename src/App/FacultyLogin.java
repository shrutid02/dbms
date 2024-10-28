package App;

import java.sql.*;
import java.util.Scanner;

public class FacultyLogin {
    static Scanner cin = new Scanner(System.in);

    public static void displayFacultyLoginPage() throws SQLException {
        System.out.println("Faculty Login Page");
        System.out.println("\n1.Sign-in\n2.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                // Add sign-in logic here
                System.out.println("Enter your username: ");
                String username = cin.next();
                System.out.println("Enter your password: ");
                String password = cin.next();
                if (verifyFaculty(username, password)) {
                    System.out.println("\n!!! WELCOME " + username + " !!!");
                    FacultyLandingPage.displayFacultyLandingPage(username);
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                    displayFacultyLoginPage();
                }
                break;
            case 2:
                App.displayHomePage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayFacultyLoginPage();
                break;
        }
    }

    public static boolean verifyFaculty(String username, String password) {

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD)) {
            String query = "SELECT * FROM Faculty WHERE faculty_id = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
