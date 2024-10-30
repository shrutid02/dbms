package App.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static App.AdminLandingPage.displayAdminLandingPage;
import static App.App.getConnection;

public class Chapter {
    static Scanner cin = new Scanner(System.in);

    public static void addChapter(int textbook_id) throws SQLException {
        System.out.println("\nAdd New Chapter\n");

        System.out.println("A. Enter unique Chapter ID");
        String chapter_id = cin.nextLine();
        System.out.println("B. Enter Chapter title");
        String title = cin.nextLine();

        System.out.println("\n1.Add New Section\n2.Go Back\n3.Landing Page");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                saveChapter(chapter_id,title, textbook_id, "no");
                //todo Section.addSection

                break;
            case 2:
                displayAdminLandingPage();
                break;
            case 3:
                Textbook.createTextbook();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void saveChapter(String chapter_id, String title, int textbook_id, String hidden) {
        String sql = "INSERT INTO chapter (textbook_id, chapter_id, title, hidden) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection()) {
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
}
