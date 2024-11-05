package App;

import java.sql.*;
import java.util.Scanner;

public class ListOfQueries {
    public static void query1() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the textbook ID: \n");

        int textbookId = scanner.nextInt();

        String sql = "{CALL CountSectionsInFirstChapter(?)}";
        Connection connection = App.getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                // Set the input parameter
                stmt.setInt(1, textbookId);

                ResultSet resultSet = stmt.executeQuery();

                System.out.println("\nOUTPUT:");
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("**** No sections found ****");
                } else {
                    System.out.printf("Textbook ID: %d%n", textbookId);
                    System.out.println("----------------------------------");

                    while (resultSet.next()) {
                        int sectionCount = resultSet.getInt("section_count");
                        System.out.printf("Number of Sections in 'chap01': %d%n", sectionCount);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }

    public static void query7() {
        String sql = "{CALL ListOfQueries7()}";
        Connection connection = App.getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                ResultSet resultSet = stmt.executeQuery();

                System.out.println("\nOUTPUT:");
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("**** No results found ****");
                } else {
                    System.out.println("+------------------------------------------------+-------------------------+------------------+-------------------------+------------------+");
                    System.out.printf("| %-46s | %-23s | %-16s | %-23s | %-16s |%n", "Textbook Title", "Instructor1 ID", "Status", "Instructor2 ID", "Status");
                    System.out.println("+------------------------------------------------+-------------------------+------------------+-------------------------+------------------+");

                    while (resultSet.next()) {
                        String title = resultSet.getString("title");
                        String statusByInstructor1 = resultSet.getString("status_by_instructor1");
                        String instructor1 = resultSet.getString("instructor1");
                        String statusByInstructor2 = resultSet.getString("status_by_instructor2");
                        String instructor2 = resultSet.getString("instructor2");

                        System.out.printf("| %-46s | %-23s | %-16s | %-23s | %-16s |%n",
                                title, instructor1, statusByInstructor1, instructor2, statusByInstructor2);
                        System.out.println("+------------------------------------------------+-------------------------+------------------+-------------------------+------------------+");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void query3() {
        String sql = "{CALL ListOfQueries3()}";
        Connection connection = App.getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                ResultSet resultSet = stmt.executeQuery();

                System.out.println("\nOUTPUT:");
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("**** No results found ****");
                } else {
                    System.out.printf("%-15s | %-25s | %-15s%n", "Course ID", "Faculty Name", "Total Students");
                    System.out.println("---------------------------------------------------");

                    while (resultSet.next()) {
                        String courseId = resultSet.getString("course_id");
                        String facultyName = resultSet.getString("FacultyName");
                        int totalStudents = resultSet.getInt("TotalStudents");

                        System.out.printf("%-15s | %-25s | %-15d%n", courseId, facultyName, totalStudents);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
