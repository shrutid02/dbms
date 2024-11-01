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