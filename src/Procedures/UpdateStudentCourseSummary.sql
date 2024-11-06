CREATE DEFINER=`soham`@`localhost` PROCEDURE `UpdateStudentCourseSummary`(
    IN studentID CHAR(8),
    IN courseID VARCHAR(50)
)
BEGIN
    -- Update total participation points
    UPDATE student_courses sc
    JOIN (
        SELECT student_id, course_id, SUM(point) AS total_points
        FROM student_activity
        WHERE student_id = studentID
          AND course_id = courseID
        GROUP BY student_id, course_id
    ) sa ON sc.student_id = sa.student_id AND sc.registered_course_id = sa.course_id
    SET sc.total_participation_points = sa.total_points;

    -- Update number of finished activities, counting each question individually
    UPDATE student_courses sc
    JOIN (
        SELECT student_id, course_id, COUNT(*) AS finished_activities
        FROM student_activity
        WHERE student_id = studentID
          AND course_id = courseID
          AND point > 0  -- Only count attempted questions
        GROUP BY student_id, course_id
    ) sa ON sc.student_id = sa.student_id AND sc.registered_course_id = sa.course_id
    SET sc.num_finished_activities = sa.finished_activities;
END