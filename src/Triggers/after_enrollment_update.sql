DELIMITER //

CREATE TRIGGER after_enrollment_update
AFTER UPDATE ON Enrollments
FOR EACH ROW
BEGIN
    -- Check if the student's status has been changed to 'Enrolled'
    IF NEW.status = 'Enrolled' AND OLD.status <> 'Enrolled' THEN
        -- Only insert into student_courses if the student-course record doesn't already exist
        IF NOT EXISTS (
            SELECT 1 FROM student_courses 
            WHERE student_id = NEW.student_id 
              AND registered_course_id = NEW.course_id
        ) THEN
            INSERT INTO student_courses (student_id, registered_course_id, total_participation_points, num_finished_activities)
            VALUES (NEW.student_id, NEW.course_id, 0, 0);
        END IF;
    END IF;
END //

DELIMITER ;
