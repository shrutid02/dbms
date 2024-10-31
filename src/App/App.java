package App;

import App.Faculty.FacultyLogin;

import java.sql.*;
import java.util.Scanner;

public class App {

    static Scanner cin = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        getConnection();
        displayHomePage();
    }

    public static Connection getConnection(){

        try {
            // Establish a connection
            return DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);
            //System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    public static void displayHomePage() throws SQLException {
        System.out.println("!!!WELCOME!!!");
        System.out.println("\n1.Admin Login\n2.Faculty Login\n3.TA Login\n4.Student Login\n5.EXIT");
        int c = cin.nextInt();

        switch (c) {
            case 1:
                AdminLogin.displayAdminLoginPage();
                break;
            case 2:
                FacultyLogin.displayFacultyLoginPage();
                break;
            case 3:
                TALogin.displayTALoginPage();
                break;
            case 4:
                StudentLogin.displayStudentLoginPage();
                break;
            case 5:
                System.out.println("Thank you for using our platform!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

}
