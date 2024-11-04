package App.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    }
    
}
