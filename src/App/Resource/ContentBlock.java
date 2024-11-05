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
                }
                break;
            case 3:
                System.out.println("Enter Activity ID");
                String activity_id = cin.nextLine();
                saveContentBlock(textbook_id, chapter_id, section_id,  blockId, ContentType.activity, activity_id, "no");
                Activity.saveActivity(textbook_id, chapter_id, section_id, blockId, activity_id, "no");
                Activity.createActivity(textbook_id, chapter_id, section_id, blockId, activity_id, () -> {
                    try {
                        newContentBlock(textbook_id, chapter_id, section_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
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
                System.out.println("Enter Unique Activity ID");
                String activity_id = cin.nextLine();
                saveContentBlock(textbook_id, chapter_id, section_id,  blockId, ContentType.activity, activity_id, "no");
                Activity.saveActivity(textbook_id, chapter_id, section_id, blockId, activity_id, "no");
                Activity.TACreateActivity(textbook_id, chapter_id, section_id, blockId, activity_id, () -> {
                    try {
                        TANewContentBlock(textbook_id, chapter_id, section_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 4:
                System.out.println("Enter Activity ID");
                String hide_activity_id = cin.nextLine();
                saveContentBlock(textbook_id, chapter_id, section_id,  blockId, ContentType.activity, hide_activity_id, "no");
                Activity.hideActivity(textbook_id, chapter_id, section_id, blockId, hide_activity_id);
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
                updateContentBlock(textbook_id, chapter_id, section_id,  blockId, ContentType.picture, activity_id, "no");
                Activity.createActivity(textbook_id, chapter_id, section_id, blockId, activity_id, () -> {
                    try {
                        newContentBlock(textbook_id, chapter_id, section_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
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

    public static void facultyModifyContentBlock(int textbook_id, String chapter_id, String section_id, Runnable caller) throws SQLException {
        cin = new Scanner(System.in);
        System.out.println("\nModify Content Block\n");

        System.out.println("A. Content Block ID");
        String blockId = cin.nextLine();

        if(!checkIfContentBlockExists(textbook_id, chapter_id, section_id, blockId)) {
            System.out.println("Section not found! Going back...\n");
            caller.run();
        }

        System.out.println("\n1.Hide Content Block \n2. Delete Content Block \n3.Add Text\n4.Add Picture \n5.Hide Activity \n6. Delete Activity \n7.Add Activity\n8.Go Back");
        int choice = cin.nextInt();
        cin = new Scanner(System.in);

        switch (choice) {
            case 1:
                hideContentBlock(textbook_id,chapter_id,section_id,blockId);
                caller.run();
                break;
            case 2:
                deleteContentBlock(textbook_id,chapter_id,section_id,blockId);
                caller.run();
            case 3:
                System.out.println("A. Enter Text");
                String text = cin.nextLine();

                System.out.println("\n1. Add \n2. Go Back");
                choice = cin.nextInt();

                if(choice == 2) caller.run();
                else updateContentBlock(textbook_id, chapter_id, section_id, blockId, ContentType.text, text, "no");
                break;
            case 4:
                System.out.println("Enter Picture");
                String picture = cin.nextLine();

                System.out.println("\n1. Add \n2. Go Back");
                choice = cin.nextInt();

                if(choice == 1) updateContentBlock(textbook_id, chapter_id, section_id, blockId, ContentType.picture, picture, "no");
                caller.run();
                break;
            case 5:
                System.out.println("Enter unique activity ID");
                String unique_activity_id = cin.nextLine();

                System.out.println("\n1. Save \n2. Cancel");
                choice = cin.nextInt();

                if(choice == 1) Activity.hideActivity(textbook_id,chapter_id,section_id,blockId,unique_activity_id);
                caller.run();
                break;
            case 6:
                System.out.println("Enter unique activity ID");
                unique_activity_id = cin.nextLine();

                System.out.println("\n1. Save \n2. Cancel");
                choice = cin.nextInt();

                if(choice == 1) Activity.deleteActivity(textbook_id,chapter_id,section_id,blockId,unique_activity_id);
                caller.run();
                break;
            case 7:
                System.out.println("Enter Activity ID");
                String activity_id = cin.nextLine();
                updateContentBlock(textbook_id, chapter_id, section_id,  blockId, ContentType.picture, activity_id, "no");
                Activity.createActivity(textbook_id, chapter_id, section_id, blockId, activity_id, () -> {
                    try {
                        newContentBlock(textbook_id, chapter_id, section_id, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 8:
                caller.run();
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void updateContentBlock(int textbook_id, String chapter_id, String section_id, String block_id, ContentType type, String content, String hidden) {
        String sql = "UPDATE blocks SET type = ?, content = ?, hidden = ? WHERE textbook_id = ? AND chapter_id = ? AND section_number = ? AND block_id = ?";
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
        String sql = "SELECT * FROM blocks WHERE textbook_id = ? AND chapter_id = ? AND section_number = ? AND block_id = ?";
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

     static void hideContentBlock(int textbook_id, String chapter_id, String section_id, String block_id){
        System.out.println("\nHide Content Block?\n");
        System.out.println("\n1.Save \n2.Cancel");
        int choice = cin.nextInt();

        if(choice == 2) return;

        String sql = "UPDATE blocks SET hidden = 'yes' WHERE textbook_id = ? AND chapter_id = ? AND section_number = ? AND block_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);
            preparedStatement.setString(3, section_id);
            preparedStatement.setString(4, block_id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Block successfully hidden.");
            } else {
                System.out.println("No matching block found to update.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static void deleteContentBlock(int textbook_id, String chapter_id, String section_id, String block_id){
        System.out.println("\nDelete Content Block?\n");
        System.out.println("\n1. Confirm Delete \n2. Cancel");
        int choice = cin.nextInt();

        if (choice == 2) return;

        String sql = "DELETE FROM blocks WHERE textbook_id = ? AND chapter_id = ? AND section_number = ? AND block_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);
            preparedStatement.setString(3, section_id);
            preparedStatement.setString(4, block_id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Content block successfully deleted.");
            } else {
                System.out.println("No matching content block found to delete.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
