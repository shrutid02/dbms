package App.Faculty;

import App.Resource.Chapter;

import java.sql.SQLException;
import java.util.Scanner;

public class FacultyEvaluationCourseMenu {
    static Scanner cin = new Scanner(System.in);

    public static void displayEvaluationCourseMenu(String faculty_id, String course_id) throws SQLException {
        System.out.println("\n*** Enter choice (1-3): ");
        System.out.println("1. Add New Chapter");
        System.out.println("2. Modify Chapters");
        System.out.println("3. Go Back");

        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                Chapter.FacultyAddChapter(course_id,() -> {
                    try {
                        displayEvaluationCourseMenu(faculty_id,course_id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                displayEvaluationCourseMenu(faculty_id,course_id);
                break;
            case 2:
                Chapter.facultyModifyChapter(course_id,() -> {
                    try {
                        displayEvaluationCourseMenu(faculty_id,course_id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                displayEvaluationCourseMenu(faculty_id,course_id);
                break;
            case 3:
                System.out.println("Returning to Faculty Landing page...");
                FacultyLandingPage.displayFacultyLandingPage(null);
                return;
            default:
                System.out.println("Invalid choice. Please chose again.");
                displayEvaluationCourseMenu(faculty_id, course_id);
        }
    }
}
