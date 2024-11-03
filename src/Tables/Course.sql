CREATE TABLE Courses (
    course_id VARCHAR(50) PRIMARY KEY,
    course_name VARCHAR(255) NOT NULL,
    textbook_id INT,
    course_type ENUM('Active', 'Evaluation'),
    faculty_id CHAR(8),
    ta_id CHAR(8),
    start_date DATE,
    end_date DATE,
    unique_token VARCHAR(50),
    course_capacity INT,
    FOREIGN KEY (textbook_id) REFERENCES Textbook(textbook_id)
    -- FOREIGN KEY (faculty_id) REFERENCES Faculty(faculty_id)
);

DESCRIBE Courses;

INSERT INTO Courses (
    course_id, course_name, textbook_id, course_type, faculty_id, ta_id, start_date, end_date, unique_token, course_capacity
) 
VALUES
	('NCSUOganCSC440F24', 'CSC440 Database Systems', 101, 'Active', 'KeOg1024', 'JaWi1024', '2024-08-15', '2024-12-15', 'XYJKLM', 60),
	('NCSUOganCSC540F24', 'CSC540 Database Systems', 101, 'Active', 'KeOg1024', 'LiAl0924', '2024-08-17', '2024-12-15', 'STUKZT', 50),
	('NCSUSaraCSC326F24', 'CSC326 Software Engineering', 102, 'Active', 'SaMi1024', 'DaJo1024', '2024-08-23', '2024-10-23', 'LRUFND', 100),
	('NCSUJegiCSC522F24', 'CSC522 Fundamentals of Machine Learning', 103, 'Evaluation', 'JeGi0524', 'ElCl1024', '2025-08-25', '2025-12-18', NULL, NULL),
	('NCSUSaraCSC326F25', 'CSC326 Software Engineering', 102, 'Evaluation', 'SaMi1024', 'JeGi0924', '2025-08-27', '2025-12-19', NULL, NULL);

SELECT * FROM Courses;

ALTER TABLE Courses
ADD CONSTRAINT
FOREIGN KEY (faculty_id) REFERENCES Faculty(faculty_id);
