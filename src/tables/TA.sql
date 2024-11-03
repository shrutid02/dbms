CREATE TABLE TA ( 
    ta_id CHAR(8) PRIMARY KEY, 
    first_name VARCHAR(50) NOT NULL, 
    last_name VARCHAR(50) NOT NULL, 
    email VARCHAR(255) UNIQUE NOT NULL, 
    password VARCHAR(255) NOT NULL, 
    course_id VARCHAR(50), 
    faculty_id CHAR(8), 
    FOREIGN KEY (faculty_id) REFERENCES Faculty(faculty_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

DESCRIBE TA;

INSERT INTO TA (ta_id, first_name, last_name, email, password, course_id, faculty_id)
VALUES 
	('JaWi1024', 'James', 'Williams', 'jwilliams@ncsu.edu', 'jwilliams@1234', 'NCSUOganCSC440F24', 'KeOg1024'),
	('LiAl0924', 'Lisa', 'Alberti', 'lalberti@ncsu.edu', 'lalberti&5678@', 'NCSUOganCSC540F24', 'KeOg1024'),
	('DaJo1024', 'David', 'Johnson', 'djohnson@ncsu.edu', 'djohnson%@1122', 'NCSUSaraCSC326F24', 'SaMi1024'),
	('ElCl1024', 'Ellie', 'Clark', 'eclark@ncsu.edu', 'eclark^#3654', 'NCSUJegiCSC522F24', 'JeGi0524'),
	('JeGi0924', 'Jeff', 'Gibson', 'jgibson@ncsu.edu', 'jgibson$#9877', 'NCSUSaraCSC326F25', 'SaMi1024');
    
SELECT * FROM TA;

