CREATE TABLE student_activity (
    student_id CHAR(8),
    course_id VARCHAR(50),
    textbook_id INT,
    chapter_id VARCHAR(50),
    section_id VARCHAR(50),
    block_id VARCHAR(50),
    unique_activity_id VARCHAR(50),
    question_id VARCHAR(50),
    point INT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (student_id, course_id, textbook_id, chapter_id, section_id, block_id, unique_activity_id, question_id),
    
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id),
    FOREIGN KEY (textbook_id) REFERENCES Textbook(textbook_id),
    FOREIGN KEY (textbook_id,chapter_id) REFERENCES Chapter(textbook_id,chapter_id),
    FOREIGN KEY (textbook_id,chapter_id,section_id) REFERENCES sections(textbook_id,chapter_id,section_id),
    FOREIGN KEY (textbook_id,chapter_id,section_id,block_id) REFERENCES blocks(textbook_id,chapter_id,section_number,block_id),
    FOREIGN KEY (textbook_id,chapter_id,section_id,block_id,unique_activity_id) REFERENCES activity(textbook_id,chapter_id,section_id,block_id,unique_activity_id),
    FOREIGN KEY (textbook_id,chapter_id,section_id,block_id,unique_activity_id,question_id) REFERENCES questions(textbook_id,chapter_id,section_id,block_id,unique_activity_id,question_id)
);


DESCRIBE student_activity;

INSERT INTO student_activity (student_id, course_id, textbook_id, chapter_id, section_id, block_id, unique_activity_id, question_id, point, timestamp) 
VALUES
	('ErPe1024', 'NCSUOganCSC440F24', 101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 3, '2024-08-01 11:10:00'), 
	('ErPe1024', 'NCSUOganCSC440F24', 101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q2', 1, '2024-08-01 14:18:00'), 
	('ErPe1024', 'NCSUOganCSC540F24', 101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 1, '2024-08-02 19:06:00'), 
	('AlAr1024', 'NCSUOganCSC440F24', 101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 3, '2024-08-01 13:24:00'), 
	('BoTe1024', 'NCSUOganCSC440F24', 101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 0, '2024-08-01 09:24:00'), 
	('LiGa1024', 'NCSUOganCSC440F24', 101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 3, '2024-08-01 07:45:00'), 
	('LiGa1024', 'NCSUOganCSC440F24', 101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q2', 3, '2024-08-01 12:30:00'), 
	('LiGa1024', 'NCSUOganCSC540F24', 101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 3, '2024-08-03 16:52:00'), 
	('ArMo1024', 'NCSUOganCSC440F24', 101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 1, '2024-08-01 21:18:00'), 
	('ArMo1024', 'NCSUOganCSC440F24', 101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q2', 3, '2024-08-01 05:03:00'), 
	('FiWi1024', 'NCSUSaraCSC326F24', 102, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 1, '2024-08-29 22:41:00'), 
	('LeMe1024', 'NCSUJegiCSC522F24', 103, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 3, '2024-09-01 13:12:00'), 
	('LeMe1024', 'NCSUJegiCSC522F24', 103, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q2', 3, '2024-09-09 18:27:00');
    
SELECT * FROM student_activity;
