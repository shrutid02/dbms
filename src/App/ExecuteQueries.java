package App;

import java.sql.SQLException;
import java.util.Scanner;

public class ExecuteQueries {
    static Scanner cin = new Scanner(System.in);

    public static void displayListOfQueries() throws SQLException {
        System.out.println("\n******** List of Queries: ********");
        System.out.println("\n1.Print the number of sections of the first chapter of a textbook." +
                "\n2.Print names of faculty and TAs of all courses." +
                "\n3.For each active course, print the course id, faculty member and total number of students." +
                "\n4.Find the course with the largest waiting list, print the course id and the total number of people on the list." +
                "\n5.Print the contents of Chapter 02 of textbook 101 in proper sequence." +
                "\n6.For Q2 of Activity0, print the incorrect answers for that question and their corresponding explanations." +
                "\n7.Find any book that is in active status by one instructor but evaluation status by a different instructor." +
                "\n8.EXIT");
        System.out.println("\n*** Enter choice (1-8): ");
        int c = cin.nextInt();

        switch (c) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                ListOfQueries.query7();
                displayListOfQueries();
                break;
            case 8:
                System.out.println("Going back to Home page...");
                App.displayHomePage();
                break;
            default:
                System.out.println("Invalid choice. Please chose again.");
                displayListOfQueries();
        }
    }
}
