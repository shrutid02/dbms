CREATE TABLE Sections (
    textbook_id INT,
    chapter_id VARCHAR(10),
    section_id VARCHAR(10),
    title VARCHAR(255),
    hidden VARCHAR(3),
    PRIMARY KEY (textbook_id, chapter_id, section_id),
    FOREIGN KEY (textbook_id) REFERENCES Textbook(textbook_id),
    FOREIGN KEY (textbook_id, chapter_id) REFERENCES Chapter(textbook_id, chapter_id)
);
DESCRIBE Sections;

INSERT INTO Sections (textbook_id, chapter_id, section_id, title, hidden) VALUES
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

SELECT * FROM Sections;
