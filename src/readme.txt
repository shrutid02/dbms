CSC 540 Database Management System
E-Learning Application

Project Members -
Shruti Dhond (sdhond)
Vaibhavi Sangawar (vmsangaw)
Pranav Manbhekar (ppmanbhe)
Soham Patil (sspatil6)

##Project Overview
This project is an implementation of an E-Learning Application modeled after platforms like ZyBooks, with a focus on managing courses, enrollments, faculty, and teaching assistants. It allows students to request enrollment in courses, enables faculty to approve or deny these requests, and enforces capacity restrictions for each course.
The application follows these development stages:
E-R Model Design: We first designed an Entity-Relationship (E-R) model that captures the main entities in the e-learning system, such as Students, Courses, Faculty, Teaching Assistants, and Enrollments, as well as the relationships between them.
Relational Schema Implementation: The E-R model was then converted into a relational schema in MariaDB/MySQL. This schema defines the structure of each table, including primary and foreign key constraints, to ensure data integrity.
Stored Procedures and Triggers: We developed stored procedures and triggers to handle core functionalities. For example, the ApproveEnrollment procedure allows faculty to approve student enrollments if course capacity permits, automatically updating the student's status from "Pending" to "Enrolled."
Menu-Driven Application Interface: Using JDBC, we implemented a simple, menu-driven interface for interacting with the database. This interface allows and supports all the functionalities mentioned in the application flow.
Setup Instructions
Prerequisites
Java Development Kit (JDK): Ensure you have JDK 8 or higher installed.
MySQL Database: Install MySQL and create the necessary tables and stored procedures.
JDBC Connector: Ensure the MySQL JDBC connector (`mysql-connector-java-x.x.x.jar`) is included in the project classpath.

##Database Setup
Create Database Schema: Run `schema.sql` in MySQL to create the required tables (`Courses`, `Enrollments`, etc.).
Stored Procedures: Run `procedures.sql` to create stored procedures such as `ApproveEnrollment`.

##Java Compilation
Navigate to the `src` directory and compile the Java files.

##Running the Application
Run the App.java point as that is the entrypoint for the project.

##Key Functionalities
E-Textbooks Management
Curated Textbooks: Admins create and organize e-textbooks with unique titles and identifiers.
Structured Content: Textbooks are organized into chapters and sections, with sequential content blocks.
Interactive Activities: Activities with answer sets are embedded within sections, providing instant feedback and explanations.

##User Roles
Role-Based Access: Users are categorized as admins, faculty, students, or TAs, each with distinct permissions.
Unique User IDs: IDs are auto-generated based on the user’s name and registration date.
Admin Privileges: Admins can add textbooks, chapters, sections, and activities.

##Course Management
Course Creation: Courses are created based on existing textbooks and categorized as "Active" or "Evaluation".
Enrollment Management: Faculty manage student enrollment, including approval of pending requests.
Teaching Assistants: Faculty can assign TAs to help manage course content and customization.

##Student Interactions
Content Access: Students view non-hidden textbook content and engage with it interactively.
Activity Scoring: Students attempt activities, receive feedback, and earn points based on accuracy.
Progress Tracking: Students’ participation points are tracked, showing their progress in each course.


Notes
Ensure the database connection credentials are correctly configured in `App.java` for successful database access.
Put your correct credentials for username and password as well as the correct schema name.
If you encounter errors, verify that the `schema.sql` and `procedures.sql` scripts are executed without issues in MySQL.
