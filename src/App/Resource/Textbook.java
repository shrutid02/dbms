package App.Resource;

import App.DatabaseConfig;

import java.sql.*;
import java.util.Optional;
import java.util.Scanner;

import static App.AdminLandingPage.displayAdminLandingPage;
import static App.App.getConnection;

public class Textbook {
    static Scanner cin = new Scanner(System.in);

    public static void createTextbook() throws SQLException {
        System.out.println("\nCreate E-textbook\n");

        System.out.println("A. Enter E-textbook title");
        String title = cin.nextLine();
        System.out.println("B. Enter unique E-textbook ID");
        int id = cin.nextInt();
        saveTextbook(id,title);

        System.out.println("\n1.Add New chapter\n2.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                Chapter.addChapter(id, () -> {
                    try {
                        createTextbook();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 2:
                displayAdminLandingPage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void saveTextbook(int id, String title) {
        String sql = "INSERT INTO textbook (textbook_id, title) VALUES (?, ?)";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("New textbook inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Did not insert: " + e.getMessage());
        }
    }

    public static void modifyTextbook() throws SQLException {
        System.out.println("\nModify E-textbook\n");

        System.out.println("A. Enter unique E-textbook id");
        int textbook_id = cin.nextInt();

        if(!checkIfTextbookExists(textbook_id)) {
            System.out.println("Textbook not found! Going back...\n");
            displayAdminLandingPage(); // go back to caller
        }

        System.out.println("\n1.Add New chapter\n2.Modify chapter\n3.Go Back\n4.LandingPage");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                Chapter.addChapter(textbook_id, () -> {
                    try {
                        modifyTextbook();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 2:
                Chapter.modifyChapter(textbook_id, () -> {
                    try {
                        modifyTextbook();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 3, 4:
                displayAdminLandingPage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static boolean checkIfTextbookExists(int id) {
        String sql = "SELECT * FROM textbook WHERE textbook_id = ?";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Integer.parseInt(String.valueOf(id)));

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Did not select: " + e.getMessage());
            return false;
        }
    }
}
