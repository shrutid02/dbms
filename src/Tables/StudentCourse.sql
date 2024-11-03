CREATE TABLE student_courses (
    student_id CHAR(8),
    registered_course_id VARCHAR(50),
    total_participation_points INT,
    num_finished_activities INT,
    PRIMARY KEY (student_id, registered_course_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (registered_course_id) REFERENCES Courses(course_id)
);

DESCRIBE student_courses;

INSERT INTO student_courses (student_id, registered_course_id, total_participation_points, num_finished_activities) 
VALUES
	('ErPe1024', 'NCSUOganCSC440F24', 4, 2), 
	('ErPe1024', 'NCSUOganCSC540F24', 1, 1), 
	('AlAr1024', 'NCSUOganCSC440F24', 3, 1), 
	('BoTe1024', 'NCSUOganCSC440F24', 0, 1), 
	('LiGa1024', 'NCSUOganCSC440F24', 9, 3), 
	('LiGa1024', 'NCSUOganCSC540F24', 0, 0),
	('SiHa1024', 'NCSUOganCSC440F24', 0, 0), 
	('FiWi1024', 'NCSUSaraCSC326F24', 1, 1), 
	('LeMe1024', 'NCSUJegiCSC522F24', 6, 2);
    
SELECT * FROM student_courses;
