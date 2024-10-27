package App;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FacultyLandingPage {
    static Scanner cin = new Scanner(System.in);

    public static void displayFacultyLandingPage() throws SQLException {
        System.out.println("\n1. Go to Active Course\n2. Go to Evaluation Course\n3. View Courses\n4. Change Password\n5. Logout");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                goToActiveCourse();
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
                displayFacultyLandingPage();
                break;
        }
    }

    private static void displayActiveCourseMenu() {
        System.out.println("\n*** Enter choice (1-7): ");
        System.out.println("1. View Worklist");
        System.out.println("2. Approve Enrollment");
        System.out.println("3. View Students");
        System.out.println("4. Add New Chapter");
        System.out.println("5. Modify Chapters");
        System.out.println("6. Add TA");
        System.out.println("7. Go Back");
    }

    private static void goToActiveCourse() throws SQLException {
        System.out.println("\nEnter Course ID: ");
        String course_id = cin.next();

        while (true) {
            displayActiveCourseMenu();
            int choice = cin.nextInt();

            switch (choice) {
                case 1:
                    viewWorklist(course_id);
                    break;
                case 2:
                    approveEnrollment(course_id);
                    break;
                case 3:
                    //viewStudents(courseId);
                    break;
                case 4:
                    //addNewChapter(courseId, scanner);
                    break;
                case 5:
                    //modifyChapters(courseId, scanner);
                    break;
                case 6:
                    //addTA(courseId, scanner);
                    break;
                case 7:
                    System.out.println("Returning to Faculty Landing page...");
                    displayFacultyLandingPage();
                    return;
                default:
                    System.out.println("Invalid choice. Please chose again.");
                    displayActiveCourseMenu();
            }
        }
    }

    private static void viewWorklist(String course_id) {

        String sql = "{ CALL ViewWorklist(?) }";
        Connection connection = App.getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                stmt.setString(1, course_id);
                // Execute the stored procedure and retrieve the result set
                ResultSet rs = stmt.executeQuery();

                // Check if there are results to display
                if (!rs.isBeforeFirst()) {
                    System.out.println("No pending enrollments found for Course ID: " + course_id);
                } else {
                    System.out.println("\n*****************************************");
                    System.out.println("     Worklist for " + course_id);
                    // Loop through the result set and display the columns
                    while (rs.next()) {
                        String studentId = rs.getString("student_id");
                        String status = rs.getString("status");

                        // Display the result
                        System.out.println("Student ID: " + studentId + ", Status: " + status);
                    }
                    System.out.println("*****************************************");
                }

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving pending enrollments: " + e.getMessage());
        }
    }

    private static void approveEnrollment(String course_id) throws SQLException {

        System.out.print("Enter Student ID: ");
        String student_id = cin.next();

        System.out.println("\n1. Save");
        System.out.println("2. Cancel");
        System.out.print("Enter choice (1-2): ");
        int choice = cin.nextInt();

        if (choice == 1) {
            String sql = "{ CALL ApproveEnrollment(?, ?) }";  // Stored procedure call
            Connection connection = App.getConnection();
            try {
                assert connection != null;
                try (CallableStatement stmt = connection.prepareCall(sql)) {
                    stmt.setString(1, course_id);
                    stmt.setString(2, student_id);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String message = rs.getString("message");
                        System.out.println(message);
                        System.out.println("\nGoing back to previous page...");
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error approving enrollment: " + e.getMessage());
            }
        } else if (choice == 2) {
            System.out.println("Operation cancelled. Going back to the previous page...");
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
        //displayActiveCourseMenu();
    }
}
