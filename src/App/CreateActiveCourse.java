package App;

import java.sql.*;
import java.util.Scanner;

public class CreateActiveCourse {
    static Scanner scanner = new Scanner(System.in);
    private static final String COURSE_TYPE = "Active"; // Enum value for course type

    public static void createNewActiveCourse() throws SQLException {
        System.out.println("Admin: Create New Active Course");
        System.out.print("A. Unique Course ID: ");
        String course_id = scanner.next();

        System.out.print("B. Course Name: ");
        String course_name = scanner.next();

        System.out.print("C. Unique ID of the E-textbook: ");
        int textbook_id = scanner.nextInt();

        System.out.print("D. Faculty Member ID: ");
        String faculty_id = scanner.next();

        System.out.print("E. Course Start date (YYYY-MM-DD): ");
        Date start_date = Date.valueOf(scanner.next());

        System.out.print("F. Course End date (YYYY-MM-DD): ");
        Date end_date = Date.valueOf(scanner.next());

        System.out.print("G. Unique Token: ");
        String unique_token = scanner.next();

        System.out.print("H. Course Capacity: ");
        int course_capacity = scanner.nextInt();

        // Display options to save or cancel
        System.out.println("\n Choose an option (1-3): ");
        System.out.println("1. Save");
        System.out.println("2. Cancel");
        System.out.println("3. Landing Page");
        int option = scanner.nextInt();

        switch (option) {
            case 1: // Save
                saveCourse(course_id, course_name, textbook_id, faculty_id, start_date, end_date, unique_token, course_capacity);
                AdminLandingPage.displayAdminLandingPage();
                break;
            case 2: // Cancel
                System.out.println("Input discarded. Going back to previous page.");
                AdminLandingPage.displayAdminLandingPage();
                break;
            case 3: // Landing Page
                System.out.println("Returning to Landing Page...");
                AdminLandingPage.displayAdminLandingPage();
                break;
            default:
                System.out.println("Invalid option. Discarding input.");
        }
    }

    private static void saveCourse(String course_id, String course_name, int textbook_id,
                                   String faculty_id, Date start_date, Date end_date,
                                   String unique_token, int course_capacity) throws SQLException {

        String sql = "{ CALL AddNewCourse(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }"; //using Procedure AddNewCourse

        //String sql = "INSERT INTO courses (course_id, course_name, textbook_id, course_type, faculty_id, ta_id, " + "start_date, end_date, unique_token, course_capacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = App.getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                stmt.setString(1, course_id);
                stmt.setString(2, course_name);
                stmt.setInt(3, textbook_id);
                stmt.setString(4, COURSE_TYPE);
                stmt.setString(5, faculty_id);
                stmt.setString(6, null);
                stmt.setDate(7, start_date);
                stmt.setDate(8, end_date);
                stmt.setString(9, unique_token);
                stmt.setInt(10, course_capacity);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("New Active Course was created successfully!");
                    System.out.println("Going back to previous page... \n");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving course: " + e.getMessage());
        }
    }
}
