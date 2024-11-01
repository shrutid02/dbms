CREATE DEFINER=`root`@`localhost` PROCEDURE `ChangePassword`(
    IN p_user_id VARCHAR(50),
    IN p_current_password VARCHAR(255),
    IN p_new_password VARCHAR(255),
    IN p_role ENUM('Faculty', 'TA'),
    OUT message VARCHAR(255)
)
BEGIN
    DECLARE v_password VARCHAR(255);

    -- Conditional logic based on role
    IF p_role = 'Faculty' THEN
        -- Retrieve current password from Faculty table
        SELECT password INTO v_password
        FROM Faculty
        WHERE faculty_id = p_user_id;

    ELSEIF p_role = 'TA' THEN
        -- Retrieve current password from TA table
        SELECT password INTO v_password
        FROM TA
        WHERE ta_id = p_user_id;

    END IF;

    -- Check if the user exists and the current password matches
    IF v_password IS NULL THEN
        SET message = 'User ID not found.';
    ELSEIF v_password != p_current_password THEN
        SET message = 'Error - Current password is incorrect.';
    ELSE
        -- Update the password in the respective table if current password is correct
        IF p_role = 'Faculty' THEN
            UPDATE Faculty
            SET password = p_new_password
            WHERE faculty_id = p_user_id;
        ELSEIF p_role = 'TA' THEN
            UPDATE TA
            SET password = p_new_password
            WHERE ta_id = p_user_id;
        END IF;

        SET message = 'Password updated successfully!!';
    END IF;
END