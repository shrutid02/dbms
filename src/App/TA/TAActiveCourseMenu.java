package App.TA;

import App.App;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static App.TA.TALandingPage.displayTALandingPage;

public class TAActiveCourseMenu {
    static Scanner cin = new Scanner(System.in);

    public static void displayActiveCourseMenu(String ta_id, String course_id) throws SQLException {
        System.out.println("\n*** Enter choice (1-4): ");
        System.out.println("1. View Students");
        System.out.println("2. Add New Chapter");
        System.out.println("3. Modify Chapters");
        System.out.println("4. Go Back");

        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                viewStudents(ta_id, course_id);
                displayTALandingPage(ta_id);
                break;
            case 2:
                //addNewChapter(courseId, scanner);
                break;
            case 3:
                //modifyChapters(courseId, scanner);
                break;
            case 4:
                System.out.println("Going back to previous page...");
                displayTALandingPage(ta_id);
                return;
            default:
                System.out.println("Invalid choice. Please chose again.");
                displayActiveCourseMenu(ta_id, course_id);
        }
    }

    private static void viewStudents(String ta_id, String course_id) throws SQLException {
        String sql = "{ CALL GetStudentsByTAAndCourse(?, ?) }";  // Stored procedure call
        Connection connection = App.getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                stmt.setString(1, ta_id);
                stmt.setString(2, course_id);

                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("\nNo students found for TA ID: " + ta_id + " in Course ID: " + course_id);
                } else {
                    System.out.println("\nStudents enrolled in Course ID '" + course_id + "' & managed by TA ID '" + ta_id + "' :-");
                    System.out.printf("%-15s %-25s\n", "Student ID", "Student Name");

                    do {
                        String studentId = rs.getString("student_id");
                        String studentName = rs.getString("full_name");
                        System.out.printf("%-15s %-25s\n", studentId, studentName);
                    } while (rs.next());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("\n1. Go Back");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("\nReturning to the previous page...");
                displayTALandingPage(ta_id);
            }
        }
    }
}
