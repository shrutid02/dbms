CREATE DEFINER=`root`@`localhost` PROCEDURE `ListOfQueries7`()
BEGIN
    SELECT DISTINCT t.title, 
           c1.course_type AS status_by_instructor1, 
           c1.faculty_id AS instructor1,
           c2.course_type AS status_by_instructor2, 
           c2.faculty_id AS instructor2
    FROM Textbook t
    JOIN Courses c1 ON t.textbook_id = c1.textbook_id
    JOIN Courses c2 ON t.textbook_id = c2.textbook_id
    WHERE c1.course_type = 'Active'
      AND c2.course_type = 'Evaluation'
      AND c1.faculty_id <> c2.faculty_id;
END