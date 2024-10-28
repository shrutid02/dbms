CREATE DEFINER=`root`@`localhost` PROCEDURE `AddTA`(
    IN p_course_id VARCHAR(50),
    IN p_faculty_id VARCHAR(50),
    IN p_first_name VARCHAR(50),
    IN p_last_name VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_default_password VARCHAR(50),
    OUT p_message VARCHAR(100)
)
BEGIN
    DECLARE v_ta_id CHAR(8);
    DECLARE v_random_number CHAR(4);
    
    -- Call the existing procedure to check if the course is active
    CALL CheckCourseType(p_course_id, @course_message);

    -- Check the message returned by CheckCourseType
    IF @course_message = 'Active course, you may continue.' THEN
        -- Generate TA_id
        SET v_random_number = LPAD(FLOOR(RAND() * 10000), 4, '0');
        SET v_ta_id = CONCAT(SUBSTRING(p_first_name, 1, 2), SUBSTRING(p_last_name, 1, 2), v_random_number);

        -- Insert the new TA into the TA table
        INSERT INTO TA (ta_id, first_name, last_name, email, password, course_id, faculty_id)
        VALUES (v_ta_id, p_first_name, p_last_name, p_email, p_default_password, p_course_id, p_faculty_id);

        SET p_message = CONCAT('\nTA added successfully with TA_id: ', v_ta_id);
    ELSE
        SET p_message = '\nError: Course is not active.';
    END IF;
END