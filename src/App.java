package App;
import java.sql.*;
import java.util.Scanner;

public class App {

    static Scanner cin = new Scanner(System.in);

    public static void main(String[] args) {
        getConnection();
        displayHomePage();
    }

    public static void getConnection(){
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306/DBMSProject";
        final String DB_USER = "root";
        final String DB_PASSWORD = "Whiterose@59";

        try {
            // Establish a connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public static void displayHomePage(){
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
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

}
