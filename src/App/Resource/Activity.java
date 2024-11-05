package App.Resource;

import App.DatabaseConfig;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static App.AdminLandingPage.displayAdminLandingPage;
import static App.App.getConnection;

public class Activity {
    static Scanner cin = new Scanner(System.in);

    public static void createActivity(int textbook_id, String chapter_id, String section_id, String block_id, String unique_activity_id, Runnable caller) throws SQLException {

        System.out.println("\n1.Add Question\n2.Go Back\n3.Landing Page");
        int choice = cin.nextInt();
        cin.nextLine();

        switch (choice) {
            case 1:
                Question.createQuestion(textbook_id, chapter_id, section_id, block_id, unique_activity_id, () -> {
                try {
                    createActivity(textbook_id, chapter_id, section_id, block_id, unique_activity_id, caller);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                });
                break;
            case 2:
                caller.run();
                break;
            case 3:
                displayAdminLandingPage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void TACreateActivity(int textbook_id, String chapter_id, String section_id, String block_id, String unique_activity_id, Runnable caller) throws SQLException {

        System.out.println("\n1.Add Question\n2.Go Back");
        int choice = cin.nextInt();
        cin.nextLine();

        switch (choice) {
            case 1:
                Question.facultyTACreateQuestion(textbook_id, chapter_id, section_id, block_id, unique_activity_id, () -> {
                    try {
                        TACreateActivity(textbook_id, chapter_id, section_id, block_id, unique_activity_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 2:
                caller.run();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

     static void saveActivity(int textbook_id, String chapter_id, String section_id, String block_id, String unique_activity_id, String hidden) {
        String sql = "INSERT INTO activity (textbook_id, chapter_id, section_id, block_id, unique_activity_id, hidden) VALUES (?, ?, ?, ?, ?, ?)";

         Connection connection = null;

        try {
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);
            preparedStatement.setString(3, section_id);
            preparedStatement.setString(4, block_id);
            preparedStatement.setString(5, unique_activity_id);
            preparedStatement.setString(6, hidden);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.printf("New activity inserted successfully!%n");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void hideActivity(int textbook_id, String chapter_id, String section_id, String block_id, String unique_activity_id) {
        String sql = "UPDATE Activity SET hidden = 'yes' WHERE textbook_id = ? AND chapter_id = ? AND section_id = ? AND block_id = ? AND unique_activity_id = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);
            preparedStatement.setString(3, section_id);
            preparedStatement.setString(4, block_id);
            preparedStatement.setString(5, unique_activity_id);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Activity successfully hidden.");
            } else {
                System.out.println("No matching activity found to hide.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void deleteActivity(int textbook_id, String chapter_id, String section_id, String block_id, String unique_activity_id) {
        String sql = "DELETE FROM Activity WHERE textbook_id = ? AND chapter_id = ? AND section_id = ? AND block_id = ? AND unique_activity_id = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);
            preparedStatement.setString(3, section_id);
            preparedStatement.setString(4, block_id);
            preparedStatement.setString(5, unique_activity_id);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Activity successfully deleted.");
            } else {
                System.out.println("No matching activity found to delete.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
