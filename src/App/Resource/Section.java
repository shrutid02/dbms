package App.Resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static App.AdminLandingPage.displayAdminLandingPage;
import static App.App.getConnection;

public class Section {
    static Scanner cin = new Scanner(System.in);

    public static void createSection(int textbookId, String chapterId, Runnable caller) throws SQLException {
        System.out.println("\nCreate Section\n");

        System.out.println("A. Enter Section Number");
        String sectionId = cin.nextLine();
        System.out.println("B. Enter Section Title");
        String title = cin.nextLine();
        saveSection(textbookId, chapterId, sectionId,title);

        System.out.println("\n1.Add New Content Block\n2.Go Back\n3.Landing Page");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                ContentBlock.newContentBlock(textbookId, chapterId, sectionId, () -> {
                    try {
                        createSection(textbookId, chapterId, caller);
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

    private static void saveSection(int textbookId, String chapterId, String sectionId, String title) {
        String sql = "INSERT INTO sections (textbook_id, chapter_id, section_id, title, hidden) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, textbookId);
            preparedStatement.setString(2, chapterId);
            preparedStatement.setString(3, sectionId);
            preparedStatement.setString(4, title);
            preparedStatement.setString(5, "no");

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.printf("New section inserted in %d textbook, %s chapter successfully!%n", textbookId, chapterId);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void modifySection(int textbookId, String chapterId, Runnable caller) throws SQLException {
        System.out.println("\nModify Section\n");

        System.out.println("A. Enter unique Textbook ID");
        int textbook_id = cin.nextInt();
        cin.nextLine();
        System.out.println("B. Enter Chapter ID");
        String chapter_id = cin.nextLine();
        System.out.println("C. Enter Section Number");
        String section_id = cin.nextLine();

        if(!checkIfSectionExists(textbook_id, chapter_id, section_id)) {
            System.out.println("Section not found! Going back...\n");
            caller.run(); // go back to caller
        }

        System.out.println("\n1.Add New Content Block\n2.Modify Content Block\n3.Go Back\n4.Landing Page");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                ContentBlock.newContentBlock(textbook_id, chapter_id, section_id, () -> {
                    try {
                        modifySection(textbookId, chapterId, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 2:
                ContentBlock.modifyContentBlock(textbook_id, chapter_id, section_id, () -> {
                    try {
                        modifySection(textbookId, chapterId, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
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

    private static boolean checkIfSectionExists(int textbook_id, String chapter_id, String section_id) {
        String sql = "SELECT * FROM sections WHERE textbook_id = ? AND chapter_id = ? AND section_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);
            preparedStatement.setString(3, section_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
