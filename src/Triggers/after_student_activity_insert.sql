DELIMITER //

CREATE TRIGGER after_student_activity_insert
AFTER INSERT ON student_activity
FOR EACH ROW
BEGIN
    CALL UpdateStudentCourseSummary(NEW.student_id, NEW.course_id);
END //

CREATE TRIGGER after_student_activity_update
AFTER UPDATE ON student_activity
FOR EACH ROW
BEGIN
    IF OLD.point = 0 AND NEW.point > 0 THEN
        CALL UpdateStudentCourseSummary(NEW.student_id, NEW.course_id);
    END IF;
END //

DELIMITER ;
