package App.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import App.DatabaseConfig;

public class ViewActivityPoint {
    static Scanner cin = new Scanner(System.in);

    public static void displayActivityPoints(String username) throws SQLException {
        System.out.println("!!!YOUR ACTIVITY POINTS!!!");
        getActivityPoints(username);
        System.out.println("\n1.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                //System.out.println("View block selected");
                StudentLandingPage.displayStudentLandingPage(username);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayActivityPoints(username);
                break;
        }
    }

    public static int getActivityPoints(String username) {
        
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

            // Prepare the callable statement
            String sql = "{CALL GetActivityPoints(?)}";
            callableStatement = connection.prepareCall(sql);

            // Set the input parameter
            callableStatement.setString(1, username);

            // Execute the procedure
            resultSet = callableStatement.executeQuery();

            // Display the results
            System.out.println("Course Name | Total Points");
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                int totalPoints = resultSet.getInt("total_participation_points");
                System.out.println(courseName + " | " + totalPoints);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
    
}
