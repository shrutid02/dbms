# CSC 540: Database Management System  
## E-Learning Application 

### Project Members  
- **Shruti Dhond** (sdhond)  
- **Vaibhavi Sangawar** (vmsangaw)  
- **Pranav Manbhekar** (ppmanbhe)  
- **Soham Patil** (sspatil6)  

### Project Overview  
This project is an implementation of an E-Learning Application modeled after platforms like ZyBooks, with a focus on managing courses, enrollments, faculty, and teaching assistants. It allows students to request enrollment in courses, enables faculty to approve or deny these requests, and enforces capacity restrictions for each course.

#### Development Stages  
1. **E-R Model Design**  
   - Designed an Entity-Relationship (E-R) model to capture the main entities in the e-learning system (e.g., Students, Courses, Faculty, Teaching Assistants, Enrollments) and the relationships between them.

2. **Relational Schema Implementation**  
   - Converted the E-R model into a relational schema in MariaDB/MySQL. This schema defines the structure of each table, including primary and foreign key constraints to ensure data integrity.

3. **Stored Procedures and Triggers**  
   - Developed stored procedures and triggers for core functionalities. For example, the `ApproveEnrollment` procedure allows faculty to approve student enrollments if course capacity permits, automatically updating the student's status from "Pending" to "Enrolled."

4. **Menu-Driven Application Interface**  
   - Implemented a simple, menu-driven interface using JDBC to interact with the database, supporting all functionalities mentioned in the application flow.

### Setup Instructions  
#### Prerequisites  
- **Java Development Kit (JDK)**: Ensure JDK 8 or higher is installed.
- **MySQL Database**: Install MySQL and create the necessary tables and stored procedures.
- **JDBC Connector**: Include the MySQL JDBC connector (`mysql-connector-java-x.x.x.jar`) in the project classpath.

#### Database Setup  
1. **Create Database Schema**: Run `schema.sql` in MySQL to create the required tables (e.g., `Courses`, `Enrollments`).
2. **Stored Procedures**: Run `procedures.sql` to create stored procedures such as `ApproveEnrollment`.
3. In DatabaseConfig.java file, update **DB_URL, DB_USER, DB_PASSWORD** fields according to your MySQL server.

#### Java Compilation  
1. Navigate to the `src` directory.
2. Compile the Java files.

#### Running the Application  
- Execute `App.java`, the entry point for the project.

### Key Functionalities  
#### E-Textbooks Management  
- **Curated Textbooks**: Admins create and organize e-textbooks with unique titles and identifiers.
- **Structured Content**: Textbooks are organized into chapters and sections, with sequential content blocks.
- **Interactive Activities**: Activities with answer sets are embedded within sections, providing instant feedback and explanations.

#### User Roles  
- **Role-Based Access**: Users are categorized as admins, faculty, students, or TAs, each with distinct permissions.
- **Unique User IDs**: IDs are auto-generated based on the user’s name and registration date.
- **Admin Privileges**: Admins can add textbooks, chapters, sections, and activities.

#### Course Management  
- **Course Creation**: Courses are created based on existing textbooks and categorized as "Active" or "Evaluation".
- **Enrollment Management**: Faculty manage student enrollment, including approval of pending requests.
- **Teaching Assistants**: Faculty can assign TAs to help manage course content and customization.

#### Student Interactions  
- **Content Access**: Students view non-hidden textbook content and engage with it interactively.
- **Activity Scoring**: Students attempt activities, receive feedback, and earn points based on accuracy.
- **Progress Tracking**: Students’ participation points are tracked, showing their progress in each course.

### Notes  
- Ensure the database connection credentials are correctly configured in `App.java` for successful database access.
  - Update with correct credentials for username, password, and schema name.
- If errors occur, verify that the `schema.sql` and `procedures.sql` scripts executed successfully in MySQL.
```
