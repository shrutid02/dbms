DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `saveFaculty`(
    IN p_first_name VARCHAR(50),
    IN p_last_name VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_password VARCHAR(50)
)
BEGIN
    DECLARE current_month CHAR(2);
    DECLARE current_year CHAR(2);
    DECLARE generated_faculty_id VARCHAR(10);

    SET current_month = DATE_FORMAT(CURDATE(), '%m');
    SET current_year = DATE_FORMAT(CURDATE(), '%y');

    SET generated_faculty_id = CONCAT(LEFT(p_first_name, 2), LEFT(p_last_name, 2), current_month, current_year);

    INSERT INTO faculty (first_name, last_name, email, password, faculty_id)
    VALUES (p_first_name, p_last_name, p_email, p_password, generated_faculty_id);
END$$
DELIMITER ;
