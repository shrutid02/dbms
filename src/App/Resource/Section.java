package App.Resource;

import App.DatabaseConfig;

import java.sql.*;
import java.util.Scanner;

import static App.AdminLandingPage.displayAdminLandingPage;
import static App.App.getConnection;

public class Section {
    static Scanner cin = new Scanner(System.in);

    public static void createSection(int textbookId, String chapterId, Runnable caller) throws SQLException {
        System.out.println("\nCreate Section\n");
        cin = new Scanner(System.in);

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

    public static void FacultyCreateSection(int textbookId, String chapterId, Runnable caller) throws SQLException {
        System.out.println("\nCreate Section\n");
        cin = new Scanner(System.in);

        System.out.println("A. Enter Section Number");
        String sectionId = cin.nextLine();
        System.out.println("B. Enter Section Title");
        String title = cin.nextLine();
        saveSection(textbookId, chapterId, sectionId,title);

        System.out.println("\n1.Add New Content Block\n2.Go Back");

        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                ContentBlock.FacultyNewContentBlock(textbookId, chapterId, sectionId, () -> {
                    try {
                        FacultyCreateSection(textbookId, chapterId, caller);
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

    public static void TACreateSection(int textbookId, String chapterId, Runnable caller) throws SQLException {
        System.out.println("\nCreate Section\n");
        cin = new Scanner(System.in);

        System.out.println("A. Enter Section Number");
        String sectionId = cin.nextLine();
        System.out.println("B. Enter Section Title");
        String title = cin.nextLine();
        saveSection(textbookId, chapterId, sectionId,title);

        System.out.println("\n1.Add New Content Block\n2.Go Back");

        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                ContentBlock.TANewContentBlock(textbookId, chapterId, sectionId, () -> {
                    try {
                        TACreateSection(textbookId, chapterId, caller);
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

    private static void saveSection(int textbookId, String chapterId, String sectionId, String title) {
        String sql = "INSERT INTO sections (textbook_id, chapter_id, section_id, title, hidden) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

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
        cin = new Scanner(System.in);

        System.out.println("A. Enter unique Textbook ID");
        int textbook_id = cin.nextInt();
        cin.nextLine();
        System.out.println("B. Enter Chapter ID");
        String chapter_id = cin.nextLine();
        System.out.println("C. Enter Section Number");
        String section_id = cin.nextLine();

        if(!checkIfSectionExists(textbook_id, chapter_id, section_id)) {
            System.out.println("Section not found! Going back...\n");
            caller.run();
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
                caller.run();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void facultyModifySection(int textbook_id, String chapter_id, Runnable caller) throws SQLException {
        cin = new Scanner(System.in);
        System.out.println("\nModify Section\n");

        System.out.println("C. Enter Section Number");
        String section_id = cin.nextLine();

        if(!checkIfSectionExists(textbook_id, chapter_id, section_id)) {
            System.out.println("Section not found! Going back...\n");
            caller.run();
        }

        System.out.println("\n1.Hide section \n2.Delete Section \n3.Add New Content Block\n4.Modify Content Block\n5.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                hideSection(textbook_id,chapter_id,section_id);
                facultyModifySection(textbook_id,chapter_id,caller);
                break;
            case 2:
                deleteSection(textbook_id,chapter_id,section_id);
                facultyModifySection(textbook_id,chapter_id,caller);
                break;
            case 3:
                ContentBlock.FacultyNewContentBlock(textbook_id, chapter_id, section_id, () -> {
                    try {
                        facultyModifySection(textbook_id, chapter_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 4:
                ContentBlock.facultyModifyContentBlock(textbook_id, chapter_id, section_id, () -> {
                    try {
                        facultyModifySection(textbook_id, chapter_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            case 5:
                caller.run();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void TAModifySection(int textbook_id, String chapter_id, Runnable caller) throws SQLException {
        System.out.println("\nModify Section\n");
        cin = new Scanner(System.in);

//        System.out.println("A. Enter unique Textbook ID");
//        int textbook_id = cin.nextInt();
//        cin.nextLine();
        System.out.println("C. Enter Section Number");
        String section_id = cin.nextLine();
//        System.out.println("B. Enter Section Title");
//        String chapter_id = cin.nextLine();
//        System.out.println("C. Enter Chapter ID");
//        String chapter_id = cin.nextLine();
//        System.out.println("C. Enter Book");
//        int textbook_id1 = cin.nextInt();
//        cin.nextLine();

        if(!checkIfSectionExists(textbook_id, chapter_id, section_id)) {
            System.out.println("Section not found! Going back...\n");
            caller.run();
        }

        System.out.println("\n1.Add New Content Block\n2.Modify Content Block\n3.Delete Content Block\n4.Hide Content Block\n5.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                ContentBlock.TANewContentBlock(textbook_id, chapter_id, section_id, () -> {
                    try {
                        TAModifySection(textbook_id, chapter_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 2:
                ContentBlock.modifyContentBlock(textbook_id, chapter_id, section_id, () -> {
                    try {
                        TAModifySection(textbook_id, chapter_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            case 3:
                System.out.println("A. Content Block ID");
                String delete_block_id = cin.nextLine();
                ContentBlock.deleteContentBlock(textbook_id, chapter_id, section_id, delete_block_id);
                break;
            case 4:
                System.out.println("A. Content Block ID");
                String hide_block_id = cin.nextLine();
                ContentBlock.hideContentBlock(textbook_id, chapter_id, section_id, hide_block_id);
                break;
            case 5:
                caller.run();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static boolean checkIfSectionExists(int textbook_id, String chapter_id, String section_id) {
        String sql = "SELECT * FROM sections WHERE textbook_id = ? AND chapter_id = ? AND section_id = ?";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);
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

    private static void hideSection(int textbook_id, String chapter_id, String section_number){
        System.out.println("\nHide Section?\n");
        System.out.println("\n1.Save \n2.Cancel");
        int choice = cin.nextInt();

        if(choice == 2) return;

        String sql = "UPDATE sections SET hidden = 'yes' WHERE chapter_id = ? AND textbook_id = ? AND section_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, chapter_id);
            preparedStatement.setInt(2, textbook_id);
            preparedStatement.setString(3, section_number);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Section successfully hidden.");
            } else {
                System.out.println("No matching section found to update.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void deleteSection(int textbook_id, String chapter_id, String section_number){
        System.out.println("\nDelete Section?\n");
        System.out.println("\n1.Save \n2.Cancel");
        int choice = cin.nextInt();

        if(choice == 2) return;

        String sql = "DELETE FROM sections WHERE chapter_id = ? AND textbook_id = ? AND section_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, chapter_id);
            preparedStatement.setInt(2, textbook_id);
            preparedStatement.setString(3, section_number);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Section successfully deleted.");
            } else {
                System.out.println("No matching section found to update.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
