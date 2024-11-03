CREATE DEFINER=`soham`@`localhost` PROCEDURE `EnrollStudentCourse`(
    IN studentID CHAR(8),
    IN courseToken VARCHAR(50)
)
BEGIN
    DECLARE courseID VARCHAR(50);

    -- Find the corresponding course ID based on the course token
    SELECT course_id INTO courseID
    FROM Courses
    WHERE unique_token = courseToken;

    -- Enroll the student in the course with status 'Pending'
    INSERT INTO Enrollments (course_id, student_id, status)
    VALUES (courseID, studentID, 'Pending');
END