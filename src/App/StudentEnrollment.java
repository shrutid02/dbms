package App;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Types;

public class StudentEnrollment {

    static Scanner cin = new Scanner(System.in);
    public static void enrollStudent() throws SQLException {
        System.out.println("1.Enroll\n2.Go Back");
        int choice = cin.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter your first name: ");
                String firstName = cin.next();
                System.out.println("Enter your last name: ");
                String lastName = cin.next();
                System.out.println("Enter your email: ");
                String email = cin.next();
                System.out.println("Enter your password: ");
                String password = cin.next();
                System.out.println("Enter Course Token: ");
                String courseToken = cin.next();
                studentAccountExists(firstName, lastName, email, password, courseToken);
                break;
            case 2:
                StudentLogin.displayStudentLoginPage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                enrollStudent();
                break;
        }
    }

    public static String generateStudentID(String firstName, String lastName){
        java.time.LocalDate currentDate = java.time.LocalDate.now();
        String month = String.format("%02d", currentDate.getMonthValue());
        String year = String.valueOf(currentDate.getYear()).substring(2);

        // Generate StudentID
        String studentID = firstName.substring(0, 2) + lastName.substring(0, 2) + month + year;

        // Print the generated StudentID
        //System.out.println("Generated StudentID: " + studentID);
        return studentID;
    }

    public static void studentAccountExists(String firstName, String lastName, String email, String password, String courseToken) {
        
        String studentID = generateStudentID(firstName, lastName);
        String username = email.substring(0, email.indexOf('@'));
        String fullName = firstName + " " + lastName;
        //System.out.println("Generated Username: " + username);

        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

            // Prepare the callable statement
            String sql = "{CALL CheckStudentAccountExists(?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);

            // Set the input parameter
            callableStatement.setString(1, email);

            // Register the output parameter
            callableStatement.registerOutParameter(2, Types.BOOLEAN);
            callableStatement.registerOutParameter(3, Types.CHAR);


            // Execute the procedure
            callableStatement.execute();

            // Get the output parameter
            boolean accountExists = callableStatement.getBoolean(2);
            String existingStudentID = callableStatement.getString(3);

        // Print the status
            if (accountExists) {
                System.out.println("Your student account already exists.");
                studentID = existingStudentID;
                verifyToken(studentID, courseToken);
            } else {
                //System.out.println("Student account does not exist.");
                createStudentAccount(studentID, fullName, username, password, email);
                verifyToken(studentID, courseToken);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            // Close the resources
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            
    }

    public static void createStudentAccount(String studentID, String fullName, String username, String password, String email){
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

            // Prepare the callable statement
            String sql = "{CALL CreateStudentAccount(?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);

            // Set the input parameters
            callableStatement.setString(1, studentID);
            callableStatement.setString(2, fullName);
            callableStatement.setString(3, username);
            callableStatement.setString(4, password);
            callableStatement.setString(5, email);

            // Execute the procedure
            callableStatement.execute();

            // Print the status
            System.out.println("Your account is created successfully.");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close the resources
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

   
    }

    public static void verifyToken(String studentID, String courseToken){
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

            // Prepare the callable statement
            String sql = "{CALL VerifyCourseToken(?, ?)}";
            callableStatement = connection.prepareCall(sql);

            // Set the input parameter
            callableStatement.setString(1, courseToken);

            // Register the output parameter
            callableStatement.registerOutParameter(2, Types.BOOLEAN);

            // Execute the procedure
            callableStatement.execute();

            // Get the output parameter
            boolean tokenExists = callableStatement.getBoolean(2);

            // Print the status
            String tempCourseToken = "";
            if (tokenExists) {
                //System.out.println("Course token is valid.");
                enrollmentExists(studentID, courseToken);
            } else {
                System.out.println("Course token is invalid. Please try again.");
                System.out.println("Enter Course Token: ");
                tempCourseToken = cin.next();
                verifyToken(studentID, tempCourseToken);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            // Close the resources
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void enrollmentExists(String studentID, String courseToken){
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

            // Prepare the callable statement
            String sql = "{CALL CheckEnrollmentExists(?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);

            // Set the input parameters
            callableStatement.setString(1, studentID);
            callableStatement.setString(2, courseToken);

            // Register the output parameter
            callableStatement.registerOutParameter(3, Types.BOOLEAN);

            // Execute the procedure
            callableStatement.execute();

            // Get the output parameter
            boolean enrollmentExists = callableStatement.getBoolean(3);

            // Print the status
            if (enrollmentExists) {
                System.out.println("You are already enrolled in the course.");
                enrollStudent();
            } else {
                enrollStudentCourse(studentID, courseToken);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            // Close the resources
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void enrollStudentCourse(String studentID, String courseToken){
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);

            // Prepare the callable statement
            String sql = "{CALL EnrollStudentCourse(?, ?)}";
            callableStatement = connection.prepareCall(sql);

            // Set the input parameters
            callableStatement.setString(1, studentID);
            callableStatement.setString(2, courseToken);

            // Execute the procedure
            callableStatement.execute();

            // Print the status
            System.out.println("You have successfully enrolled in the course");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
        finally {
            // Close the resources
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    
}
