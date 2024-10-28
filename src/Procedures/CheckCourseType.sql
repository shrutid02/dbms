CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckCourseType`(
    IN p_course_id VARCHAR(10),
    OUT p_message VARCHAR(50)
)
BEGIN
    DECLARE v_course_type VARCHAR(10);

    -- Check the course type in the courses table
    SELECT course_type INTO v_course_type
    FROM Courses
    WHERE course_id = p_course_id;

    -- Set the output message based on the course type
    IF v_course_type != 'Active' THEN
        SET p_message = 'Please enter an active course_id.';
    END IF;
END