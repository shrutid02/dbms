CREATE DEFINER=`soham`@`localhost` PROCEDURE `LogStudentActivity`(
    IN studentID CHAR(8),
    IN courseID VARCHAR(50),
    IN textbookID INT,
    IN chapterID VARCHAR(50),
    IN sectionID VARCHAR(50),
    IN blockID VARCHAR(50),
    IN uniqueActivityID VARCHAR(50),
    IN questionID VARCHAR(50),
    IN point INT
)
BEGIN
    INSERT INTO student_activity (student_id, course_id, textbook_id, chapter_id, section_id, block_id, unique_activity_id, question_id, point, timestamp)
    VALUES (studentID, courseID, textbookID, chapterID, sectionID, blockID, uniqueActivityID, questionID, point, NOW());
END