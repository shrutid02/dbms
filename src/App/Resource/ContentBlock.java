package App.Resource;

import App.DatabaseConfig;

import java.sql.*;
import java.util.Scanner;

import static App.AdminLandingPage.displayAdminLandingPage;
import static App.App.getConnection;
import static App.TA.TALandingPage.displayTALandingPage;
import static App.TA.TALandingPage.gbTAId;

public class ContentBlock {

    enum ContentType {
        text,
        picture,
        activity
    }

    static Scanner cin = new Scanner(System.in);

    public static void newContentBlock(int textbook_id, String chapter_id, String section_id, Runnable caller) throws SQLException {
        System.out.println("\nCreate ContentBlock\n");

        System.out.println("A. Enter Content Block ID");
        String blockId = cin.nextLine();

        System.out.println("\n1.Add Text\n2.Add Picture\n3.Add Activity\n4.Go Back\n5.Landing Page");
        int choice = cin.nextInt();
        cin.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter Text");
                String text = cin.nextLine();
                System.out.println("\n1.Add\n2.Go Back\n3.Landing Page");
                int choice2 = cin.nextInt();
                switch (choice2) {
                    case 1:
                        saveContentBlock(textbook_id, chapter_id, section_id, blockId, ContentType.text, text, "no");
                        break;
                    case 2:
                        caller.run();
                        break;
                    case 3:
                        displayAdminLandingPage();
                        break;
                }
                break;
            case 2:
                System.out.println("Enter Picture");
                String picture = cin.nextLine();
                System.out.println("\n1.Add\n2.Go Back\n3.Landing Page");
                int choice3 = cin.nextInt();
                switch (choice3) {
                    case 1:
                        saveContentBlock(textbook_id, chapter_id, section_id, blockId, ContentType.text, picture, "no");
                        break;
                    case 2:
                        caller.run();
                        break;
                    case 3:
                        displayAdminLandingPage();
                        break;
                }                break;
            case 3:
                System.out.println("Enter Activity ID");
                String activity_id = cin.nextLine();
                Activity.saveActivity(textbook_id, chapter_id, section_id, blockId, activity_id, "no");
                Activity.createActivity(textbook_id, chapter_id, section_id, blockId, activity_id, () -> {
                    try {
                        newContentBlock(textbook_id, chapter_id, section_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                saveContentBlock(textbook_id, chapter_id, section_id,  blockId, ContentType.activity, activity_id, "no");
                break;
            case 4:
                caller.run();
                break;
            case 5:
                displayAdminLandingPage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void TANewContentBlock(int textbook_id, String chapter_id, String section_id, Runnable caller) throws SQLException {
        System.out.println("\nCreate ContentBlock\n");

        System.out.println("A. Enter Content Block ID");
        String blockId = cin.nextLine();

        System.out.println("\n1.Add Text\n2.Add Picture\n3.Add Activity\n4.Hide Activity\n5.Go Back");
        int choice = cin.nextInt();
        cin.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter Text");
                String text = cin.nextLine();
                System.out.println("\n1.Add\n2.Go Back");
                int choice2 = cin.nextInt();
                switch (choice2) {
                    case 1:
                        saveContentBlock(textbook_id, chapter_id, section_id, blockId, ContentType.text, text, "no");
                        break;
                    case 2:
                        caller.run();
                        break;
                    case 3:
                        displayAdminLandingPage();
                        break;
                }
                break;
            case 2:
                System.out.println("Enter Picture");
                String picture = cin.nextLine();
                System.out.println("\n1.Add\n2.Go Back");
                int choice3 = cin.nextInt();
                switch (choice3) {
                    case 1:
                        saveContentBlock(textbook_id, chapter_id, section_id, blockId, ContentType.text, picture, "no");
                        break;
                    case 2:
                        caller.run();
                        break;
                    case 3:
                        displayAdminLandingPage();
                        break;
                }
                break;
            case 3:
                System.out.println("Enter Activity ID");
                String activity_id = cin.nextLine();
                Activity.saveActivity(textbook_id, chapter_id, section_id, blockId, activity_id, "no");
                Activity.createActivity(textbook_id, chapter_id, section_id, blockId, activity_id, () -> {
                    try {
                        TANewContentBlock(textbook_id, chapter_id, section_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                saveContentBlock(textbook_id, chapter_id, section_id,  blockId, ContentType.activity, activity_id, "no");
                break;
            case 4:
                System.out.println("Enter Activity ID");
                String hide_activity_id = cin.nextLine();
                //Activity.hideActivity(textbook_id, chapter_id, section_id, blockId, hide_activity_id, "yes");
                saveContentBlock(textbook_id, chapter_id, section_id,  blockId, ContentType.activity, hide_activity_id, "no");
                break;
            case 5:
                caller.run();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void saveContentBlock(int textbook_id, String chapter_id, String section_id, String block_id, ContentType type, String content, String hidden) {
        String sql = "INSERT INTO blocks (textbook_id, chapter_id, section_number, block_id, type, content, hidden) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);
            preparedStatement.setString(3, section_id);
            preparedStatement.setString(4, block_id);
            preparedStatement.setString(5, type.name());
            preparedStatement.setString(6, content);
            preparedStatement.setString(7, hidden);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.printf("New content block inserted successfully!%n");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void modifyContentBlock(int textbook_id, String chapter_id, String section_id, Runnable caller) throws SQLException {
        System.out.println("\nModify Content Block\n");

        System.out.println("A. Content Block ID");
        String blockId = cin.nextLine();

        if(!checkIfContentBlockExists(textbook_id, chapter_id, section_id, blockId)) {
            System.out.println("Section not found! Going back...\n");
            caller.run(); // go back to caller
        }

        System.out.println("\n1.Add Text\n2.Add Picture\n3.Add Activity\n4.Go Back\n5.Landing Page");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter Text");
                String text = cin.nextLine();
                updateContentBlock(textbook_id, chapter_id, section_id, blockId, ContentType.text, text, "no");
                break;
            case 2:
                System.out.println("Enter Picture");
                String picture = cin.nextLine();
                updateContentBlock(textbook_id, chapter_id, section_id, blockId, ContentType.picture, picture, "no");
                break;
            case 3:
                System.out.println("Enter Activity ID");
                String activity_id = cin.nextLine();
                Activity.createActivity(textbook_id, chapter_id, section_id, blockId, activity_id, () -> {
                    try {
                        newContentBlock(textbook_id, chapter_id, section_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                updateContentBlock(textbook_id, chapter_id, section_id,  blockId, ContentType.picture, activity_id, "no");
                break;
            case 4:
                caller.run();
                break;
            case 5:
                if(caller.toString().contains("TA")) displayTALandingPage(gbTAId);
                else displayAdminLandingPage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void updateContentBlock(int textbook_id, String chapter_id, String section_id, String block_id, ContentType type, String content, String hidden) {
        String sql = "UPDATE blocks SET type = ?, content = ?, hidden = ? WHERE textbook_id = ? AND chapter_id = ? AND section_id = ? AND block_id = ?";
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set new values for the columns to be updated
            preparedStatement.setString(1, type.name());
            preparedStatement.setString(2, content);
            preparedStatement.setString(3, hidden);

            // Set the primary key parameters
            preparedStatement.setInt(4, textbook_id);
            preparedStatement.setString(5, chapter_id);
            preparedStatement.setString(6, section_id);
            preparedStatement.setString(7, block_id);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Content block updated successfully!");
            } else {
                System.out.println("No matching content block found to update. Enter valid Content Block ID");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static boolean checkIfContentBlockExists(int textbook_id, String chapter_id, String section_id, String block_id) {
        String sql = "SELECT * FROM blocks WHERE textbook_id = ? AND chapter_id = ? AND section_id = ? AND block_id = ?";
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);
            preparedStatement.setString(3, section_id);
            preparedStatement.setString(4, block_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
