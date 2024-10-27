CREATE DEFINER=`root`@`localhost` PROCEDURE `ViewWorklist`(
    IN p_course_id VARCHAR(50)
)
BEGIN
    SELECT * 
    FROM Enrollments
    WHERE course_id = p_course_id
    AND Status = 'Pending';
END