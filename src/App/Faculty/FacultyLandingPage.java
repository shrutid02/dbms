package App.Faculty;

import App.App;
import App.UserChangePassword;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FacultyLandingPage {
    static Scanner cin = new Scanner(System.in);

    public static void displayFacultyLandingPage(String faculty_id) throws SQLException {
        System.out.println("""
                
                1. Go to Active Course\
                
                2. Go to Evaluation Course\
                
                3. View Courses\
                
                4. Change Password\
                
                5. Logout""");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                goToActiveCourse(faculty_id);
                break;
            case 2:
                goToEvaluationCourse(faculty_id);
                break;
            case 3:
                goToViewCourses(faculty_id);
                break;
            case 4:
                goToChangePassword(faculty_id);
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

    private static void goToChangePassword(String faculty_id) throws SQLException {
        String role = "Faculty";
        UserChangePassword.changePassowrd(faculty_id, role);
        displayFacultyLandingPage(faculty_id);
    }

    private static void goToViewCourses(String faculty_id) throws SQLException {
        String sql = "{ CALL GetCoursesByFaculty(?) }";  // Stored procedure call
        Connection connection = App.getConnection();
        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                stmt.setString(1, faculty_id);
                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("\nNo courses found for Faculty ID: " + faculty_id);
                } else {
                    System.out.println("\nCourses taught by Faculty ID: " + faculty_id);
                    do {
                        String courseName = rs.getString("course_name");
                        System.out.println("- " + courseName);
                    } while (rs.next());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n1. Go Back");
        int choice = cin.nextInt();
        if(choice == 1) {
            System.out.println("\n Returning to previous page... ");
        }
        displayFacultyLandingPage(faculty_id);
    }

    private static void goToEvaluationCourse(String faculty_id) throws SQLException {
        System.out.println("\nEnter Course ID: ");
        String course_id = cin.next();
        FacultyEvaluationCourseMenu.displayEvaluationCourseMenu(faculty_id, course_id);
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
                    FacultyActiveCourseMenu.displayActiveCourseMenu(faculty_id, course_id);
                } else {
                    System.out.println("\nThe course you entered is not Active. Going back to previous page...");
                    displayFacultyLandingPage(faculty_id);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}