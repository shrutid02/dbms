package App.Student;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewBlock {
    static Scanner cin = new Scanner(System.in);
    
    public static void displayViewBlockPage(String username) throws SQLException {

        System.out.println("View Block Page");
        System.out.println("\n1.Next/Submit\n2.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                System.out.println("View block selected");
                break;
            case 2:
                ViewSection.displayViewSectionPage(username);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayViewBlockPage(username);
                break;
        }
    }
    
}
