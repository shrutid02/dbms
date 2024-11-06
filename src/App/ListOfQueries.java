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

    public static void query2() {
        String sql = "{CALL GetCourseParticipants()}";
        Connection connection = App.getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                // Execute the stored procedure
                ResultSet resultSet = stmt.executeQuery();

                System.out.println("\nOUTPUT:");
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("**** No course participants found ****");
                } else {
                    System.out.printf("%-20s %-20s %-10s %-30s %-15s%n", "First Name", "Last Name", "Role", "Course Name", "Course ID");
                    System.out.println("-----------------------------------------------------------------------------------------");

                    while (resultSet.next()) {
                        String firstName = resultSet.getString("Name");
                        String lastName = resultSet.getString("LastName");
                        String role = resultSet.getString("Role");
                        String courseName = resultSet.getString("Course");
                        String courseId = resultSet.getString("CourseID");

                        System.out.printf("%-20s %-20s %-10s %-30s %-15s%n", firstName, lastName, role, courseName, courseId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
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

    public static void query4() {
        String sql = "{CALL ListOfQueries4()}";
        Connection connection = App.getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                ResultSet resultSet = stmt.executeQuery();

                System.out.println("\nOUTPUT:");
                System.out.printf("%-15s | %-15s%n", "Course ID", "Total Waiting");
                System.out.println("-----------------------------");

                if (resultSet.next()) {
                    String courseId = resultSet.getString("course_id");
                    int totalWaiting = resultSet.getInt("TotalWaitingList");

                    System.out.printf("%-15s | %-15d%n", courseId, totalWaiting);
                } else {
                    System.out.println("No waiting list entries found.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void query5() {
        String sql = "{CALL GetChapterContent(101, 'chap02')}";
        Connection connection = App.getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                ResultSet resultSet = stmt.executeQuery();

                System.out.println("\nOUTPUT:");
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("**** No content found for the specified chapter ****");
                } else {
                    System.out.printf("%-25s %-15s %-20s %-20s %-30s %-20s %-50s %-50s %-50s %-50s %-50s %-10s%n",
                            "Section Title", "Block Type", "Block Content", "Activity ID", "Question ID",
                            "Question Text", "Option 1", "Option 1 Explanation", "Option 2", "Option 2 Explanation",
                            "Option 3", "Option 3 Explanation", "Option 4", "Option 4 Explanation", "Answer");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------");

                    while (resultSet.next()) {
                        String sectionTitle = resultSet.getString("section_title");
                        String blockType = resultSet.getString("block_type");
                        String blockContent = resultSet.getString("block_content");
                        String activityId = resultSet.getString("activity_id");
                        String questionId = resultSet.getString("question_id");
                        String questionText = resultSet.getString("question_text");
                        String option1 = resultSet.getString("option_1");
                        String option1Explanation = resultSet.getString("opt_1_explanation");
                        String option2 = resultSet.getString("option_2");
                        String option2Explanation = resultSet.getString("opt_2_explanation");
                        String option3 = resultSet.getString("option_3");
                        String option3Explanation = resultSet.getString("opt_3_explanation");
                        String option4 = resultSet.getString("option_4");
                        String option4Explanation = resultSet.getString("opt_4_explanation");
                        String answer = resultSet.getString("answer");

                        System.out.printf("%-25s %-15s %-20s %-20s %-30s %-20s %-50s %-50s %-50s %-50s %-50s %-10s%n",
                                sectionTitle, blockType, blockContent, activityId, questionId,
                                questionText, option1, option1Explanation, option2, option2Explanation,
                                option3, option3Explanation, option4, option4Explanation, answer);
                    }

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }

    public static void query6() {
        String sql = "{CALL GetIncorrectAnswersForQ2()}";
        Connection connection = App.getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                ResultSet resultSet = stmt.executeQuery();

                System.out.println("\nOUTPUT:");
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("**** No incorrect answers found for the specified question ****");
                } else {
                    System.out.printf("%-30s %-100s%n", "Incorrect Option", "Explanation");
                    System.out.println("-----------------------------------------------------------------------------------------------");

                    while (resultSet.next()) {
                        String incorrectOption = resultSet.getString("IncorrectOption");
                        String explanation = resultSet.getString("Explanation");

                        System.out.printf("%-30s %-100s%n", incorrectOption, explanation);
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

}
