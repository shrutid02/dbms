
------------------------------------------------- TABLES -------------------------------------------

CREATE TABLE `Activity` (
  `textbook_id` int NOT NULL,
  `chapter_id` varchar(10) NOT NULL,
  `section_id` varchar(10) NOT NULL,
  `block_id` varchar(10) NOT NULL,
  `unique_activity_id` varchar(10) NOT NULL,
  `hidden` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`textbook_id`,`chapter_id`,`section_id`,`block_id`,`unique_activity_id`),
  CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`textbook_id`) REFERENCES `Textbook` (`textbook_id`) ON DELETE CASCADE,
  CONSTRAINT `activity_ibfk_2` FOREIGN KEY (`textbook_id`, `chapter_id`) REFERENCES `Chapter` (`textbook_id`, `chapter_id`) ON DELETE CASCADE,
  CONSTRAINT `activity_ibfk_3` FOREIGN KEY (`textbook_id`, `chapter_id`, `section_id`) REFERENCES `Sections` (`textbook_id`, `chapter_id`, `section_id`) ON DELETE CASCADE,
  CONSTRAINT `activity_ibfk_4` FOREIGN KEY (`textbook_id`, `chapter_id`, `section_id`, `block_id`) REFERENCES `Blocks` (`textbook_id`, `chapter_id`, `section_number`, `block_id`) ON DELETE CASCADE
);

CREATE TABLE `Blocks` (
  `textbook_id` int NOT NULL,
  `chapter_id` varchar(10) NOT NULL,
  `section_number` varchar(10) NOT NULL,
  `block_id` varchar(10) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `content` text,
  `hidden` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`textbook_id`,`chapter_id`,`section_number`,`block_id`),
  CONSTRAINT `blocks_ibfk_1` FOREIGN KEY (`textbook_id`) REFERENCES `Textbook` (`textbook_id`) ON DELETE CASCADE,
  CONSTRAINT `blocks_ibfk_2` FOREIGN KEY (`textbook_id`, `chapter_id`) REFERENCES `Chapter` (`textbook_id`, `chapter_id`) ON DELETE CASCADE,
  CONSTRAINT `blocks_ibfk_3` FOREIGN KEY (`textbook_id`, `chapter_id`, `section_number`) REFERENCES `Sections` (`textbook_id`, `chapter_id`, `section_id`) ON DELETE CASCADE
);

CREATE TABLE `Chapter` (
  `textbook_id` int NOT NULL,
  `chapter_id` varchar(10) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `hidden` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`textbook_id`,`chapter_id`),
  CONSTRAINT `chapter_ibfk_1` FOREIGN KEY (`textbook_id`) REFERENCES `Textbook` (`textbook_id`)
);

CREATE TABLE `Courses` (
  `course_id` varchar(50) NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `textbook_id` int DEFAULT NULL,
  `course_type` enum('Active','Evaluation') DEFAULT NULL,
  `faculty_id` char(8) DEFAULT NULL,
  `ta_id` char(8) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `unique_token` varchar(50) DEFAULT NULL,
  `course_capacity` int DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `textbook_id` (`textbook_id`),
  KEY `faculty_id` (`faculty_id`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`textbook_id`) REFERENCES `Textbook` (`textbook_id`),
  CONSTRAINT `courses_ibfk_2` FOREIGN KEY (`faculty_id`) REFERENCES `Faculty` (`faculty_id`)
);

