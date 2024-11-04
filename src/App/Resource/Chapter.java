package App.Resource;

import App.DatabaseConfig;

import java.sql.*;
import java.util.Scanner;

import static App.AdminLandingPage.displayAdminLandingPage;
import static App.App.getConnection;

public class Chapter {
    static Scanner cin = new Scanner(System.in);

    public static void addChapter(int textbook_id, Runnable caller) throws SQLException {
        System.out.println("\nAdd New Chapter\n");

        System.out.println("A. Enter unique Chapter ID");
        String chapter_id = cin.nextLine();
        System.out.println("B. Enter Chapter title");
        String title = cin.nextLine();
        saveChapter(chapter_id,title, textbook_id, "no");

        System.out.println("\n1.Add New Section\n2.Go Back\n3.Landing Page");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                Section.createSection(textbook_id, chapter_id, () -> {
                    try {
                        addChapter(textbook_id, caller);
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

    private static void saveChapter(String chapter_id, String title, int textbook_id, String hidden) {
        String sql = "INSERT INTO chapter (textbook_id, chapter_id, title, hidden) VALUES (?, ?, ?, ?)";

        Connection connection;

        try {

            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, hidden);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("New Chapter added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void modifyChapter(int textbook_id, Runnable caller) throws SQLException {
        System.out.println("\nModify Chapter\n");

        System.out.println("A. Enter unique Chapter ID");
        String chapter_id = cin.nextLine();

        if(!checkIfChapterExists(textbook_id, chapter_id)) {
            System.out.println("Chapter not found! Going back...\n");
            caller.run(); // go back to caller
        }

        System.out.println("\n1.Add New Section\n2.Modify Section\n3.Go Back\n4.Landing Page");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                Section.createSection(textbook_id, chapter_id, () -> {
                    try {
                        modifyChapter(textbook_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 2:
                Section.modifySection(textbook_id, chapter_id, () -> {
                    try {
                        modifyChapter(textbook_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 3:
                caller.run();
                break;
            case 4:
                displayAdminLandingPage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static boolean checkIfChapterExists(int textbook_id, String chapter_id) {
        String sql = "SELECT * FROM chapter WHERE textbook_id = ? AND chapter_id = ?";
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
