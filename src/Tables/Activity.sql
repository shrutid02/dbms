CREATE TABLE Activity (
    textbook_id INT,
    chapter_id VARCHAR(10),
    section_id VARCHAR(10),
    block_id VARCHAR(10),
    unique_activity_id VARCHAR(10),
    hidden VARCHAR(3),
    PRIMARY KEY (textbook_id, chapter_id, section_id, block_id, unique_activity_id),
    FOREIGN KEY (textbook_id) REFERENCES Textbook(textbook_id) ON DELETE CASCADE,
    FOREIGN KEY (textbook_id, chapter_id) REFERENCES Chapter(textbook_id, chapter_id) ON DELETE CASCADE,
    FOREIGN KEY (textbook_id, chapter_id, section_id) REFERENCES Sections(textbook_id, chapter_id, section_id) ON DELETE CASCADE,
    FOREIGN KEY (textbook_id, chapter_id, section_id, block_id) REFERENCES Blocks(textbook_id, chapter_id, section_number, block_id) ON DELETE CASCADE
);


INSERT INTO `Activity` (textbook_id, chapter_id, section_id, block_id, unique_activity_id, hidden) 
VALUES
(101, 'chap01', 'Sec02', 'Block01', 'ACT0', 'no'),
(102, 'chap01', 'Sec02', 'Block01', 'ACT0', 'no'),
(103, 'chap01', 'Sec02', 'Block01', 'ACT0', 'no');
