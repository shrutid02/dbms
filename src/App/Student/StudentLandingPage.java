package App.Student;

import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import App.App;
import App.DatabaseConfig;

public class StudentLandingPage {

    static Scanner cin = new Scanner(System.in);

    public static void displayStudentLandingPage(String username) throws SQLException {
        System.out.println("!!!WELCOME " + username + "!!!");
        displayTextbooksAndContents(username);
        System.out.println("\n1.View a section\n2.View participation activity point\n3.Logout");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                //System.out.println("View a section selected");
                ViewSection.displayViewSectionPage(username);

                break;
            case 2:
                //System.out.println("View participation activity point selected");
                ViewActivityPoint.displayActivityPoints(username);
                break;
            case 3:
                System.out.println("You have logged out successfully.");
                App.displayHomePage();
                break;
        }
    }

    public static void displayTextbooksAndContents(String username) {
        String studentID = getStudentID(username);
        if (studentID == null) {
            System.out.println("Student not found.");
            return;
        }

        Set<Integer> displayedTextbooks = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetRegisteredCourses(?)}");
            callableStatement.setString(1, studentID);
            ResultSet rsCourses = callableStatement.executeQuery();

            while (rsCourses.next()) {
                String courseID = rsCourses.getString("registered_course_id");
                displayTextbookDetails(connection, courseID, displayedTextbooks);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getStudentID(String username) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetStudentID(?, ?)}");
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, Types.CHAR);
            callableStatement.execute();
            return callableStatement.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void displayTextbookDetails(Connection connection, String courseID, Set<Integer> displayedTextbooks) {
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetTextbookDetails(?)}");
            callableStatement.setString(1, courseID);
            ResultSet rsTextbook = callableStatement.executeQuery();

            if (rsTextbook.next()) {
                int textbookID = rsTextbook.getInt("textbook_id");
                if(!displayedTextbooks.contains(textbookID)) {
                    String textbookTitle = rsTextbook.getString("title");
                    System.out.println(textbookTitle);
                    displayedTextbooks.add(textbookID);

                    displayChaptersAndSections(connection, textbookID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayChaptersAndSections(Connection connection, int textbookID) {
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetChaptersAndSections(?)}");
            callableStatement.setInt(1, textbookID);
            ResultSet rsChaptersSections = callableStatement.executeQuery();

            String currentChapter = "";
            while (rsChaptersSections.next()) {
                String chapterID = rsChaptersSections.getString("chapter_id");
                String chapterTitle = rsChaptersSections.getString("chapter_title");
                String sectionID = rsChaptersSections.getString("section_id");
                String sectionTitle = rsChaptersSections.getString("section_title");

                if (!chapterID.equals(currentChapter)) {
                    System.out.println("    " + chapterTitle);
                    currentChapter = chapterID;
                }

                if (sectionTitle != null) {
                    System.out.println("        " + sectionTitle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

