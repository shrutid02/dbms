package App.Student;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewSection {
    static Scanner cin = new Scanner(System.in);

    public static void displayViewSectionPage(String username) throws SQLException {
        System.out.println("View Section Page");
        System.out.println("\n1.View Block\n2.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                //System.out.println("View block selected");
                ViewBlock.displayViewBlockPage(username);
                break;
            case 2:
                StudentLandingPage.displayStudentLandingPage(username);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayViewSectionPage(username);
                break;
        }
    }
    
}
