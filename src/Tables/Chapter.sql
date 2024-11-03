CREATE TABLE Chapter (
    textbook_id INT,
    chapter_id VARCHAR(10),
    title VARCHAR(255),
    hidden VARCHAR(3),
    PRIMARY KEY (textbook_id, chapter_id),
    FOREIGN KEY (textbook_id) REFERENCES Textbook(textbook_id)
);

DESCRIBE Chapter;

INSERT INTO Chapter (textbook_id, chapter_id, title, hidden) VALUES
(101, 'chap01', 'introduction to Database', 'no'),
(101, 'chap02', 'The Relational Model', 'no'),
(102, 'chap01', 'introduction to Software Engineering', 'no'),
(102, 'chap02', 'introduction to Software Development Life Cycle (SDLC)', 'no'),
(103, 'chap01', 'Introduction to Machine Learning', 'no');

SELECT * FROM Chapter;