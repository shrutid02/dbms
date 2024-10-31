package App;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static App.Faculty.FacultyLandingPage.displayFacultyLandingPage;

public class UserChangePassword {
    static Scanner scanner = new Scanner(System.in);

    public static void changePassowrd(String user_id, String role) throws SQLException {
        System.out.print("Enter Current Password: ");
        String currentPassword = scanner.nextLine();
        System.out.print("Enter New Password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Confirm New Password: ");
        String confirmPassword = scanner.nextLine();

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("New passwords do not match. Please try again.");
            return;
        }

        System.out.println("\n1. Update\n2. Go Back\nEnter choice:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            updatePasswordInDatabase(user_id, currentPassword, newPassword, role);
        }

        System.out.println("\nReturning to previous page...");
        displayFacultyLandingPage(user_id);
    }

    private static void updatePasswordInDatabase(String user_id, String currentPassword, String newPassword, String role) {
        String sql = "{ CALL ChangePassword(?, ?, ?, ?, ?) }";
        Connection connection = App.getConnection();
        try {
            assert connection != null;
            try (CallableStatement stmt = connection.prepareCall(sql)) {
                stmt.setString(1, user_id);
                stmt.setString(2, currentPassword);
                stmt.setString(3, newPassword);
                stmt.setString(4, role);

                stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
                stmt.execute();

                String message = stmt.getString(5);
                System.out.println(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
