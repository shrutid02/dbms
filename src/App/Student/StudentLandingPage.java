package App.Student;

import java.sql.*;


import java.util.Scanner;

import App.App;

public class StudentLandingPage {

    static Scanner cin = new Scanner(System.in);

    public static void displayStudentLandingPage(String username) throws SQLException {
        System.out.println("!!!WELCOME " + username + "!!!");
        System.out.println("\n1.View a section\n2.View participation activity point\n3.Logout");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                //System.out.println("View a section selected");
                ViewSection.displayViewSectionPage(username);

                break;
            case 2:
                //System.out.println("View participation activity point selected");
                ViewActivityPoint.displayActivityPoints(username);
                break;
            case 3:
                System.out.println("You have logged out successfully.");
                App.displayHomePage();
                break;
        }
    }
}
