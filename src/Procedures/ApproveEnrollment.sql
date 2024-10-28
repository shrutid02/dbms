CREATE DEFINER=`root`@`localhost` PROCEDURE `ApproveEnrollment`(
    IN p_faculty_id VARCHAR(50),
    IN p_course_id VARCHAR(50),
    IN p_student_id VARCHAR(50)
)
BEGIN
    DECLARE v_faculty_id VARCHAR(50);
    DECLARE v_status VARCHAR(50);

    -- Check if the student is in the enrollments table with pending status
    SELECT status INTO v_status
    FROM Enrollments
    WHERE student_id = p_student_id AND course_id = p_course_id;

    IF v_status = 'Pending' THEN
        -- Check if the faculty_id matches the course's faculty_id
        SELECT faculty_id INTO v_faculty_id
        FROM Courses
        WHERE course_id = p_course_id;

        IF v_faculty_id = p_faculty_id THEN
            -- Update the student's status to enrolled
            UPDATE Enrollments
            SET status = 'Enrolled'
            WHERE student_id = p_student_id AND course_id = p_course_id;
            
            SELECT 'Enrollment approved and updated successfully.' AS message;
        ELSE
            SELECT 'Error: Faculty ID does not match the course.' AS message;
        END IF;
    ELSE
        SELECT 'Error: Student is not in pending status or does not exist.' AS message;
    END IF;
END