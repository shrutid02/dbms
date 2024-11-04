CREATE DEFINER=`soham`@`localhost` PROCEDURE `GetBlocks`(
    IN textbookID INT,
    IN chapterID VARCHAR(50),
    IN sectionID VARCHAR(50)
)
BEGIN
    SELECT block_id, type, content, hidden
    FROM Blocks
    WHERE textbook_id = textbookID
      AND chapter_id = chapterID
      AND section_number = sectionID
    ORDER BY block_id;
END