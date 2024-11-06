package App.Resource;

import App.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static App.AdminLandingPage.displayAdminLandingPage;
import static App.App.getConnection;

public class Question {
    static Scanner cin = new Scanner(System.in);

    public static void createQuestion(int textbook_id, String chapter_id, String section_id, String block_id, String unique_activity_id, Runnable caller) throws SQLException {
        System.out.println("\nCreate New Question\n");
        cin = new Scanner(System.in);

        System.out.println("Enter Question ID:");
        String question_id = cin.nextLine();

        System.out.println("Enter Question Text:");
        String questionText = cin.nextLine();

        Options[] options = new Options[4];
        // Option 1
        System.out.println("Enter Option 1 Text:");
        String option1Text = cin.nextLine();

        System.out.println("Enter Option 1 Explanation:");
        String option1Explanation = cin.nextLine();

        System.out.println("Enter Option 1 Label (Correct or Incorrect):");
        String option1Label = cin.nextLine();
        options[0] = new Options(option1Text, option1Explanation, option1Label);

        // Option 2
        System.out.println("Enter Option 2 Text:");
        String option2Text = cin.nextLine();

        System.out.println("Enter Option 2 Explanation:");
        String option2Explanation = cin.nextLine();

        System.out.println("Enter Option 2 Label (Correct or Incorrect):");
        String option2Label = cin.nextLine();
        options[1] = new Options(option2Text, option2Explanation, option2Label);

        // Option 3
        System.out.println("Enter Option 3 Text:");
        String option3Text = cin.nextLine();

        System.out.println("Enter Option 3 Explanation:");
        String option3Explanation = cin.nextLine();

        System.out.println("Enter Option 3 Label (Correct or Incorrect):");
        String option3Label = cin.nextLine();
        options[2] = new Options(option3Text, option3Explanation, option3Label);

        // Option 4
        System.out.println("Enter Option 4 Text:");
        String option4Text = cin.nextLine();

        System.out.println("Enter Option 4 Explanation:");
        String option4Explanation = cin.nextLine();

        System.out.println("Enter Option 4 Label (Correct or Incorrect):");
        String option4Label = cin.nextLine();
        options[3] = new Options(option4Text, option4Explanation, option4Label);

        System.out.println("\n1.Save\n2.Cancel\n3.Landing Page");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                saveQuestion(textbook_id, chapter_id, section_id, block_id, unique_activity_id, question_id, questionText, options);
                caller.run();
                break;
            case 2:
                caller.run();
                break;
            case 3:
                displayAdminLandingPage();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void facultyTACreateQuestion(int textbook_id, String chapter_id, String section_id, String block_id, String unique_activity_id, Runnable caller) throws SQLException {
        System.out.println("\nCreate New Question\n");
        cin = new Scanner(System.in);

        // Step A: Question ID
        System.out.println("Enter Question ID:");
        String question_id = cin.nextLine();

        // Step B: Question Text
        System.out.println("Enter Question Text:");
        String questionText = cin.nextLine();

        Options[] options = new Options[4];

        // Step C, D: Option 1 and Explanation
        System.out.println("Enter Option 1 Text:");
        String option1Text = cin.nextLine();
        System.out.println("Enter Option 1 Explanation:");
        String option1Explanation = cin.nextLine();
        options[0] = new Options(option1Text, option1Explanation);

        // Step E, F: Option 2 and Explanation
        System.out.println("Enter Option 2 Text:");
        String option2Text = cin.nextLine();
        System.out.println("Enter Option 2 Explanation:");
        String option2Explanation = cin.nextLine();
        options[1] = new Options(option2Text, option2Explanation);

        // Step G, H: Option 3 and Explanation
        System.out.println("Enter Option 3 Text:");
        String option3Text = cin.nextLine();
        System.out.println("Enter Option 3 Explanation:");
        String option3Explanation = cin.nextLine();
        options[2] = new Options(option3Text, option3Explanation);

        // Step I, J: Option 4 and Explanation
        System.out.println("Enter Option 4 Text:");
        String option4Text = cin.nextLine();
        System.out.println("Enter Option 4 Explanation:");
        String option4Explanation = cin.nextLine();
        options[3] = new Options(option4Text, option4Explanation);

        // Step K: Correct Answer
        System.out.println("Enter the correct answer (1-4 corresponding to Option 1 to Option 4):");
        int correctAnswerIndex = cin.nextInt();
        cin.nextLine();

        // Display menu options
        System.out.println("\n1. Save\n2. Cancel");
        int choice = cin.nextInt();
        cin.nextLine();

        switch (choice) {
            case 1:
                facultySaveQuestion(textbook_id, chapter_id, section_id, block_id, unique_activity_id, question_id, questionText, options, correctAnswerIndex);
                caller.run();
                break;
            case 2:
                caller.run();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void saveQuestion(int textbook_id, String chapter_id, String section_id, String block_id, String unique_activity_id, String question_id, String questionText, Options[] options) {
        String sql = "INSERT INTO questions (textbook_id, chapter_id, section_id, block_id, unique_activity_id, question_id, question_text, option_1, opt_1_explanation, option_2, opt_2_explanation, option_3, opt_3_explanation, option_4, opt_4_explanation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);
            preparedStatement.setString(3, section_id);
            preparedStatement.setString(4, block_id);
            preparedStatement.setString(5, unique_activity_id);
            preparedStatement.setString(6, question_id);
            preparedStatement.setString(7, questionText);
            preparedStatement.setString(8, options[0].optionText);
            preparedStatement.setString(9, options[0].optionLabel+": "+options[0].optionExplanation);
            preparedStatement.setString(10, options[1].optionText);
            preparedStatement.setString(11, options[1].optionLabel+": "+options[1].optionExplanation);
            preparedStatement.setString(12, options[2].optionText);
            preparedStatement.setString(13, options[2].optionLabel+": "+options[2].optionExplanation);
            preparedStatement.setString(14, options[3].optionText);
            preparedStatement.setString(15, options[3].optionLabel+": "+options[3].optionExplanation);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.printf("New question saved successfully!%n");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void facultySaveQuestion(int textbook_id, String chapter_id, String section_id, String block_id, String unique_activity_id, String question_id, String questionText, Options[] options, int answer) {
        String sql = "INSERT INTO questions (textbook_id, chapter_id, section_id, block_id, unique_activity_id, question_id, question_text, option_1, opt_1_explanation, option_2, opt_2_explanation, option_3, opt_3_explanation, option_4, opt_4_explanation, answer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, textbook_id);
            preparedStatement.setString(2, chapter_id);
            preparedStatement.setString(3, section_id);
            preparedStatement.setString(4, block_id);
            preparedStatement.setString(5, unique_activity_id);
            preparedStatement.setString(6, question_id);
            preparedStatement.setString(7, questionText);
            preparedStatement.setString(8, options[0].optionText);
            preparedStatement.setString(9, options[0].optionExplanation);
            preparedStatement.setString(10, options[1].optionText);
            preparedStatement.setString(11, options[1].optionExplanation);
            preparedStatement.setString(12, options[2].optionText);
            preparedStatement.setString(13, options[2].optionExplanation);
            preparedStatement.setString(14, options[3].optionText);
            preparedStatement.setString(15, options[3].optionExplanation);
            preparedStatement.setInt(15,answer);


            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.printf("New question saved successfully!%n");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static class Options {
        public String optionText;
        public String optionExplanation;
        public String optionLabel;

        private Options(String optionText, String optionExplanation, String optionLabel) {
            this.optionText = optionText;
            this.optionExplanation = optionExplanation;
            this.optionLabel = optionLabel;
        }

        private Options(String optionText, String optionExplanation) {
            this.optionText = optionText;
            this.optionExplanation = optionExplanation;
            this.optionLabel = "";
        }
    }
}
