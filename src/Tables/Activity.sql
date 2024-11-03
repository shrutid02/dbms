CREATE TABLE Activity (
    textbook_id INT,
    chapter_id VARCHAR(50),
    section_id VARCHAR(50),
    block_id VARCHAR(50),
    unique_activity_id VARCHAR(50),
    hidden VARCHAR(3),
    PRIMARY KEY (textbook_id, chapter_id, section_id, block_id, unique_activity_id),
    FOREIGN KEY (textbook_id) REFERENCES Textbook(textbook_id),
    FOREIGN KEY (textbook_id, chapter_id) REFERENCES Chapter(textbook_id, chapter_id),
    FOREIGN KEY (textbook_id, chapter_id, section_id) REFERENCES Sections(textbook_id, chapter_id, section_id),
    FOREIGN KEY (textbook_id, chapter_id, section_id, block_id) REFERENCES Blocks(textbook_id, chapter_id, section_number, block_id)
);

DESCRIBE Activity;

INSERT INTO Activity (textbook_id, chapter_id, section_id, block_id, unique_activity_id, hidden) VALUES
(101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'no'),
(102, 'chap01', 'Sec02', 'Block01', 'ACT0', 'no'),
(103, 'chap01', 'Sec02', 'Block01', 'ACT0', 'no');

SELECT * FROM Activity;

