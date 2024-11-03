CREATE TABLE students (
    student_id CHAR(8) PRIMARY KEY,
    full_name VARCHAR(100),
    username VARCHAR(50),
    password VARCHAR(50),
    email VARCHAR(100)
);

DESCRIBE students;

INSERT INTO students (student_id, full_name, username, password, email) 
VALUES
	('ErPe1024', 'Eric Perrig', 'ez356', 'qwdmq', 'ez356@example.mail'), 
	('AlAr1024', 'Alice Artho', 'aa23', 'omdsws', 'aa23@edu.mail'), 
	('BoTe1024', 'Bob Temple', 'bt163', 'sak+=', 'bt163@template.mail'), 
	('LiGa1024', 'Lily Gaddy', 'li123', 'cnaos', 'li123@example.edu'), 
	('ArMo1024', 'Aria Morrow', 'am213', 'jwocals', 'am213@example.edu'), 
	('KeRh1014', 'Kellan Rhodes', 'kr21', 'camome', 'kr21@example.edu'), 
	('SiHa1024', 'Sienna Hayes', 'sh13', 'asdqm', 'sh13@example.edu'), 
	('FiWi1024', 'Finn Wilder', 'fw23', 'f13mas', 'fw23@example.edu'), 
	('LeMe1024', 'Leona Mercer', 'lm56', 'ca32', 'lm56@example.edu');
    
SELECT * FROM students;
