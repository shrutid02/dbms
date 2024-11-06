INSERT INTO `Textbook` (textbook_id, title) VALUES
(101, 'Database Management Systems'),
(102, 'Fundamentals of Software Engineering'),
(103, 'Fundamentals of Machine Learning');

INSERT INTO `Chapter` (textbook_id, chapter_id, title, hidden) VALUES
(101, 'chap01', 'Introduction to Database', 'no'),
(101, 'chap02', 'The Relational Model', 'no'),
(102, 'chap01', 'Introduction to Software Engineering', 'no'),
(102, 'chap02', 'Introduction to Software Development Life Cycle (SDLC)', 'no'),
(103, 'chap01', 'Introduction to Machine Learning', 'no');

INSERT INTO `Sections` (textbook_id, chapter_id, section_id, title, hidden) VALUES
(101, 'chap01', 'Sec01', 'Database Management Systems (DBMS) Overview', 'no'),
(101, 'chap01', 'Sec02', 'Data Models and Schemas', 'no'),
(101, 'chap02', 'Sec01', 'Entities, Attributes, and Relationships', 'no'),
(101, 'chap02', 'Sec02', 'Normalization and Integrity Constraints', 'no'),
(102, 'chap01', 'Sec01', 'History and Evolution of Software Engineering', 'no'),
(102, 'chap01', 'Sec02', 'Key Principles of Software Engineering', 'no'),
(102, 'chap02', 'Sec01', 'Phases of the SDLC', 'yes'),
(102, 'chap02', 'Sec02', 'Agile vs. Waterfall Models', 'no'),
(103, 'chap01', 'Sec01', 'Overview of Machine Learning', 'yes'),
(103, 'chap01', 'Sec02', 'Supervised vs Unsupervised Learning', 'no');

INSERT INTO `Blocks` (textbook_id, chapter_id, section_number, block_id, type, content, hidden) VALUES
(101, 'chap01', 'Sec01', 'Block01', 'text', 'A Database Management System (DBMS) is software that enables users to efficiently create, manage, and manipulate databases. It serves as an interface between the database and end users, ensuring data is stored securely, retrieved accurately, and maintained consistently. Key features of a DBMS include data organization, transaction management, and support for multiple users accessing data concurrently.', 'no'),
(101, 'chap01', 'Sec02', 'Block01', 'activity', 'ACT0', 'no'),
(101, 'chap02', 'Sec01', 'Block01', 'text', 'DBMS systems provide structured storage and ensure that data is accessible through queries using languages like SQL. They handle critical tasks such as maintaining data integrity, enforcing security protocols, and optimizing data retrieval, making them essential for both small-scale and enterprise-level applications. Examples of popular DBMS include MySQL, Oracle, and PostgreSQL.', 'no'),
(101, 'chap02', 'Sec02', 'Block01', 'picture', 'sample.png', 'no'),
(102, 'chap01', 'Sec01', 'Block01', 'text', 'The history of software engineering dates back to the 1960s, when the "software crisis" highlighted the need for structured approaches to software development due to rising complexity and project failures. Over time, methodologies such as Waterfall, Agile, and DevOps evolved, transforming software engineering into a disciplined, iterative process that emphasizes efficiency, collaboration, and adaptability.', 'no'),
(102, 'chap01', 'Sec02', 'Block01', 'activity', 'ACT0', 'no'),
(102, 'chap02', 'Sec01', 'Block01', 'text', 'The Software Development Life Cycle (SDLC) consists of key phases including requirements gathering, design, development, testing, deployment, and maintenance. Each phase plays a crucial role in ensuring that software is built systematically, with feedback and revisions incorporated at each step to deliver a high-quality product.', 'no'),
(102, 'chap02', 'Sec02', 'Block01', 'picture', 'sample2.png', 'no'),
(103, 'chap01', 'Sec01', 'Block01', 'text', 'Machine learning is a subset of artificial intelligence that enables systems to learn from data, identify patterns, and make decisions with minimal human intervention. By training algorithms on vast datasets, machine learning models can improve their accuracy over time, driving advancements in fields like healthcare, finance, and autonomous systems.', 'no'),
(103, 'chap01', 'Sec02', 'Block01', 'activity', 'ACT0', 'no');

INSERT INTO `Activity` (textbook_id, chapter_id, section_id, block_id, unique_activity_id, hidden) VALUES
(101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'no'),
(102, 'chap01', 'Sec02', 'Block01', 'ACT0', 'no'),
(103, 'chap01', 'Sec02', 'Block01', 'ACT0', 'no');

