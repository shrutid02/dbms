CREATE DEFINER=`soham`@`localhost` PROCEDURE `GetChaptersAndSections`(
    IN textbookID INT
)
BEGIN
    SELECT c.chapter_id, c.title AS chapter_title, s.section_id, s.title AS section_title
    FROM Chapter c
    LEFT JOIN Sections s ON c.textbook_id = s.textbook_id AND c.chapter_id = s.chapter_id
    WHERE c.textbook_id = textbookID AND c.hidden = 'no' AND (s.hidden = 'no' OR s.hidden IS NULL)
    ORDER BY c.chapter_id, s.section_id;
END