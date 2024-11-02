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