-- CREATE DATABASE universitiesDB;
-- USE universitiesDB;

-- Section 1: Table creation
-- P01: Table Design

CREATE TABLE countries
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE cities
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(40) NOT NULL UNIQUE,
    population INT,
    country_id INT         NOT NULL,
    CONSTRAINT fk_cities_countries
        FOREIGN KEY (country_id)
            REFERENCES countries (id)
);

CREATE TABLE universities
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(60)    NOT NULL UNIQUE,
    address         VARCHAR(80)    NOT NULL UNIQUE,
    tuition_fee     DECIMAL(19, 2) NOT NULL,
    number_of_staff INT,
    city_id         INT,
    CONSTRAINT fk_universities_cities
        FOREIGN KEY (city_id)
            REFERENCES cities (id)

);

CREATE TABLE students
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    first_name   VARCHAR(40)  NOT NULL,
    last_name    VARCHAR(40)  NOT NULL,
    age          INT,
    phone        VARCHAR(20)  NOT NULL UNIQUE,
    email        VARCHAR(255) NOT NULL UNIQUE,
    is_graduated TINYINT(1)   NOT NULL DEFAULT 0,
    city_id      INT,
    CONSTRAINT fk_students_city
        FOREIGN KEY (city_id)
            REFERENCES cities (id)
);

CREATE TABLE courses
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(40) NOT NULL UNIQUE,
    duration_hours DECIMAL(19, 2),
    start_date     DATE,
    teacher_name   VARCHAR(60) NOT NULL UNIQUE,
    description    TEXT,
    university_id  INT,
    CONSTRAINT fk_courses_universities
        FOREIGN KEY (university_id)
            REFERENCES universities (id)
);

CREATE TABLE students_courses
(
    grade      DECIMAL(19, 2) NOT NULL,
    student_id INT            NOT NULL,
    course_id  INT            NOT NULL,
    CONSTRAINT fk_studentsCourses_students
        FOREIGN KEY (student_id)
            REFERENCES students (id),
    CONSTRAINT fk_studentsCourses_courses
        FOREIGN KEY (course_id)
            REFERENCES courses (id)
);

-- Section 2: CRUD
-- P02: Insert

INSERT INTO courses(name, duration_hours, start_date, teacher_name, description, university_id)
SELECT CONCAT(teacher_name, ' course'),
       CHAR_LENGTH(name) / 10,
       DATE_ADD(start_date, INTERVAL 5 DAY),
       REVERSE(teacher_name),
       CONCAT('Course ', teacher_name, REVERSE(description)),
       DAY(start_date)
FROM courses
WHERE id <= 5;

-- P03: Update

UPDATE universities
SET tuition_fee = tuition_fee + 300
WHERE id >= 5
  AND id <= 12;

-- P04: Delete

DELETE
FROM universities
WHERE number_of_staff IS NULL;

-- Section 3: Querying
-- P05: Cities

SELECT id, name, population, country_id
FROM cities
ORDER BY population DESC;

-- P06: Students age

SELECT first_name, last_name, age, phone, email
FROM students
WHERE age >= 21
ORDER BY first_name DESC, email ASC, id ASC
LIMIT 10;

-- P07: New students

SELECT CONCAT_WS(' ', first_name, last_name) AS full_name,
       SUBSTR(email, 2, 10)                  AS username,
       REVERSE(phone)                        AS password
FROM students
         LEFT JOIN students_courses sc on students.id = sc.student_id
WHERE sc.course_id IS NULL
ORDER BY password DESC;

-- P08: Students count

SELECT COUNT(sc.student_id) AS students_count,
       u.name               AS university_name
FROM universities AS u
         JOIN courses c ON u.id = c.university_id
         JOIN students_courses sc ON c.id = sc.course_id
GROUP BY u.name
HAVING students_count >= 8
ORDER BY students_count DESC, university_name DESC;

-- P09: Price rankings

SELECT u.name  AS university_name,
       c.name  AS city_name,
       u.address,
       CASE
           WHEN u.tuition_fee < 800 THEN 'cheap'
           WHEN u.tuition_fee >= 800 AND u.tuition_fee < 1200 THEN 'normal'
           WHEN u.tuition_fee >= 1200 AND u.tuition_fee < 2500 THEN 'high'
           ELSE 'expensive'
           END AS price_rank,
       u.tuition_fee
FROM universities u
         JOIN cities c ON u.city_id = c.id
ORDER BY u.tuition_fee ASC;

-- Section 4: Programmability
-- P10: Average grades

DELIMITER $$
CREATE FUNCTION udf_average_alumni_grade_by_course_name(course_name VARCHAR(60))
    RETURNS DECIMAL(19, 2)
    DETERMINISTIC
BEGIN
    DECLARE avg_grade DECIMAL(19, 2);
    SELECT AVG(sc.grade)
    INTO avg_grade
    FROM students_courses sc
             JOIN students s ON s.id = sc.student_id
             JOIN courses c ON c.id = sc.course_id
    WHERE s.is_graduated = 1
      AND c.name = course_name;
    RETURN avg_grade;
END$$
DELIMITER ;

-- Test Result:
SELECT c.name,
       udf_average_alumni_grade_by_course_name('Quantum Physics') as average_alumni_grade
FROM courses c
WHERE c.name = 'Quantum Physics';

-- P11: Graduate students

DELIMITER $$
CREATE PROCEDURE udp_graduate_all_students_by_year(IN year_started INT)
BEGIN
    UPDATE students
    SET is_graduated = 1
    WHERE id IN (SELECT DISTINCT sc.student_id
                 FROM students_courses sc
                          JOIN courses c ON c.id = sc.course_id
                 WHERE YEAR(c.start_date) = year_started);
END$$
DELIMITER ;

-- Test Result:
CALL udp_graduate_all_students_by_year(2017);