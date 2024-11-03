CREATE DEFINER=`soham`@`localhost` PROCEDURE `CheckEnrollmentExists`(
    IN studentID CHAR(8),
    IN courseToken VARCHAR(50),
    OUT enrollmentExists BOOLEAN
)
BEGIN
    DECLARE courseID VARCHAR(50);

    -- Retrieve the corresponding course_id based on the course token
    SELECT course_id INTO courseID
    FROM Courses
    WHERE unique_token = courseToken
    LIMIT 1;

    -- Check if the student has already enrolled in the course
    IF courseID IS NOT NULL THEN
        SELECT COUNT(*) INTO @count
        FROM Enrollments
        WHERE course_id = courseID AND student_id = studentID;

        IF @count > 0 THEN
            SET enrollmentExists = TRUE;
        ELSE
            SET enrollmentExists = FALSE;
        END IF;
    ELSE
        SET enrollmentExists = FALSE;
    END IF;
END