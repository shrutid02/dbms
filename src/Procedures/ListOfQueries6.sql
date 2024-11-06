CREATE DEFINER=`root`@`localhost` PROCEDURE `GetIncorrectAnswersForQ2`()
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE option1 VARCHAR(255);
    DECLARE option2 VARCHAR(255);
    DECLARE option3 VARCHAR(255);
    DECLARE option4 VARCHAR(255);
    DECLARE explanation1 TEXT;
    DECLARE explanation2 TEXT;
    DECLARE explanation3 TEXT;
    DECLARE explanation4 TEXT;

    DECLARE cur CURSOR FOR
        SELECT option_1, opt_1_explanation, option_2, opt_2_explanation,
               option_3, opt_3_explanation, option_4, opt_4_explanation
        FROM Questions
        WHERE textbook_id = 101
          AND chapter_id = 'chap01'
          AND section_id = 'Sec02'
          AND block_id = 'Block01'
          AND unique_activity_id = 'ACT0'
          AND question_id = 'Q2';

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    CREATE TEMPORARY TABLE IF NOT EXISTS temp_incorrect_answers (
        IncorrectOption VARCHAR(255),
        Explanation TEXT
    );

    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO option1, explanation1, option2, explanation2,
                     option3, explanation3, option4, explanation4;
        IF done THEN
            LEAVE read_loop;
        END IF;

        IF explanation1 LIKE '%Incorrect%' THEN
            INSERT INTO temp_incorrect_answers (IncorrectOption, Explanation)
            VALUES (option1, explanation1);
        END IF;

        IF explanation2 LIKE '%Incorrect%' THEN
            INSERT INTO temp_incorrect_answers (IncorrectOption, Explanation)
            VALUES (option2, explanation2);
        END IF;

        IF explanation3 LIKE '%Incorrect%' THEN
            INSERT INTO temp_incorrect_answers (IncorrectOption, Explanation)
            VALUES (option3, explanation3);
        END IF;

        IF explanation4 LIKE '%Incorrect%' THEN
            INSERT INTO temp_incorrect_answers (IncorrectOption, Explanation)
            VALUES (option4, explanation4);
        END IF;

    END LOOP;

    CLOSE cur;

    SELECT * FROM temp_incorrect_answers;

    DROP TEMPORARY TABLE IF EXISTS temp_incorrect_answers;

END