CREATE DEFINER=`soham`@`localhost` PROCEDURE `CreateStudentAccount`(
    IN studentID CHAR(8),
    IN fullName VARCHAR(100),
    IN username VARCHAR(50),
    IN password VARCHAR(50),
    IN email VARCHAR(100)
)
BEGIN
    INSERT INTO students (student_id, full_name, username, password, email)
    VALUES (studentID, fullName, username, password, email);
END