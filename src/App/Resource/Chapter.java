package App.Resource;

import App.DatabaseConfig;

import java.sql.*;
import java.util.Scanner;

import static App.AdminLandingPage.displayAdminLandingPage;
import static App.App.getConnection;

public class Chapter {
    static Scanner cin = new Scanner(System.in);

    public static void addChapter(String courseId, Runnable caller) throws SQLException {
        addChapter(getTextbookIdForCourse(courseId),caller);
    }

    public static void addChapter(int textbook_id, Runnable caller) throws SQLException {
        System.out.println("\nAdd New Chapter\n");

        System.out.println("A. Enter unique Chapter ID");
        String chapter_id = cin.nextLine();
        System.out.println("B. Enter Chapter title");
        String title = cin.nextLine();
        saveChapter(chapter_id,title, textbook_id, "no");

        System.out.println("\n1.Add New Section\n2.Go Back");
        if(!caller.toString().contains("TA") && !caller.toString().contains("Faculty")) System.out.println("3.Landing Page");

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
            System.out.println("Failure");
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void facultyModifyChapter(String courseId, Runnable caller) throws SQLException {
        int textbook_id = getTextbookIdForCourse(courseId);
        System.out.println("\nModify Chapter\n");

        System.out.println("A. Enter unique Chapter ID");
        String chapter_id = cin.nextLine();

        if (!checkIfChapterExists(textbook_id, chapter_id)) {
            System.out.println("Chapter not found! Going back...\n");
            caller.run(); // go back to caller
        }

        System.out.println("\n1. Hide Chapter \n2. Delete Chapter \n3.Add New Section\n4.Modify Section\n5.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                hideChapter(textbook_id, chapter_id);
                caller.run();
                break;
            case 2:
                deleteChapter(chapter_id, textbook_id);
                caller.run();
                break;
            case 3:
                Section.createSection(textbook_id, chapter_id, () -> {
                    try {
                        facultyModifyChapter(courseId, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 4:
                Section.facultyModifySection(textbook_id, chapter_id, () -> {
                    try {
                        facultyModifyChapter(courseId, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 5:
                caller.run();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;

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

    public static void TAModifyChapter(String courseId, Runnable caller) throws SQLException {
        int textbook_id = getTextbookIdForCourse(courseId);
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
                        TAModifyChapter(courseId, caller);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case 2:
                Section.TAModifySection(textbook_id, chapter_id, () -> {
                    try {
                        TAModifyChapter(courseId, caller);
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

    private static int getTextbookIdForCourse(String course) throws SQLException {
        String sql = "SELECT * FROM courses WHERE course_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, course);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("textbook_id");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            throw e;
        }
    }

    private static void hideChapter(int textbook_id, String chapter_id){
        System.out.println("\nHide Chapter?\n");
        System.out.println("\n1.Save \n2.Cancel");
        int choice = cin.nextInt();

        if(choice == 2) return;

        String sql = "UPDATE chapter SET hidden = 'yes' WHERE chapter_id = ? AND textbook_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, chapter_id);
            preparedStatement.setInt(2, textbook_id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Chapter successfully hidden.");
            } else {
                System.out.println("No matching chapter found to update.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void deleteChapter(String chapterId, int textbookId) {
        System.out.println("\nDelete Chapter?\n");
        System.out.println("\n1.Save \n2.Cancel");
        int choice = cin.nextInt();

        if(choice == 2) return;

        String sql = "DELETE FROM chapter WHERE chapter_id = ? AND textbook_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, chapterId);
            preparedStatement.setInt(2, textbookId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Chapter successfully deleted.");
            } else {
                System.out.println("No matching chapter found to delete.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
