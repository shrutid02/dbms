package App.Student;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ViewSection {
    static Scanner cin = new Scanner(System.in);

    public static void displayViewSectionPage(String username, Map<Integer, Map<String, Set<String>>> displayedData) throws SQLException {
        System.out.println("Enter the textbook ID: ");
        int textbookID = cin.nextInt();

        if (!displayedData.containsKey(textbookID)) {
            System.out.println("Invalid textbook ID.");
            displayViewSectionPage(username, displayedData);
            return;
        }

        System.out.println("Enter the chapter ID: ");
        String chapterID = String.format("chap%02d", cin.nextInt());

        if (!displayedData.get(textbookID).containsKey(chapterID)) {
            System.out.println("Invalid chapter ID.");
            displayViewSectionPage(username, displayedData);
            return;
        }

        System.out.println("Enter the section ID: ");
        String sectionID = String.format("Sec%02d", cin.nextInt());

        if (!displayedData.get(textbookID).get(chapterID).contains(sectionID)) {
            System.out.println("Invalid section ID.");
            displayViewSectionPage(username, displayedData);
            return;
        }

        System.out.println("Valid input. Displaying section...");
        System.out.println("\n1.View Block\n2.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                //System.out.println("View block selected");
                ViewBlock.displayViewBlockPage(username, textbookID, chapterID, sectionID, displayedData);
                break;
            case 2:
                StudentLandingPage.displayStudentLandingPage(username);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayViewSectionPage(username, displayedData);
                break;
        }
        // Continue with the rest of your code to display the section
    }

    /*public static void displayViewSectionPage(String username, Set<Integer> displayedTextbooks) throws SQLException {
        System.out.println("View Section Page");
        System.out.println("Enter the textbook ID: ");
        int textbookID = cin.nextInt();
        System.out.println("Enter the chapter ID: ");
        int chapterID = cin.nextInt();
        String formattedChapterID = String.format("chap%02d", chapterID);
        System.out.println("Enter the section ID: ");
        int sectionID = cin.nextInt();
        String formattedSectionID = String.format("Sec%02d", sectionID);
        
        System.out.println("\n1.View Block\n2.Go Back");
        int choice = cin.nextInt();

        switch (choice) {
            case 1:
                //System.out.println("View block selected");
                ViewBlock.displayViewBlockPage(username, displayedTextbooks);
                break;
            case 2:
                StudentLandingPage.displayStudentLandingPage(username);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayViewSectionPage(username, displayedTextbooks);
                break;
        }
    }*/
    
}
