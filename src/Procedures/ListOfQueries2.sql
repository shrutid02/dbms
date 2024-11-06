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