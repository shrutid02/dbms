CREATE TABLE Enrollments (
    course_id VARCHAR(50),
    student_id CHAR(8),
    status VARCHAR(50),
    PRIMARY KEY (course_id, student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id),
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);

DESCRIBE Enrollments;

INSERT INTO Enrollments (course_id, student_id, status) 
VALUES
	('NCSUOganCSC440F24', 'ErPe1024', 'Enrolled'), 
	('NCSUOganCSC540F24', 'ErPe1024', 'Enrolled'), 
	('NCSUOganCSC440F24', 'AlAr1024', 'Enrolled'), 
	('NCSUOganCSC440F24', 'BoTe1024', 'Enrolled'), 
	('NCSUOganCSC440F24', 'LiGa1024', 'Enrolled'), 
	('NCSUOganCSC540F24', 'LiGa1024', 'Enrolled'), 
	('NCSUOganCSC540F24', 'ArMo1024', 'Enrolled'), 
	('NCSUOganCSC440F24', 'ArMo1024', 'Enrolled'), 
	('NCSUOganCSC440F24', 'SiHa1024', 'Enrolled'), 
	('NCSUSaraCSC326F24', 'FiWi1024', 'Enrolled'), 
	('NCSUJegiCSC522F24', 'LeMe1024', 'Enrolled'), 
	('NCSUSaraCSC326F25', 'ErPe1024', 'Pending'), 
	('NCSUSaraCSC326F25', 'AlAr1024', 'Pending'), 
	('NCSUJegiCSC522F24', 'FiWi1024', 'Pending'), 
	('NCSUSaraCSC326F25', 'LeMe1024', 'Pending'), 
	('NCSUSaraCSC326F25', 'ArMo1024', 'Pending'), 
	('NCSUJegiCSC522F24', 'LiGa1024', 'Pending');

SELECT * FROM Enrollments;