CREATE TABLE `Enrollments` (
  `course_id` varchar(50) NOT NULL,
  `student_id` char(8) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`student_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `enrollments_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `Courses` (`course_id`),
  CONSTRAINT `enrollments_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `Students` (`student_id`)
);

CREATE TABLE `Faculty` (
  `faculty_id` char(8) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`faculty_id`),
  UNIQUE KEY `email` (`email`)
);

CREATE TABLE `Questions` (
  `textbook_id` int NOT NULL,
  `chapter_id` varchar(50) NOT NULL,
  `section_id` varchar(50) NOT NULL,
  `block_id` varchar(50) NOT NULL,
  `unique_activity_id` varchar(50) NOT NULL,
  `question_id` varchar(50) NOT NULL,
  `question_text` text,
  `option_1` text,
  `opt_1_explanation` text,
  `option_2` text,
  `opt_2_explanation` text,
  `option_3` text,
  `opt_3_explanation` text,
  `option_4` text,
  `opt_4_explanation` text,
  `answer` int DEFAULT NULL,
  PRIMARY KEY (`textbook_id`,`chapter_id`,`section_id`,`block_id`,`unique_activity_id`,`question_id`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`textbook_id`) REFERENCES `Textbook` (`textbook_id`) ON DELETE CASCADE,
  CONSTRAINT `questions_ibfk_2` FOREIGN KEY (`textbook_id`, `chapter_id`) REFERENCES `Chapter` (`textbook_id`, `chapter_id`) ON DELETE CASCADE,
  CONSTRAINT `questions_ibfk_3` FOREIGN KEY (`textbook_id`, `chapter_id`, `section_id`) REFERENCES `Sections` (`textbook_id`, `chapter_id`, `section_id`) ON DELETE CASCADE,
  CONSTRAINT `questions_ibfk_4` FOREIGN KEY (`textbook_id`, `chapter_id`, `section_id`, `block_id`) REFERENCES `Blocks` (`textbook_id`, `chapter_id`, `section_number`, `block_id`) ON DELETE CASCADE,
  CONSTRAINT `questions_ibfk_5` FOREIGN KEY (`textbook_id`, `chapter_id`, `section_id`, `block_id`, `unique_activity_id`) REFERENCES `Activity` (`textbook_id`, `chapter_id`, `section_id`, `block_id`, `unique_activity_id`) ON DELETE CASCADE
);

CREATE TABLE `Sections` (
  `textbook_id` int NOT NULL,
  `chapter_id` varchar(10) NOT NULL,
  `section_id` varchar(10) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `hidden` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`textbook_id`,`chapter_id`,`section_id`),
  CONSTRAINT `sections_ibfk_1` FOREIGN KEY (`textbook_id`) REFERENCES `Textbook` (`textbook_id`) ON DELETE CASCADE,
  CONSTRAINT `sections_ibfk_2` FOREIGN KEY (`textbook_id`, `chapter_id`) REFERENCES `Chapter` (`textbook_id`, `chapter_id`) ON DELETE CASCADE
);

CREATE TABLE `student_activity` (
  `student_id` char(8) NOT NULL,
  `course_id` varchar(50) NOT NULL,
  `textbook_id` int NOT NULL,
  `chapter_id` varchar(50) NOT NULL,
  `section_id` varchar(50) NOT NULL,
  `block_id` varchar(50) NOT NULL,
  `unique_activity_id` varchar(50) NOT NULL,
  `question_id` varchar(50) NOT NULL,
  `point` int DEFAULT NULL,
  `timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`student_id`,`course_id`,`textbook_id`,`chapter_id`,`section_id`,`block_id`,`unique_activity_id`,`question_id`),
  KEY `course_id` (`course_id`),
  KEY `textbook_id` (`textbook_id`,`chapter_id`,`section_id`,`block_id`,`unique_activity_id`,`question_id`),
  CONSTRAINT `student_activity_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
  CONSTRAINT `student_activity_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `Courses` (`course_id`),
  CONSTRAINT `student_activity_ibfk_3` FOREIGN KEY (`textbook_id`) REFERENCES `Textbook` (`textbook_id`),
  CONSTRAINT `student_activity_ibfk_4` FOREIGN KEY (`textbook_id`, `chapter_id`) REFERENCES `Chapter` (`textbook_id`, `chapter_id`),
  CONSTRAINT `student_activity_ibfk_5` FOREIGN KEY (`textbook_id`, `chapter_id`, `section_id`) REFERENCES `sections` (`textbook_id`, `chapter_id`, `section_id`),
  CONSTRAINT `student_activity_ibfk_6` FOREIGN KEY (`textbook_id`, `chapter_id`, `section_id`, `block_id`) REFERENCES `blocks` (`textbook_id`, `chapter_id`, `section_number`, `block_id`),
  CONSTRAINT `student_activity_ibfk_7` FOREIGN KEY (`textbook_id`, `chapter_id`, `section_id`, `block_id`, `unique_activity_id`) REFERENCES `activity` (`textbook_id`, `chapter_id`, `section_id`, `block_id`, `unique_activity_id`),
  CONSTRAINT `student_activity_ibfk_8` FOREIGN KEY (`textbook_id`, `chapter_id`, `section_id`, `block_id`, `unique_activity_id`, `question_id`) REFERENCES `questions` (`textbook_id`, `chapter_id`, `section_id`, `block_id`, `unique_activity_id`, `question_id`)
);

