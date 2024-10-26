package App;

import java.util.Scanner;

public class FacultyLogin {
    static Scanner cin = new Scanner(System.in);

    public static void displayFacultyLoginPage() {
        System.out.println("Faculty Login Page");
        System.out.println("\n1.Sign-in\n2.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                // Add sign-in logic here
                System.out.println("Sign-in selected");
                break;
            case 2:
                App.displayHomePage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayFacultyLoginPage();
                break;
        }
    }
}
