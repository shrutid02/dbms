package App;

import java.sql.*;
import App.Faculty.FacultyAccount;
import App.resource.Textbook;

import java.util.Scanner;

public class StudentLandingPage {

    static Scanner cin = new Scanner(System.in);

    public static void displayStudentLandingPage(String username) throws SQLException {
        System.out.println("!!!WELCOME " + username + "!!!");
        System.out.println("\n1.View a section\n2.View participation activity point\n3.Logout");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Create a Faculty Account");

                break;
            case 2:
                System.out.println("Create E-Textbook");

                break;
            case 3:
                System.out.println("You have logged out successfully.");
                App.displayHomePage();
                break;
        }
    }
}
