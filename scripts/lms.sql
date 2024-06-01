Create database mydb;
go

use mydb;
go

drop table book;
drop table rack;
drop table library

CREATE TABLE library (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    start_time TIME,
    end_time TIME
);

CREATE TABLE rack (
    id INT PRIMARY KEY IDENTITY(1,1),
    library_id INT NOT NULL,
    row_number INT NOT NULL,
    column_number INT NOT NULL,
    FOREIGN KEY (library_id) REFERENCES Library(id)
);

CREATE TABLE book (
    id INT PRIMARY KEY IDENTITY(1,1),
    rack_id INT NOT NULL,
    name NVARCHAR(100) NOT NULL,
    description NVARCHAR(255),
    FOREIGN KEY (rack_id) REFERENCES Rack(id)
);

INSERT INTO library (name, start_time, end_time) VALUES
('Central Library', '09:00', '17:00'),
('Eastside Library', '08:00', '16:00'),
('Westside Library', '10:00', '18:00');

INSERT INTO rack (library_id, row_number, column_number) VALUES
(1, 1, 1),
(1, 1, 2),
(1, 2, 1),
(2, 1, 1),
(2, 1, 2),
(3, 1, 1);

INSERT INTO book (rack_id, name, description) VALUES
(1, 'Introduction to SQL', 'A comprehensive guide to SQL'),
(1, 'Database Management', 'Fundamentals of database management systems'),
(2, 'Advanced SQL Queries', 'In-depth SQL query techniques'),
(3, 'Data Warehousing', 'Concepts and design of data warehouses'),
(4, 'Data Mining', 'Techniques and applications of data mining'),
(5, 'Big Data Analytics', 'Analysis of large data sets'),
(6, 'Machine Learning', 'Introduction to machine learning algorithms');

Select * from library;
Select * from rack;
Select * from book;
