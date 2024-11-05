CREATE DEFINER=`root`@`localhost` PROCEDURE `ListOfQueries3`()
BEGIN
    SELECT 
        C.course_id,
        CONCAT(F.first_name, ' ', F.last_name) AS FacultyName,
        COUNT(SC.student_id) AS TotalStudents
    FROM Courses C
    JOIN Faculty F ON C.faculty_id = F.faculty_id
    LEFT JOIN student_courses SC ON C.course_id = SC.registered_course_id
    WHERE C.course_type = 'Active'
    GROUP BY C.course_id, F.faculty_id;
END