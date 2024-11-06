CREATE TABLE Notifications (
    student_id CHAR(8),
    message TEXT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(student_id)
);
