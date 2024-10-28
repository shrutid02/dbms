package App;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class FacultyLandingPage {
    static Scanner cin = new Scanner(System.in);

    public static void displayFacultyLandingPage(String faculty_id) throws SQLException {
        System.out.println("\n1. Go to Active Course\n2. Go to Evaluation Course\n3. View Courses\n4. Change Password\n5. Logout");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                goToActiveCourse(faculty_id);
                break;
            case 2:
                System.out.println("Create E-Textbook selected");
                break;
            case 3:
                System.out.println("Modify E-Textbooks selected");
                break;
            case 4:
                System.out.println("Create a New Active Course selected");
                CreateActiveCourse.createNewActiveCourse();
                break;
            case 5:
                System.out.println("You have logged out successfully.");
                App.displayHomePage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayFacultyLandingPage(null);
                break;
        }
    }

    private static void goToActiveCourse(String faculty_id) throws SQLException {
        System.out.println("\nEnter Course ID: ");
        String course_id = cin.next();

        String sql = "{ CALL CheckCourseType(?, ?) }";  // Stored procedure call
        Connection connection = App.getConnection();
        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                stmt.setString(1, course_id);
                stmt.registerOutParameter(2, java.sql.Types.VARCHAR); // Registering the output parameter

                stmt.execute();
                String message = stmt.getString(2);
                System.out.println(message);

                if (message.equals("Active course, you may continue.")) {
                    DisplayActiveCourseMenu.displayActiveCourseMenu(faculty_id, course_id);
                } else {
                    System.out.println("\nGoing back to previous page...");
                    displayFacultyLandingPage(faculty_id);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}