INSERT INTO `Questions` (textbook_id, chapter_id, section_id, block_id, unique_activity_id, question_id, question_text, option_1, opt_1_explanation, option_2, opt_2_explanation, option_3, opt_3_explanation, option_4, opt_4_explanation, answer) VALUES
(101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 'What does a DBMS provide?', 'Data storage only', 'Incorrect: DBMS provides more than just storage', 'Data storage and retrieval', 'Correct: DBMS manages both storing and retrieving data', 'Only security features', 'Incorrect: DBMS also handles other functions', 'Network management', 'Incorrect: DBMS does not manage network infrastructure', 2),
(101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q2', 'Which of these is an example of a DBMS?', 'Microsoft Excel', 'Incorrect: Excel is a spreadsheet application', 'MySQL', 'Correct: MySQL is a popular DBMS', 'Google Chrome', 'Incorrect: Chrome is a web browser', 'Windows 10', 'Incorrect: Windows is an operating system', 2),
(101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q3', 'What type of data does a DBMS manage?', 'Structured data', 'Correct: DBMS primarily manages structured data', 'Unstructured multimedia', 'Incorrect: While some DBMS systems can handle it, it\'s not core', 'Network traffic data', 'Incorrect: DBMS doesn’t manage network data', 'Hardware usage statistics', 'Incorrect: DBMS does not handle hardware usage data', 1),
(102, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 'What was the "software crisis"?', 'A hardware shortage', 'Incorrect: The crisis was related to software development issues', 'Difficulty in software creation', 'Correct: The crisis was due to the complexity and unreliability of software', 'A network issue', 'Incorrect: It was not related to networking', 'Lack of storage devices', 'Incorrect: The crisis was not about physical storage limitations', 2),
(102, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q2', 'Which methodology was first introduced in software engineering?', 'Waterfall model', 'Correct: Waterfall was the first formal software development model', 'Agile methodology', 'Incorrect: Agile was introduced much later', 'DevOps', 'Incorrect: DevOps is a more recent development approach', 'Scrum', 'Incorrect: Scrum is a part of Agile, not the first methodology', 1),
(102, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q3', 'What challenge did early software engineering face?', 'Lack of programming languages', 'Incorrect: Programming languages existed but were difficult to manage', 'Increasing complexity of software', 'Correct: Early engineers struggled with managing large, complex systems', 'Poor hardware development', 'Incorrect: The issue was primarily with software, not hardware', 'Internet connectivity issues', 'Incorrect: Internet connectivity wasn\'t a challenge in early software', 2),
(103, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 'What is the primary goal of supervised learning?', 'Predict outcomes', 'Correct: The goal is to learn a mapping from inputs to outputs for prediction.', 'Group similar data', 'Incorrect: This is more aligned with unsupervised learning.', 'Discover patterns', 'Incorrect: This is not the main goal of supervised learning.', 'Optimize cluster groups', 'Incorrect: This is not applicable to supervised learning.', 1),
(103, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q2', 'Which type of data is used in unsupervised learning?', 'Labeled data', 'Incorrect: Unsupervised learning uses unlabeled data.', 'Unlabeled data', 'Correct: It analyzes data without pre-existing labels.', 'Structured data', 'Incorrect: Unlabeled data can be structured or unstructured.', 'Time-series data', 'Incorrect: Unsupervised learning does not specifically focus on time-series.', 2),
(103, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q3', 'In which scenario would you typically use supervised learning?', 'Customer segmentation', 'Incorrect: This is more relevant to unsupervised learning.', 'Fraud detection', 'Correct: Supervised learning is ideal for predicting fraud based on labeled examples.', 'Market basket analysis', 'Incorrect: This is generally done using unsupervised methods.', 'Anomaly detection', 'Incorrect: While applicable, it is less common than fraud detection in supervised learning.', 2);

INSERT INTO Courses (course_id, course_name, textbook_id, course_type, faculty_id, ta_id, start_date, end_date, unique_token, course_capacity)
VALUES
('NCSUOganCSC440F24', 'CSC440 Database Systems', 101, 'Active', 'KeOg1024', 'JaWi1024', '2024-08-15', '2024-12-15', 'XYJKLM', 60),
('NCSUOganCSC540F24', 'CSC540 Database Systems', 101, 'Active', 'KeOg1024', 'LiAl0924', '2024-08-17', '2024-12-15', 'STUKZT', 50),
('NCSUSaraCSC326F24', 'CSC326 Software Engineering', 102, 'Active', 'SaMi1024', 'DaJo1024', '2024-08-23', '2024-10-23', 'LRUFND', 100),
('NCSUDoeCSC522F24', 'CSC522 Fundamentals of Machine Learning', 103, 'Evaluation', 'JoDo1024', NULL, '2025-08-25', '2025-12-18', NULL, NULL),
('NCSUSaraCSC326F25', 'CSC326 Software Engineering', 102, 'Evaluation', 'SaMi1024', NULL, '2025-08-27', '2025-12-19', NULL, NULL);

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
('NCSUOganCSC440F24', 'LeMe1024', 'Enrolled'),
('NCSUOganCSC440F24', 'FiWi1024', 'Pending'),
('NCSUOganCSC540F24', 'LeMe1024', 'Pending'),
('NCSUOganCSC540F24', 'AlAr1024', 'Pending'),
('NCSUOganCSC540F24', 'SiHa1024', 'Pending'),
('NCSUOganCSC540F24', 'FiWi1024', 'Pending');

INSERT INTO TA (ta_id, first_name, last_name, email, password, course_id, faculty_id)
VALUES
('JaWi1024', 'James', 'Williams', 'jwilliams@ncsu.edu', 'jwilliams@1234', 'NCSUOganCSC440F24', 'KeOg1024'),
('LiAl0924', 'Lisa', 'Alberti', 'lalberti@ncsu.edu', 'lalberti&5678@', 'NCSUOganCSC540F24', 'KeOg1024'),
('DaJo1024', 'David', 'Johnson', 'djohnson@ncsu.edu', 'djohnson%@1122', 'NCSUSaraCSC326F24', 'SaMi1024'),
('ElCl1024', 'Ellie', 'Clark', 'eclark@ncsu.edu', 'eclark^#3654', NULL, NULL),
('JeGi0924', 'Jeff', 'Gibson', 'jgibson@ncsu.edu', 'jgibson$#9877', NULL, NULL);

INSERT INTO Faculty (faculty_id, first_name, last_name, email, password)
VALUES
('KeOg1024', 'Kemafor', 'Ogan', 'kogan@ncsu.edu', 'Ko2024!rpc'),
('JoDo1024', 'John', 'Doe', 'john.doe@example.com', 'Jd2024!abc'),
('SaMi1024', 'Sarah', 'Miller', 'sarah.miller@domain.com', 'Sm#Secure2024'),
('DaBr1024', 'David', 'Brown', 'david.b@webmail.com', 'DbPass123!'),
('EmDa1024', 'Emily', 'Davis', 'emily.davis@email.com', 'Emily#2024!'),
('MiWi1024', 'Michael', 'Wilson', 'michael.w@service.com', 'Mw987secure');

INSERT INTO students (student_id, full_name, username, password, email) VALUES
('ErPe1024', 'Eric Perrig', 'ez356', 'qwdmq', 'ez356@example.mail'),
('AlAr1024', 'Alice Artho', 'aa23', 'omdsws', 'aa23@edu.mail'),
('BoTe1024', 'Bob Temple', 'bt163', 'sak+=', 'bt163@template.mail'),
('LiGa1024', 'Lily Gaddy', 'li123', 'cnaos', 'li123@example.edu'),
('ArMo1024', 'Aria Morrow', 'am213', 'jwocals', 'am213@example.edu'),
('KeRh1014', 'Kellan Rhodes', 'kr21', 'camome', 'kr21@example.edu'),
('SiHa1024', 'Sienna Hayes', 'sh13', 'asdqm', 'sh13@example.edu'),
('FiWi1024', 'Finn Wilder', 'fw23', 'f13mas', 'fw23@example.edu'),
('LeMe1024', 'Leona Mercer', 'lm56', 'ca32', 'lm56@example.edu');

INSERT INTO `student_courses` (student_id, registered_course_id, total_participation_points, num_finished_activities) VALUES
('ErPe1024', 'NCSUOganCSC440F24', 4, 2),
('ErPe1024', 'NCSUOganCSC540F24', 1, 1),
('AlAr1024', 'NCSUOganCSC440F24', 3, 1),
('BoTe1024', 'NCSUOganCSC440F24', 0, 1),
('LiGa1024', 'NCSUOganCSC440F24', 9, 3),
('LiGa1024', 'NCSUOganCSC540F24', 0, 0),
('ArMo1024', 'NCSUOganCSC540F24', 0, 0),
('ArMo1024', 'NCSUOganCSC440F24', 4, 2),
('SiHa1024', 'NCSUOganCSC440F24', 0, 0),
('FiWi1024', 'NCSUSaraCSC326F24', 1, 1);

INSERT INTO `student_activity` (student_id, course_id, textbook_id, chapter_id, section_id, block_id, unique_activity_id, question_id, point, timestamp) VALUES
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
('FiWi1024', 'NCSUSaraCSC326F24', 102, 'chap01', 'Sec02', 'Block01', 'ACT0', 'Q1', 1, '2024-08-29 22:41:00');

