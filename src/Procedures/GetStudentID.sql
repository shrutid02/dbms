CREATE DEFINER=`soham`@`localhost` PROCEDURE `GetStudentID`(
    IN studentUsername VARCHAR(50),
    OUT studentID CHAR(8)
)
BEGIN
    SELECT student_id INTO studentID
    FROM students
    WHERE username = studentUsername
    LIMIT 1;
END