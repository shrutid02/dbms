CREATE TABLE Blocks (
    textbook_id INT,
    chapter_id VARCHAR(10),
    section_number VARCHAR(10),
    block_id VARCHAR(10),
    type VARCHAR(50),
    content TEXT,
    hidden VARCHAR(3),
    PRIMARY KEY (textbook_id, chapter_id, section_number, block_id),
    FOREIGN KEY (textbook_id) REFERENCES Textbook(textbook_id) ON DELETE CASCADE,
    FOREIGN KEY (textbook_id, chapter_id) REFERENCES Chapter(textbook_id, chapter_id) ON DELETE CASCADE,
    FOREIGN KEY (textbook_id, chapter_id, section_number) REFERENCES Sections(textbook_id, chapter_id, section_id) ON DELETE CASCADE
);

INSERT INTO `Blocks` (textbook_id, chapter_id, section_number, block_id, type, content, hidden) 
VALUES
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
