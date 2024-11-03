CREATE TABLE Faculty (
    faculty_id CHAR(8) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

DESCRIBE Faculty;

INSERT INTO Faculty (faculty_id, first_name, last_name, email, password)
VALUES
	('KeOg1024', 'Kemafor', 'Ogan', 'kogan@ncsu.edu', 'Ko2024!rpc'),
	('JoDo1024', 'John', 'Doe', 'john.doe@example.com', 'Jd2024!abc'),
	('SaMi1024', 'Sarah', 'Miller', 'sarah.miller@domain.com', 'Sm#Secure2024'),
	('DaBr1024', 'David', 'Brown', 'david.b@webmail.com', 'DbPass123!'),
	('EmDa1024', 'Emily', 'Davis', 'emily.davis@email.com', 'Emily#2024!'),
	('MiWi1024', 'Michael', 'Wilson', 'michael.w@service.com', 'Mw987secure'),
	('JeGi0524', 'Jenny', 'Gilbert', 'jenny.gilbert@email.com', 'Jenny#2024!');
    
SELECT * FROM Faculty;
