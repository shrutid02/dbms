package App;

import java.util.Scanner;

public class AdminLogin {

    static Scanner cin = new Scanner(System.in);

    public static void displayAdminLoginPage() {

        final String adminUsername = "admin";
        final String adminPassword = "password";

        System.out.println("Admin Login Page");
        System.out.println("\n1.Sign-in\n2.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                // Add sign-in logic here
                System.out.println("Enter your username: ");
                String username = cin.next();
                System.out.println("Enter your password: ");
                String password = cin.next();
                if (username.equals(adminUsername) && password.equals(adminPassword)) {
                    AdminLandingPage.displayAdminLandingPage();
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                    displayAdminLoginPage();
                }
                break;
            case 2:
                App.displayHomePage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayAdminLoginPage();
                break;
        }
    }
}