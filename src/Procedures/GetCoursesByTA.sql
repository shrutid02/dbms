CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCoursesByTA`(
	IN p_ta_id CHAR(8)
)
BEGIN
    -- Select all courses taught by the given faculty member
    SELECT course_name
    FROM Courses
    WHERE ta_id = p_ta_id;
END