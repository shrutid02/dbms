CREATE DEFINER=`soham`@`localhost` PROCEDURE `CheckStudentAccountExists`(
	IN studentEmail VARCHAR(100), 
    OUT accountExists BOOLEAN,
    OUT studentID CHAR(8)
)
BEGIN
    DECLARE count INT;

    SELECT student_id INTO studentID
    FROM students
    WHERE email = studentEmail
    LIMIT 1;

    IF studentID IS NOT NULL THEN
        SET accountExists = TRUE;
    ELSE
        SET accountExists = FALSE;
        SET studentID = NULL;
    END IF;
END