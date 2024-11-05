package App;

import java.sql.*;

public class ListOfQueries {

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
