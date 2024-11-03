CREATE DEFINER=`soham`@`localhost` PROCEDURE `VerifyCourseToken`(
IN courseToken VARCHAR(50), 
OUT tokenExists BOOLEAN)
BEGIN
    DECLARE count INT;

    SELECT COUNT(*) INTO count
    FROM Courses
    WHERE unique_token = courseToken;

    IF count > 0 THEN
        SET tokenExists = TRUE;
    ELSE
        SET tokenExists = FALSE;
    END IF;
END