CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCoursesByFaculty`(
	IN p_faculty_id CHAR(8)
)
BEGIN
    -- Select all courses taught by the given faculty member
    SELECT course_name
    FROM Courses
    WHERE faculty_id = p_faculty_id;
END