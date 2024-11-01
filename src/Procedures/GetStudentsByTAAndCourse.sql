CREATE DEFINER=`root`@`localhost` PROCEDURE `GetStudentsByTAAndCourse`(
    IN p_ta_id CHAR(8),
    IN p_course_id VARCHAR(50)
)
BEGIN
    -- Select all students enrolled in the specific course assigned to the given TA
    SELECT students.student_id, students.student_name
    FROM students
    INNER JOIN Enrollments ON students.student_id = Enrollments.student_id
    INNER JOIN Courses ON Enrollments.course_id = Courses.course_id
    WHERE Courses.ta_id = p_ta_id
      AND Courses.course_id = p_course_id;
END