package App;

import java.sql.SQLException;
import java.util.Scanner;

import static App.App.displayHomePage;

public class ExecuteQueries {
    static Scanner cin = new Scanner(System.in);

    public static void displayListOfQueries() throws SQLException {
        System.out.println("\n******** List of Queries: ********");
        System.out.println("""

                1.Print the number of sections of the first chapter of a textbook.\

                2.Print names of faculty and TAs of all courses.\

                3.For each active course, print the course id, faculty member and total number of students.\

                4.Find the course with the largest waiting list, print the course id and the total number of people on the list.\

                5.Print the contents of Chapter 02 of textbook 101 in proper sequence.\

                6.For Q2 of Activity0, print the incorrect answers for that question and their corresponding explanations.\

                7.Find any book that is in active status by one instructor but evaluation status by a different instructor.\

                8.EXIT""");
        System.out.println("\n*** Enter choice (1-8): \n");
        int c = cin.nextInt();

        switch (c) {
            case 1:
                ListOfQueries.query1();
                displayListOfQueries();
                break;
            case 2:
                ListOfQueries.query2();
                displayListOfQueries();
                break;
            case 3:
                ListOfQueries.query3();
                displayListOfQueries();
                break;
            case 4:
                ListOfQueries.query4();
                displayListOfQueries();
                break;
            case 5:
                ListOfQueries.query5();
                displayListOfQueries();
                break;
            case 6:
                ListOfQueries.query6();
                displayListOfQueries();
                break;
            case 7:
                ListOfQueries.query7();
                displayListOfQueries();
                break;
            case 8:
                System.out.println("Going back to Home page...");
                displayHomePage();
                break;
            default:
                System.out.println("Invalid choice. Please chose again.");
                displayListOfQueries();
        }
    }
}
