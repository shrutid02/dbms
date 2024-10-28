CREATE DEFINER=`root`@`localhost` PROCEDURE `ViewStudents`(
    IN p_course_id VARCHAR(50)
)
BEGIN
    -- Select students who are enrolled in the course
    SELECT student_id
    FROM Enrollments
    WHERE course_id = p_course_id;
END