CREATE TABLE `student_courses` (
  `student_id` char(8) NOT NULL,
  `registered_course_id` varchar(50) NOT NULL,
  `total_participation_points` int DEFAULT NULL,
  `num_finished_activities` int DEFAULT NULL,
  PRIMARY KEY (`student_id`,`registered_course_id`),
  KEY `registered_course_id` (`registered_course_id`),
  CONSTRAINT `student_courses_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
  CONSTRAINT `student_courses_ibfk_2` FOREIGN KEY (`registered_course_id`) REFERENCES `Courses` (`course_id`)
);

CREATE TABLE `students` (
  `student_id` char(8) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
);

CREATE TABLE `TA` (
  `ta_id` char(8) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `course_id` varchar(50) DEFAULT NULL,
  `faculty_id` char(8) DEFAULT NULL,
  PRIMARY KEY (`ta_id`),
  UNIQUE KEY `email` (`email`),
  KEY `faculty_id` (`faculty_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `ta_ibfk_1` FOREIGN KEY (`faculty_id`) REFERENCES `Faculty` (`faculty_id`),
  CONSTRAINT `ta_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `Courses` (`course_id`)
);

CREATE TABLE `Textbook` (
  `textbook_id` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`textbook_id`)
);

CREATE TABLE Notifications (
    student_id CHAR(8),
    message TEXT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);


------------------------------------------------ PROCEDURES -------------------------------------------

DELIMITER //

CREATE DEFINER=`root`@`localhost` PROCEDURE `AddNewCourse`(
    IN p_course_id VARCHAR(50),
	IN p_course_name VARCHAR(100),
    IN p_textbook_id INT,
    IN p_course_type ENUM('Active', 'Evaluation'),
    IN p_faculty_id VARCHAR(50),
    IN p_ta_id VARCHAR(50),
    IN p_start_date DATE,
    IN p_end_date DATE,
    IN p_unique_token VARCHAR(50),
    IN p_course_capacity INT
)
BEGIN
    INSERT INTO Courses (course_id, course_name, textbook_id, course_type, faculty_id, ta_id, start_date, end_date, unique_token, course_capacity)
    VALUES (p_course_id, p_course_name, p_textbook_id, p_course_type, p_faculty_id, p_ta_id, p_start_date, p_end_date, p_unique_token, p_course_capacity);
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `AddTA`(
    IN p_course_id VARCHAR(50),
    IN p_faculty_id VARCHAR(50),
    IN p_first_name VARCHAR(50),
    IN p_last_name VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_default_password VARCHAR(50),
    OUT p_message VARCHAR(100)
)
BEGIN
    DECLARE v_ta_id CHAR(8);
    DECLARE current_month CHAR(2);
    DECLARE current_year CHAR(2);
    DECLARE v_actual_faculty_id VARCHAR(50);

    -- Step 1: Check if the faculty is assigned to the course
    SELECT faculty_id INTO v_actual_faculty_id
    FROM Courses
    WHERE course_id = p_course_id;

    -- If the faculty_id does not match, set an error message and end
    IF v_actual_faculty_id IS NULL THEN
        SET p_message = 'Error: Course does not exist.';
        -- End execution if the course does not exist
    ELSEIF v_actual_faculty_id != p_faculty_id THEN
        SET p_message = 'Error: You are not authorized to add a TA to this course.';
        -- End execution if the faculty is unauthorized
    ELSE
        -- Step 2: Call the existing procedure to check if the course is active
        CALL CheckCourseType(p_course_id, @course_message);

        -- Step 3: Check the message returned by CheckCourseType
        IF @course_message = 'Active course, you may continue.' THEN
            -- Generate TA_id
            SET current_month = DATE_FORMAT(CURDATE(), '%m');
            SET current_year = DATE_FORMAT(CURDATE(), '%y');
            SET v_ta_id = CONCAT(LEFT(p_first_name, 2), LEFT(p_last_name, 2), current_month, current_year);

            -- Step 4: Insert the new TA into the TA table
            INSERT INTO TA (ta_id, first_name, last_name, email, password, course_id, faculty_id)
            VALUES (v_ta_id, p_first_name, p_last_name, p_email, p_default_password, p_course_id, p_faculty_id);

            -- Success message
            SET p_message = CONCAT('\nTA added successfully with TA_id: ', v_ta_id);
        ELSE
            -- If the course is not active, set an error message
            SET p_message = '\nError: Course is not active.';
        END IF;
    END IF;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `ApproveEnrollment`(
    IN p_faculty_id VARCHAR(50),
    IN p_course_id VARCHAR(50),
    IN p_student_id VARCHAR(50)
)
BEGIN
    DECLARE v_faculty_id VARCHAR(50);
    DECLARE v_status VARCHAR(50);
    DECLARE v_enrolled_count INT;
    DECLARE v_course_capacity INT;

    -- Check if the student is in the enrollments table with pending status
    SELECT status INTO v_status
    FROM Enrollments
    WHERE student_id = p_student_id AND course_id = p_course_id;

    IF v_status = 'Pending' THEN
        -- Check if the faculty_id matches the course's faculty_id
        SELECT faculty_id, course_capacity INTO v_faculty_id, v_course_capacity
        FROM Courses
        WHERE course_id = p_course_id;

        IF v_faculty_id = p_faculty_id THEN
            -- Count the currently enrolled students for this course
            SELECT COUNT(*) INTO v_enrolled_count
            FROM Enrollments
            WHERE course_id = p_course_id AND status = 'Enrolled';

            -- Verify if there is capacity to enroll more students
            IF v_enrolled_count < v_course_capacity THEN
                -- Update the student's status to 'Enrolled'
                UPDATE Enrollments
                SET status = 'Enrolled'
                WHERE student_id = p_student_id AND course_id = p_course_id;

                SELECT 'Enrollment approved and updated successfully.' AS message;
            ELSE
                SELECT 'Error: Course capacity reached. Cannot enroll more students.' AS message;
            END IF;
        ELSE
            SELECT 'Error: Faculty ID does not match the course.' AS message;
        END IF;
    ELSE
        SELECT 'Error: Student is not in pending status or does not exist.' AS message;
    END IF;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `ChangePassword`(
    IN p_user_id VARCHAR(50),
    IN p_current_password VARCHAR(255),
    IN p_new_password VARCHAR(255),
    IN p_role ENUM('Faculty', 'TA'),
    OUT message VARCHAR(255)
)
BEGIN
    DECLARE v_password VARCHAR(255);

    -- Conditional logic based on role
    IF p_role = 'Faculty' THEN
        -- Retrieve current password from Faculty table
        SELECT password INTO v_password
        FROM Faculty
        WHERE faculty_id = p_user_id;

    ELSEIF p_role = 'TA' THEN
        -- Retrieve current password from TA table
        SELECT password INTO v_password
        FROM TA
        WHERE ta_id = p_user_id;

    END IF;

    -- Check if the user exists and the current password matches
    IF v_password IS NULL THEN
        SET message = 'User ID not found.';
    ELSEIF v_password != p_current_password THEN
        SET message = 'Error - Current password is incorrect.';
    ELSE
        -- Update the password in the respective table if current password is correct
        IF p_role = 'Faculty' THEN
            UPDATE Faculty
            SET password = p_new_password
            WHERE faculty_id = p_user_id;
        ELSEIF p_role = 'TA' THEN
            UPDATE TA
            SET password = p_new_password
            WHERE ta_id = p_user_id;
        END IF;

        SET message = 'Password updated successfully!!';
    END IF;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckCourseType`(
    IN p_course_id VARCHAR(50),
    OUT p_message VARCHAR(50)
)
BEGIN
    DECLARE v_course_type VARCHAR(50);

    -- Check the course type in the courses table
    SELECT course_type INTO v_course_type
    FROM Courses
    WHERE course_id = p_course_id;

    -- Set the output message based on the course type
    IF v_course_type = 'Active' THEN
        SET p_message = 'Active course, you may continue.';
    ELSE
        SET p_message = '\n****** Error: Please enter an active course_id.';
    END IF;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckEnrollmentExists`(
    IN studentID CHAR(8),
    IN courseToken VARCHAR(50),
    OUT enrollmentExists BOOLEAN
)
BEGIN
    DECLARE courseID VARCHAR(50);

    -- Retrieve the corresponding course_id based on the course token
    SELECT course_id INTO courseID
    FROM Courses
    WHERE unique_token = courseToken
    LIMIT 1;

    -- Check if the student has already enrolled in the course
    IF courseID IS NOT NULL THEN
        SELECT COUNT(*) INTO @count
        FROM Enrollments
        WHERE course_id = courseID AND student_id = studentID;

        IF @count > 0 THEN
            SET enrollmentExists = TRUE;
        ELSE
            SET enrollmentExists = FALSE;
        END IF;
    ELSE
        SET enrollmentExists = FALSE;
    END IF;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckStudentAccountExists`(
	IN studentEmail VARCHAR(100), 
    OUT accountExists BOOLEAN,
    OUT studentID CHAR(8)
)
BEGIN
    DECLARE count INT;

    SELECT student_id INTO studentID
    FROM students
    WHERE email = studentEmail
    LIMIT 1;

    IF studentID IS NOT NULL THEN
        SET accountExists = TRUE;
    ELSE
        SET accountExists = FALSE;
        SET studentID = NULL;
    END IF;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `CountSectionsInFirstChapter`(IN input_textbook_id INT)
BEGIN
    SELECT COUNT(*) AS section_count
    FROM Sections
    WHERE textbook_id = input_textbook_id AND chapter_id = 'chap01';
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateStudentAccount`(
    IN studentID CHAR(8),
    IN fullName VARCHAR(100),
    IN username VARCHAR(50),
    IN password VARCHAR(50),
    IN email VARCHAR(100)
)
BEGIN
    INSERT INTO students (student_id, full_name, username, password, email)
    VALUES (studentID, fullName, username, password, email);
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `EnrollStudentCourse`(
    IN studentID CHAR(8),
    IN courseToken VARCHAR(50)
)
BEGIN
    DECLARE courseID VARCHAR(50);

    -- Find the corresponding course ID based on the course token
    SELECT course_id INTO courseID
    FROM Courses
    WHERE unique_token = courseToken;

    -- Enroll the student in the course with status 'Pending'
    INSERT INTO Enrollments (course_id, student_id, status)
    VALUES (courseID, studentID, 'Pending');
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetBlocks`(
    IN textbookID INT,
    IN chapterID VARCHAR(50),
    IN sectionID VARCHAR(50)
)
BEGIN
    SELECT block_id, type, content, hidden
    FROM Blocks
    WHERE textbook_id = textbookID
      AND chapter_id = chapterID
      AND section_number = sectionID
    ORDER BY block_id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetChapterContent`(
    IN p_textbook_id INT,
    IN p_chapter_id VARCHAR(10)
)
BEGIN
    SELECT 
        s.title AS section_title,
        b.type AS block_type,
        b.content AS block_content,
        a.unique_activity_id AS activity_id,
        q.question_id AS question_id,
        q.question_text AS question_text,
        q.option_1 AS option_1,
        q.opt_1_explanation AS opt_1_explanation,
        q.option_2 AS option_2,
        q.opt_2_explanation AS opt_2_explanation,
        q.option_3 AS option_3,
        q.opt_3_explanation AS opt_3_explanation,
        q.option_4 AS option_4,
        q.opt_4_explanation AS opt_4_explanation,
        q.answer AS answer
    FROM 
        Sections s
    JOIN 
        Blocks b 
    ON 
        s.textbook_id = b.textbook_id 
        AND s.chapter_id = b.chapter_id 
        AND s.section_id = b.section_number
    LEFT JOIN 
        Activity a 
    ON 
        b.textbook_id = a.textbook_id 
        AND b.chapter_id = a.chapter_id 
        AND b.section_number = a.section_id 
        AND b.block_id = a.block_id
    LEFT JOIN 
        Questions q 
    ON 
        a.textbook_id = q.textbook_id 
        AND a.chapter_id = q.chapter_id 
        AND a.section_id = q.section_id 
        AND a.block_id = q.block_id 
        AND a.unique_activity_id = q.unique_activity_id
    WHERE 
        s.textbook_id = p_textbook_id
        AND s.chapter_id = p_chapter_id
    ORDER BY 
        s.section_id, b.block_id, a.unique_activity_id, q.question_id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetChaptersAndSections`(
    IN textbookID INT
)
BEGIN
    SELECT c.chapter_id, c.title AS chapter_title, s.section_id, s.title AS section_title
    FROM Chapter c
    LEFT JOIN Sections s ON c.textbook_id = s.textbook_id AND c.chapter_id = s.chapter_id
    WHERE c.textbook_id = textbookID AND c.hidden = 'no' AND (s.hidden = 'no' OR s.hidden IS NULL)
    ORDER BY c.chapter_id, s.section_id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCourseParticipants`()
BEGIN
    SELECT
        ta.First_Name AS Name,
        ta.Last_Name AS LastName,
        'TA' AS Role,
        c.Course_Name AS Course,
        c.Course_ID AS CourseID
    FROM
        TA AS ta
    JOIN
        Courses AS c
    ON
        ta.TA_id = c.TA_ID
	WHERE
        c.course_type = 'Active'

    UNION ALL

    SELECT
        f.First_Name AS Name,
        f.Last_Name AS LastName,
        'Faculty' AS Role,
        c.Course_Name AS Course,
        c.Course_ID AS CourseID
    FROM
        Faculty AS f
    JOIN
        Courses AS c
    ON
        f.faculty_id = c.faculty_id
	WHERE
        c.course_type = 'Active';
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCoursesByFaculty`(
	IN p_faculty_id CHAR(8)
)
BEGIN
    -- Select all courses taught by the given faculty member
    SELECT course_name
    FROM Courses
    WHERE faculty_id = p_faculty_id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCoursesByTA`(
	IN p_ta_id CHAR(8)
)
BEGIN
    -- Select all courses taught by the given faculty member
    SELECT course_name
    FROM Courses
    WHERE ta_id = p_ta_id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetIncorrectAnswersForQ2`()
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE option1 VARCHAR(255);
    DECLARE option2 VARCHAR(255);
    DECLARE option3 VARCHAR(255);
    DECLARE option4 VARCHAR(255);
    DECLARE explanation1 TEXT;
    DECLARE explanation2 TEXT;
    DECLARE explanation3 TEXT;
    DECLARE explanation4 TEXT;

    DECLARE cur CURSOR FOR
        SELECT option_1, opt_1_explanation, option_2, opt_2_explanation,
               option_3, opt_3_explanation, option_4, opt_4_explanation
        FROM Questions
        WHERE textbook_id = 101
          AND chapter_id = 'chap01'
          AND section_id = 'Sec02'
          AND block_id = 'Block01'
          AND unique_activity_id = 'ACT0'
          AND question_id = 'Q2';

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    CREATE TEMPORARY TABLE IF NOT EXISTS temp_incorrect_answers (
        IncorrectOption VARCHAR(255),
        Explanation TEXT
    );

    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO option1, explanation1, option2, explanation2,
                     option3, explanation3, option4, explanation4;
        IF done THEN
            LEAVE read_loop;
        END IF;

        IF explanation1 LIKE '%Incorrect%' THEN
            INSERT INTO temp_incorrect_answers (IncorrectOption, Explanation)
            VALUES (option1, explanation1);
        END IF;

        IF explanation2 LIKE '%Incorrect%' THEN
            INSERT INTO temp_incorrect_answers (IncorrectOption, Explanation)
            VALUES (option2, explanation2);
        END IF;

        IF explanation3 LIKE '%Incorrect%' THEN
            INSERT INTO temp_incorrect_answers (IncorrectOption, Explanation)
            VALUES (option3, explanation3);
        END IF;

        IF explanation4 LIKE '%Incorrect%' THEN
            INSERT INTO temp_incorrect_answers (IncorrectOption, Explanation)
            VALUES (option4, explanation4);
        END IF;

    END LOOP;

    CLOSE cur;

    SELECT * FROM temp_incorrect_answers;

    DROP TEMPORARY TABLE IF EXISTS temp_incorrect_answers;

END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetQuestions`(
    IN textbookID INT,
    IN chapterID VARCHAR(50),
    IN sectionID VARCHAR(50),
    IN blockID VARCHAR(50)
)
BEGIN
    SELECT q.question_text, q.option_1, q.option_2, q.option_3, q.option_4
    FROM Questions q
    JOIN Activity a ON q.textbook_id = a.textbook_id
                   AND q.chapter_id = a.chapter_id
                   AND q.section_id = a.section_id
                   AND q.block_id = a.block_id
                   AND q.unique_activity_id = a.unique_activity_id
    WHERE q.textbook_id = textbookID
      AND q.chapter_id = chapterID
      AND q.section_id = sectionID
      AND q.block_id = blockID
      AND a.hidden = 'no'
    ORDER BY q.question_id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetRegisteredCourses`(
    IN studentID CHAR(8)
)
BEGIN
    SELECT registered_course_id
    FROM student_courses
    WHERE student_id = studentID;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetStudentID`(
    IN studentUsername VARCHAR(50),
    OUT studentID CHAR(8)
)
BEGIN
    SELECT student_id INTO studentID
    FROM students
    WHERE username = studentUsername
    LIMIT 1;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetStudentsByFacultyAndCourse`(
    IN p_faculty_id CHAR(8),
    IN p_course_id VARCHAR(50)
)
BEGIN
    -- Select all students enrolled in the specific course taught by the given Faculty
    SELECT students.student_id, students.full_name
    FROM students
    INNER JOIN Enrollments ON students.student_id = Enrollments.student_id
    INNER JOIN Courses ON Enrollments.course_id = Courses.course_id
    WHERE Courses.faculty_id = p_faculty_id
      AND Courses.course_id = p_course_id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetStudentsByTAAndCourse`(
    IN p_ta_id CHAR(8),
    IN p_course_id VARCHAR(50)
)
BEGIN
    -- Select all students enrolled in the specific course assigned to the given TA
    SELECT students.student_id, students.full_name
    FROM students
    INNER JOIN Enrollments ON students.student_id = Enrollments.student_id
    INNER JOIN Courses ON Enrollments.course_id = Courses.course_id
    WHERE Courses.ta_id = p_ta_id
      AND Courses.course_id = p_course_id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetTextbookDetails`(
    IN courseID VARCHAR(50)
)
BEGIN
    SELECT t.textbook_id, t.title
    FROM Courses c
    JOIN Textbook t ON c.textbook_id = t.textbook_id
    WHERE c.course_id = courseID;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `ListOfQueries3`()
BEGIN
    SELECT 
        C.course_id,
        CONCAT(F.first_name, ' ', F.last_name) AS FacultyName,
        COUNT(SC.student_id) AS TotalStudents
    FROM Courses C
    JOIN Faculty F ON C.faculty_id = F.faculty_id
    LEFT JOIN student_courses SC ON C.course_id = SC.registered_course_id
    WHERE C.course_type = 'Active'
    GROUP BY C.course_id, F.faculty_id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `ListOfQueries4`()
BEGIN
    SELECT course_id, COUNT(student_id) AS TotalWaitingList
    FROM Enrollments
    WHERE status = 'Pending'
    GROUP BY course_id
    ORDER BY TotalWaitingList DESC
    LIMIT 1;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `ListOfQueries7`()
BEGIN
    SELECT DISTINCT t.title, 
           c1.course_type AS status_by_instructor1, 
           c1.faculty_id AS instructor1,
           c2.course_type AS status_by_instructor2, 
           c2.faculty_id AS instructor2
    FROM Textbook t
    JOIN Courses c1 ON t.textbook_id = c1.textbook_id
    JOIN Courses c2 ON t.textbook_id = c2.textbook_id
    WHERE c1.course_type = 'Active'
      AND c2.course_type = 'Evaluation'
      AND c1.faculty_id <> c2.faculty_id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `LogStudentActivity`(
    IN studentID CHAR(8),
    IN courseID VARCHAR(50),
    IN textbookID INT,
    IN chapterID VARCHAR(50),
    IN sectionID VARCHAR(50),
    IN blockID VARCHAR(50),
    IN uniqueActivityID VARCHAR(50),
    IN questionID VARCHAR(50),
    IN point INT
)
BEGIN
    INSERT INTO student_activity (student_id, course_id, textbook_id, chapter_id, section_id, block_id, unique_activity_id, question_id, point, timestamp)
    VALUES (studentID, courseID, textbookID, chapterID, sectionID, blockID, uniqueActivityID, questionID, point, NOW());
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `saveFaculty`(
    IN p_first_name VARCHAR(50),
    IN p_last_name VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_password VARCHAR(50)
)
BEGIN
    DECLARE current_month CHAR(2);
    DECLARE current_year CHAR(2);
    DECLARE generated_faculty_id VARCHAR(10);

    SET current_month = DATE_FORMAT(CURDATE(), '%m');
    SET current_year = DATE_FORMAT(CURDATE(), '%y');

    SET generated_faculty_id = CONCAT(LEFT(p_first_name, 2), LEFT(p_last_name, 2), current_month, current_year);

    INSERT INTO faculty (first_name, last_name, email, password, faculty_id)
    VALUES (p_first_name, p_last_name, p_email, p_password, generated_faculty_id);
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateStudentCourseSummary`(
    IN studentID CHAR(8),
    IN courseID VARCHAR(50)
)
BEGIN
    -- Update total participation points
    UPDATE student_courses sc
    JOIN (
        SELECT student_id, course_id, SUM(point) AS total_points
        FROM student_activity
        WHERE student_id = studentID
          AND course_id = courseID
        GROUP BY student_id, course_id
    ) sa ON sc.student_id = sa.student_id AND sc.registered_course_id = sa.course_id
    SET sc.total_participation_points = sa.total_points;

    -- Update number of finished activities, counting each question individually
    UPDATE student_courses sc
    JOIN (
        SELECT student_id, course_id, COUNT(*) AS finished_activities
        FROM student_activity
        WHERE student_id = studentID
          AND course_id = courseID
          AND point > 0  -- Only count attempted questions
        GROUP BY student_id, course_id
    ) sa ON sc.student_id = sa.student_id AND sc.registered_course_id = sa.course_id
    SET sc.num_finished_activities = sa.finished_activities;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `VerifyCourseToken`(
IN courseToken VARCHAR(50), 
OUT tokenExists BOOLEAN)
BEGIN
    DECLARE count INT;

    SELECT COUNT(*) INTO count
    FROM Courses
    WHERE unique_token = courseToken;

    IF count > 0 THEN
        SET tokenExists = TRUE;
    ELSE
        SET tokenExists = FALSE;
    END IF;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `ViewStudents`(
    IN p_course_id VARCHAR(50)
)
BEGIN
    -- Select students who are enrolled in the course
    SELECT student_id
    FROM Enrollments
    WHERE course_id = p_course_id;
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `ViewWorklist`(
    IN p_course_id VARCHAR(50)
)
BEGIN
    SELECT * 
    FROM Enrollments
    WHERE course_id = p_course_id
    AND Status = 'Pending';
END

DELIMITER;

------------------------------------------ TRIGGERS ------------------------------------------
DELIMITER //

CREATE TRIGGER after_enrollment_update
AFTER UPDATE ON Enrollments
FOR EACH ROW
BEGIN
    -- Check if the student's status has been changed to 'Enrolled'
    IF NEW.status = 'Enrolled' AND OLD.status <> 'Enrolled' THEN
        -- Only insert into student_courses if the student-course record doesn't already exist
        IF NOT EXISTS (
            SELECT 1 FROM student_courses 
            WHERE student_id = NEW.student_id 
              AND registered_course_id = NEW.course_id
        ) THEN
            INSERT INTO student_courses (student_id, registered_course_id, total_participation_points, num_finished_activities)
            VALUES (NEW.student_id, NEW.course_id, 0, 0);
        END IF;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER after_student_activity_insert
AFTER INSERT ON student_activity
FOR EACH ROW
BEGIN
    CALL UpdateStudentCourseSummary(NEW.student_id, NEW.course_id);
END //

CREATE TRIGGER after_student_activity_update
AFTER UPDATE ON student_activity
FOR EACH ROW
BEGIN
    IF OLD.point = 0 AND NEW.point > 0 THEN
        CALL UpdateStudentCourseSummary(NEW.student_id, NEW.course_id);
    END IF;
END //

DELIMITER ;

