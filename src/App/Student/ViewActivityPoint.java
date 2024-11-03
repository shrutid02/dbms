package App.Student;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewActivityPoint {
    static Scanner cin = new Scanner(System.in);

    public static void displayActivityPoints(String username) throws SQLException {
        System.out.println("View Participation Activity points Page");
        System.out.println("\n1.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                //System.out.println("View block selected");
                StudentLandingPage.displayStudentLandingPage(username);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayActivityPoints(username);
                break;
        }
    }
    
}
