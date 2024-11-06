package App.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import App.DatabaseConfig;

public class ViewBlock {
    static Scanner cin = new Scanner(System.in);
    
    public static void displayViewBlockPage(String username, int textbookID, String chapterID, String sectionID, Map<Integer, Map<String, Set<String>>> displayedData) throws SQLException {

        List<Block> blocks = getBlocks(textbookID, chapterID, sectionID);
        int currentBlockIndex = 0;

        while (currentBlockIndex < blocks.size()) {
            Block block = blocks.get(currentBlockIndex);
            if (!block.isHidden()) {
                if (block.getType().equals("text") || block.getType().equals("picture")) {
                    System.out.println(block.getContent());
                } else if (block.getType().equals("activity")) {
                    displayActivityQuestions(username, textbookID, chapterID, sectionID, block.getBlockID());
                }
            }

            System.out.println("\n1.Next/Submit\n2.Go Back");
            int choice = cin.nextInt();

            switch (choice) {
                case 1:
                    currentBlockIndex++;
                    if (currentBlockIndex >= blocks.size()) {
                        System.out.println("End of blocks. Returning to section view.");
                        ViewSection.displayViewSectionPage(username, displayedData);
                        return;
                    }
                    break;
                case 2:
                    ViewSection.displayViewSectionPage(username, displayedData);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static List<Block> getBlocks(int textbookID, String chapterID, String sectionID) throws SQLException {
        List<Block> blocks = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetBlocks(?, ?, ?)}");
            callableStatement.setInt(1, textbookID);
            callableStatement.setString(2, chapterID);
            callableStatement.setString(3, sectionID);
            ResultSet rsBlocks = callableStatement.executeQuery();

            while (rsBlocks.next()) {
                String blockID = rsBlocks.getString("block_id");
                String type = rsBlocks.getString("type");
                String content = rsBlocks.getString("content");
                String hidden = rsBlocks.getString("hidden");
                blocks.add(new Block(blockID, type, content, hidden));
            }
        }

        return blocks;
    }

    private static void displayActivityQuestions(String username, int textbookID, String chapterID, String sectionID, String blockID) throws SQLException {
        String studentID = getStudentID(username);
        List<String> courseIDs = getCourseIDs(studentID, textbookID);

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetQuestions(?, ?, ?, ?)}");
            callableStatement.setInt(1, textbookID);
            callableStatement.setString(2, chapterID);
            callableStatement.setString(3, sectionID);
            callableStatement.setString(4, blockID);
            ResultSet rsQuestions = callableStatement.executeQuery();

            while (rsQuestions.next()) {
                String questionID = rsQuestions.getString("question_id");
                String questionText = rsQuestions.getString("question_text");
                String option1 = rsQuestions.getString("option_1");
                String option2 = rsQuestions.getString("option_2");
                String option3 = rsQuestions.getString("option_3");
                String option4 = rsQuestions.getString("option_4");
                String opt1Explanation = rsQuestions.getString("opt_1_explanation");
                String opt2Explanation = rsQuestions.getString("opt_2_explanation");
                String opt3Explanation = rsQuestions.getString("opt_3_explanation");
                String opt4Explanation = rsQuestions.getString("opt_4_explanation");
                int correctAnswer = rsQuestions.getInt("answer");
                String uniqueActivityID = rsQuestions.getString("unique_activity_id");

                System.out.println("Question: " + questionText);
                System.out.println("Option1: " + option1);
                System.out.println("Option2: " + option2);
                System.out.println("Option3: " + option3);
                System.out.println("Option4: " + option4);

                System.out.print("Your Answer: ");
                String answerInput = cin.nextLine().trim();
                int answer = answerInput.isEmpty() ? 0 : Integer.parseInt(answerInput);

                int points = 0;
                if (answer == 0) {
                    System.out.println("Not attempted.");
                } else if (answer == correctAnswer) {
                    points = 3;
                    System.out.println("Correct! Explanation: " + getExplanation(answer, opt1Explanation, opt2Explanation, opt3Explanation, opt4Explanation));
                } else {
                    points = 1;
                    System.out.println("Incorrect. Explanation: " + getExplanation(answer, opt1Explanation, opt2Explanation, opt3Explanation, opt4Explanation));
                }

                for (String courseID : courseIDs) {
                    logStudentActivity(studentID, courseID, textbookID, chapterID, sectionID, blockID, uniqueActivityID, questionID, points);
                }
                System.out.println();
            }
        }
    }

    private static String getStudentID(String username) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetStudentID(?, ?)}");
            callableStatement.setString(1, username);
            callableStatement.registerOutParameter(2, Types.CHAR);
            callableStatement.execute();
            return callableStatement.getString(2);
        }
    }

    private static List<String> getCourseIDs(String studentID, int textbookID) throws SQLException {
        List<String> courseIDs = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetCourseID(?, ?)}");
            callableStatement.setString(1, studentID);
            callableStatement.setInt(2, textbookID);
            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                courseIDs.add(rs.getString("course_id"));
            }
        }

        return courseIDs;
    }

    private static void logStudentActivity(String studentID, String courseID, int textbookID, String chapterID, String sectionID, String blockID, String uniqueActivityID, String questionID, int points) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{CALL LogStudentActivity(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, studentID);
            callableStatement.setString(2, courseID);
            callableStatement.setInt(3, textbookID);
            callableStatement.setString(4, chapterID);
            callableStatement.setString(5, sectionID);
            callableStatement.setString(6, blockID);
            callableStatement.setString(7, uniqueActivityID);
            callableStatement.setString(8, questionID);
            callableStatement.setInt(9, points);
            callableStatement.execute();
        }
    }

    private static String getExplanation(int answer, String opt1Explanation, String opt2Explanation, String opt3Explanation, String opt4Explanation) {
        switch (answer) {
            case 1:
                return opt1Explanation;
            case 2:
                return opt2Explanation;
            case 3:
                return opt3Explanation;
            case 4:
                return opt4Explanation;
            default:
                return "No explanation available.";
        }
    }
}

