CREATE DEFINER=`root`@`localhost` PROCEDURE `CountSectionsInFirstChapter`(IN input_textbook_id INT)
BEGIN
    SELECT COUNT(*) AS section_count
    FROM Sections
    WHERE textbook_id = input_textbook_id AND chapter_id = 'chap01';
END