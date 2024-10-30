package App.Faculty;

import App.AdminLandingPage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static App.App.getConnection;

public class FacultyAccount {
    static Scanner scanner = new Scanner(System.in);

    public static void create() throws SQLException {
        System.out.println("Enter details for the faculty account.");

        System.out.println("A. First Name: ");
        String firstName = scanner.next();
        System.out.println("B. Last Name: ");
        String lastName = scanner.next();
        System.out.println("C. Email: ");
        String email = scanner.next();
        System.out.println("D. Password: ");
        String password = scanner.next();

        System.out.println("\n1.Add User\n2.Go Back");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                saveFaculty(firstName,lastName,email,password);
                AdminLandingPage.displayAdminLandingPage();
                break;
            case 2:
                AdminLandingPage.displayAdminLandingPage();
                break;
        }
    }

    private static void saveFaculty(String firstName, String lastName, String email, String password) throws SQLException {

        String sql = "{ CALL saveFaculty(?, ?, ?, ?) }";
        Connection connection = getConnection();

        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, email);
                stmt.setString(4, password);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("New Faculty was added successfully!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving faculty: " + e.getMessage());
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}

