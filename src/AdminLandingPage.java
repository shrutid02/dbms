package App;

import java.util.Scanner;

public class AdminLandingPage {
    static Scanner cin = new Scanner(System.in);

    public static void displayAdminLandingPage() {
        System.out.println("!!!WELCOME ADMIN!!!");
        System.out.println("\n1.Create a Faculty Account\n2.Create E-Textbook\n3.Modify E-Textbooks\n4.Create a New Active Course\n5.Create New Evaluation Course\n6.Logout");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                // Add sign-in logic here
                System.out.println("Create a Faculty Account selected");
                break;
            case 2:
                System.out.println("Create E-Textbook selected");
                break;
            case 3:
                System.out.println("Modify E-Textbooks selected");
                break;
            case 4:
                System.out.println("Create a New Active Course selected");
                break;
            case 5:
                System.out.println("Create New Evaluation Course selected");
                break;
            case 6:
                System.out.println("You have logged out successfully.");
                AdminLogin.displayAdminLoginPage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayAdminLandingPage();
                break;
        }
    }
}
