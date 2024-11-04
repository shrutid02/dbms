CREATE DEFINER=`soham`@`localhost` PROCEDURE `GetTextbookDetails`(
    IN courseID VARCHAR(50)
)
BEGIN
    SELECT t.textbook_id, t.title
    FROM Courses c
    JOIN Textbook t ON c.textbook_id = t.textbook_id
    WHERE c.course_id = courseID;
END