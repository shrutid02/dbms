CREATE DEFINER=`root`@`localhost` PROCEDURE `GetChapterContent`(
    IN p_textbook_id INT,
    IN p_chapter_id VARCHAR(10)
)
BEGIN
    SELECT 
        s.title AS section_title,
        b.type AS block_type,
        b.content AS block_content,
        a.unique_activity_id AS activity_id,
        q.question_id AS question_id,
        q.question_text AS question_text,
        q.option_1 AS option_1,
        q.opt_1_explanation AS opt_1_explanation,
        q.option_2 AS option_2,
        q.opt_2_explanation AS opt_2_explanation,
        q.option_3 AS option_3,
        q.opt_3_explanation AS opt_3_explanation,
        q.option_4 AS option_4,
        q.opt_4_explanation AS opt_4_explanation,
        q.answer AS answer
    FROM 
        Sections s
    JOIN 
        Blocks b 
    ON 
        s.textbook_id = b.textbook_id 
        AND s.chapter_id = b.chapter_id 
        AND s.section_id = b.section_number
    LEFT JOIN 
        Activity a 
    ON 
        b.textbook_id = a.textbook_id 
        AND b.chapter_id = a.chapter_id 
        AND b.section_number = a.section_id 
        AND b.block_id = a.block_id
    LEFT JOIN 
        Questions q 
    ON 
        a.textbook_id = q.textbook_id 
        AND a.chapter_id = q.chapter_id 
        AND a.section_id = q.section_id 
        AND a.block_id = q.block_id 
        AND a.unique_activity_id = q.unique_activity_id
    WHERE 
        s.textbook_id = p_textbook_id
        AND s.chapter_id = p_chapter_id
    ORDER BY 
        s.section_id, b.block_id, a.unique_activity_id, q.question_id;
END