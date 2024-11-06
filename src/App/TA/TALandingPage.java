package App.TA;

import App.App;
import App.UserChangePassword;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TALandingPage {
    static Scanner cin = new Scanner(System.in);
    public static String gbTAId = null;

    public static void displayTALandingPage(String ta_id) throws SQLException {
        gbTAId = ta_id;
        System.out.println("\n1. Go to Active Course\n2. View Courses\n3. Change Password\n4. Logout");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                goToActiveCourse(ta_id);
                break;
            case 2:
                goToViewCourses(ta_id);
                break;
            case 3:
                goToChangePassword(ta_id);
                break;
            case 4:
                System.out.println("You have logged out successfully.");
                gbTAId = null;
                App.displayHomePage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayTALandingPage(ta_id);
                break;
        }
    }

    private static void goToChangePassword(String ta_id) throws SQLException {
        String role = "TA";
        UserChangePassword.changePassowrd(ta_id, role);
        displayTALandingPage(ta_id);
    }

    private static void goToViewCourses(String ta_id) throws SQLException {
        String sql = "{ CALL GetCoursesByTA(?) }";  // Stored procedure call
        Connection connection = App.getConnection();
        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                stmt.setString(1, ta_id);
                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("\nNo courses found for TA ID: " + ta_id);
                } else {
                    System.out.println("\nCourses for TA ID: " + ta_id);
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
        displayTALandingPage(ta_id);
    }

    private static void goToActiveCourse(String ta_id) {
        System.out.println("\nEnter Course ID: ");
        String course_id = cin.next();

        String sql = "{ CALL CheckCourseType(?, ?) }";
        Connection connection = App.getConnection();
        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                stmt.setString(1, course_id);
                stmt.registerOutParameter(2, java.sql.Types.VARCHAR);

                stmt.execute();
                String message = stmt.getString(2);
                System.out.println(message);

                if (message.equals("Active course, you may continue.")) {
                    TAActiveCourseMenu.displayActiveCourseMenu(ta_id, course_id);
                } else {
                    System.out.println("\nThe course you entered is not Active. Going back to previous page...");
                    displayTALandingPage(ta_id);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
