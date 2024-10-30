package App;

import App.Faculty.FacultyAccount;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminLandingPage {
    static Scanner cin = new Scanner(System.in);

    public static void displayAdminLandingPage() throws SQLException {
        System.out.println("!!!WELCOME ADMIN!!!");
        System.out.println("\n1.Create a Faculty Account\n2.Create E-Textbook\n3.Modify E-Textbooks\n4.Create a New Active Course\n5.Create New Evaluation Course\n6.Logout");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Create a Faculty Account");
                FacultyAccount.create();
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
                System.out.println("Create New Evaluation Course selected");
                CreateEvaluationCourse.createNewEvaluationCourse();
                break;
            case 6:
                System.out.println("You have logged out successfully.");
                App.displayHomePage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayAdminLandingPage();
                break;
        }
    }
}
