CREATE DEFINER=`soham`@`localhost` PROCEDURE `GetQuestions`(
    IN textbookID INT,
    IN chapterID VARCHAR(50),
    IN sectionID VARCHAR(50),
    IN blockID VARCHAR(50)
)
BEGIN
    SELECT q.question_text, q.option_1, q.option_2, q.option_3, q.option_4
    FROM Questions q
    JOIN Activity a ON q.textbook_id = a.textbook_id
                   AND q.chapter_id = a.chapter_id
                   AND q.section_id = a.section_id
                   AND q.block_id = a.block_id
                   AND q.unique_activity_id = a.unique_activity_id
    WHERE q.textbook_id = textbookID
      AND q.chapter_id = chapterID
      AND q.section_id = sectionID
      AND q.block_id = blockID
      AND a.hidden = 'no'
    ORDER BY q.question_id;
END