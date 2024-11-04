CREATE DEFINER=`soham`@`localhost` PROCEDURE `GetRegisteredCourses`(
    IN studentID CHAR(8)
)
BEGIN
    SELECT registered_course_id
    FROM student_courses
    WHERE student_id = studentID;
END