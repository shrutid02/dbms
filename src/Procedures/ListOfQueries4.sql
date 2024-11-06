CREATE DEFINER=`root`@`localhost` PROCEDURE `ListOfQueries4`()
BEGIN
    SELECT course_id, COUNT(student_id) AS TotalWaitingList
    FROM Enrollments
    WHERE status = 'Pending'
    GROUP BY course_id
    ORDER BY TotalWaitingList DESC
    LIMIT 1;
END