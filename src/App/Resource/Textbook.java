package App.Resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

        System.out.println("\n1.Add New chapter\n2.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                saveTextbook(id,title);
                Chapter.addChapter(id);
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

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("New textbook inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
