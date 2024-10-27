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