class Block {
    private String blockID;
    private String type;
    private String content;
    private String hidden;

    public Block(String blockID, String type, String content, String hidden) {
        this.blockID = blockID;
        this.type = type;
        this.content = content;
        this.hidden = hidden;
    }

    public String getBlockID() {
        return blockID;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public boolean isHidden() {
        return "yes".equalsIgnoreCase(hidden);
    }
}


        /*List<Block> blocks = getBlocks(textbookID, chapterID, sectionID);
        int currentBlockIndex = 0;

        while (currentBlockIndex < blocks.size()) {
            Block block = blocks.get(currentBlockIndex);
            if (!block.isHidden()) {
                if (block.getType().equals("text") || block.getType().equals("picture")) {
                    System.out.println(block.getContent());
                } else if (block.getType().equals("activity")) {
                    System.out.println("This is an activity");
                }
            }

            System.out.println("\n1.Next/Submit\n2.Go Back");
            int choice = cin.nextInt();

            switch (choice) {
                case 1:
                    currentBlockIndex++;
                    if (currentBlockIndex >= blocks.size()) {
                        System.out.println("End of blocks. Returning to section view.");
                        ViewSection.displayViewSectionPage(username, displayedData);
                        return;
                    }
                    break;
                case 2:
                    ViewSection.displayViewSectionPage(username, displayedData);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static List<Block> getBlocks(int textbookID, String chapterID, String sectionID) throws SQLException {
        List<Block> blocks = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASSWORD)) {
            CallableStatement callableStatement = connection.prepareCall("{CALL GetBlocks(?, ?, ?)}");
            callableStatement.setInt(1, textbookID);
            callableStatement.setString(2, chapterID);
            callableStatement.setString(3, sectionID);
            ResultSet rsBlocks = callableStatement.executeQuery();

            while (rsBlocks.next()) {
                String blockID = rsBlocks.getString("block_id");
                String type = rsBlocks.getString("type");
                String content = rsBlocks.getString("content");
                String hidden = rsBlocks.getString("hidden");
                blocks.add(new Block(blockID, type, content, hidden));
            }
        }

        return blocks;
    }
}

class Block {
    private String blockID;
    private String type;
    private String content;
    private String hidden;

    public Block(String blockID, String type, String content, String hidden) {
        this.blockID = blockID;
        this.type = type;
        this.content = content;
        this.hidden = hidden;
    }

    public String getBlockID() {
        return blockID;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public boolean isHidden() {
        return "yes".equalsIgnoreCase(hidden);
    }*/
    

