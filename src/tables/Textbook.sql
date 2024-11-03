CREATE TABLE Textbook (
    textbook_id INT PRIMARY KEY,
    title VARCHAR(255)
);

DESCRIBE Textbook;

INSERT INTO Textbook (textbook_id, title) VALUES
(101, 'Database Management Systems'),
(102, 'Fundamentals of Software Engineering'),
(103, 'Fundamentals of Machine Learning');

SELECT * FROM Textbook;


