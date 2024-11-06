package App.Faculty;

import App.App;
import App.Resource.Chapter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static App.Faculty.FacultyLandingPage.displayFacultyLandingPage;

public class FacultyActiveCourseMenu {
    static Scanner cin = new Scanner(System.in);

    public static void displayActiveCourseMenu(String faculty_id, String course_id) throws SQLException {
        System.out.println("\n*** Enter choice (1-7): ");
        System.out.println("""
                1. View Worklist
                2. Approve Enrollment
                3. View Students
                4. Add New Chapter
                5. Modify Chapters
                6. Adda TA
                7. Go Back""");

        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                viewWorklist(course_id);
                displayActiveCourseMenu(faculty_id, course_id);
                break;
            case 2:
                approveEnrollment(course_id, faculty_id);
                displayActiveCourseMenu(faculty_id, course_id);
                break;
            case 3:
                viewStudents(faculty_id, course_id);
                displayActiveCourseMenu(faculty_id, course_id);
                break;
            case 4:
                Chapter.FacultyAddChapter(course_id,() -> {
                    try {
                        displayActiveCourseMenu(faculty_id,course_id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                displayActiveCourseMenu(faculty_id, course_id);
                break;
            case 5:
                Chapter.facultyModifyChapter(course_id,() -> {
                    try {
                        displayActiveCourseMenu(faculty_id,course_id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                displayActiveCourseMenu(faculty_id, course_id);
                break;
            case 6:
                addTA(course_id, faculty_id);
                break;
            case 7:
                System.out.println("Going back...");
                displayFacultyLandingPage(faculty_id);
                return;
            default:
                System.out.println("Invalid choice. Please chose again.");
                displayActiveCourseMenu(faculty_id, course_id);
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
            System.out.println("Error retrieving worklist : " + e.getMessage());
        }
    }

    private static void approveEnrollment(String course_id, String faculty_id) throws SQLException {

        System.out.print("Enter Student ID: ");
        String student_id = cin.next();

        System.out.println("\n1. Save\n2. Cancel");
        System.out.print("Enter choice (1-2): ");
        int choice = cin.nextInt();

        if (choice == 1) {
            String sql = "{ CALL ApproveEnrollment(?, ?, ?) }";  // Stored procedure call
            Connection connection = App.getConnection();
            try {
                assert connection != null;
                try (CallableStatement stmt = connection.prepareCall(sql)) {
                    stmt.setString(1, faculty_id);
                    stmt.setString(2, course_id);
                    stmt.setString(3, student_id);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        String message = rs.getString("message");
                        System.out.println(message);
                        System.out.println("\nGoing back to previous page...");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else if (choice == 2) {
            System.out.println("Operation cancelled. Going back to the previous page...");
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
        //displayActiveCourseMenu();
    }

    private static void viewStudents(String faculty_id, String course_id) throws SQLException {
        String sql = "{ CALL GetStudentsByFacultyAndCourse(?, ?) }";  // Stored procedure call
        Connection connection = App.getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                stmt.setString(1, faculty_id);
                stmt.setString(2, course_id);

                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("\nNo students found for Faculty ID: " + faculty_id + " in Course ID: " + course_id);
                } else {
                    System.out.println("\nStudents enrolled in Course ID '" + course_id + "' & taught by Faculty ID '" + faculty_id + "' :-");
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
                displayActiveCourseMenu(faculty_id, course_id);
            }
        }
    }

    private static void addTA(String course_id, String faculty_id) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter First Name: ");
        String first_name = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String last_name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Default Password: ");
        String password = scanner.nextLine();

        String sql = "{CALL AddTA(?, ?, ?, ?, ?, ?, ?)}";
        Connection connection = App.getConnection();
        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                stmt.setString(1, course_id);
                stmt.setString(2, faculty_id);
                stmt.setString(3, first_name);
                stmt.setString(4, last_name);
                stmt.setString(5, email);
                stmt.setString(6, password);
                stmt.registerOutParameter(7, java.sql.Types.VARCHAR); // Registering the output parameter

                stmt.execute();
                String message = stmt.getString(7); // Retrieving the output parameter
                System.out.println(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
