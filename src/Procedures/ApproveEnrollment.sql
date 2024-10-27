CREATE DEFINER=`root`@`localhost` PROCEDURE `ApproveEnrollment`(
    IN p_course_id VARCHAR(50),
    IN p_student_id VARCHAR(50)
)
BEGIN
    -- Check if the student has a 'Pending' status in the Enrollments table
    IF EXISTS (
        SELECT 1 FROM Enrollments 
        WHERE course_id = p_course_id 
        AND student_id = p_student_id 
        AND status = 'Pending'
    ) THEN
        -- If 'Pending', update status to 'Enrolled'
        UPDATE Enrollments
        SET status = 'Enrolled'
        WHERE course_id = p_course_id 
        AND student_id = p_student_id;
        
        -- Return a success message
        SELECT '\nEnrollment approved successfully!!' AS message;
    ELSE
        -- If already enrolled or no pending status, return a failure message
        SELECT '\nError - Student is already enrolled!' AS message;
    END IF;
END