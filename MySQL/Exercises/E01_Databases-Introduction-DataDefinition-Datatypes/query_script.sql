CREATE DATABASE minions;
USE minions;

-- P01. Create Tables
CREATE TABLE minions (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(12),
    `age` INT
);  

CREATE TABLE towns (
    `town_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(12)
);

-- P02. Alter Minions Table 
ALTER TABLE minions
ADD COLUMN town_id int;

ALTER TABLE minions
ADD CONSTRAINT fk_minions_towns
FOREIGN KEY minions(town_id)
REFERENCES towns(id);

-- P03. Insert Records in Both Tables
INSERT INTO towns (id, `name`) VALUES
(1, 'Sofia'),
(2, 'Plovdiv'),
(3, 'Varna');

INSERT INTO minions (id, `name`, age, town_id) VALUES
(1, 'Kevin', 22, 1),
(2, 'Bob', 15, 3),
(3, 'Steward', null, 2);

SELECT * FROM minions;

-- P04. Truncate Table Minions
TRUNCATE TABLE `minions`;

-- P05. Drop All Tables --

DROP TABLE `minions`;

DROP TABLE `towns`;

-- P06. Create Table People

CREATE TABLE `people` (
	`id` INT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    `name` VARCHAR(200) NOT NULL,
    `picture` MEDIUMBLOB,
    `height` DECIMAL (5, 2) DEFAULT NULL,
    CHECK (`height` >= 0) ,
    `weight` DECIMAL (5, 2) DEFAULT NULL,
	CHECK (`weight` >= 0),
    `gender` ENUM ("m", "f") NOT NULL,
    `birthdate` DATE NOT NULL,
    `biography`TEXT
);

INSERT INTO `people` (`name`, `gender`, `birthdate`)
VALUES 
	("Pesho", "m", DATE(NOW())),
	("Maria", "f", DATE(NOW())),
    ("Ivan", "m", DATE(NOW())),
    ("Kaloyan", "m", DATE(NOW())),
    ("Gergana", "f", DATE(NOW()));
    
-- P07. Create Table Users

CREATE TABLE `users` (
	`id` INT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    `username` VARCHAR (50) NOT NULl,
    `password` VARCHAR (26) NOT NULL,
    `profile_picture` MEDIUMBLOB,
    `last_login_time` DATETIME,
    `is_deleted` BOOLEAN
);

INSERT INTO `users` (`username`, `password`)
VALUES
	("pesho", "pesho123*"),
    ("ivan", "ivanPass*"),
    ("gergana", "gerito46*"),
    ("maria", "mar1a*"),
    ("kaloyan", "ka10yan*");
    
-- Task 08: Change Primary Key 
    
ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_users`
PRIMARY KEY (`id`, `username`);

-- Task 09: Set Default Value of a Field

ALTER TABLE `users`
MODIFY COLUMN `last_login_time` DATETIME DEFAULT NOW();

-- Task 10: Set Unique Field 

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_users`
PRIMARY KEY (`id`),
MODIFY COLUMN `username` VARCHAR (50) NOT NULl UNIQUE;

-- Task 11: Movies Database 

CREATE DATABASE `movies`;

USE `movies`;

-- Submit in judge from here:
CREATE TABLE `directors` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `director_name` VARCHAR (50) NOT NULL,
    `notes` TEXT
    );
    
INSERT INTO `directors` (`director_name`, `notes`) 
VALUES
	("Francis Ford Coppola", "test notes"),
	("Peter Jackson", "test notes"),
    ("Quentin Tarantino", "test notes"),
    ("Christopher Nolan", "test notes"),
    ("Frank Darabont", "test notes");

CREATE TABLE `genres` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `genre_name` VARCHAR (20) NOT NULL,
    `notes` TEXT
);

INSERT INTO `genres` (`genre_name`, `notes`) 
VALUES
	("Commedy", "test notes"),
	("Drama", "test notes"),
    ("Action", "test notes"),
    ("Mistery", "test notes"),
    ("Sci-Fi", "test notes");
    
CREATE TABLE `categories` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `category_name` VARCHAR (20) NOT NULL,
    `notes` TEXT
);

INSERT INTO `categories` (`category_name`, `notes`) 
VALUES
	("Crime", "test notes"),
	("Parody", "test notes"),
    ("Legends", "test notes"),
    ("Detective", "test notes"),
    ("Adventure", "test notes");
    
CREATE TABLE `movies` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR (50) NOT NULL,
    `director_id` INT,
    `copyright_year` YEAR,
    `length` TIME,
    `genre_id` INT,
    `category_id` INT,
    `rating` DECIMAL(2,1),
    `notes` TEXT
	/*FOREIGN KEY (`director_id`) REFERENCES `directors`(`id`),
	FOREIGN KEY (`genre_id`) REFERENCES `genres`(`id`),
    FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`)*/
);

INSERT INTO `movies` (`title`, `director_id`, `copyright_year`, `length`, `genre_id`, `category_id`)
VALUES
("The Godfather", 1, 1972, '2:55:00', 2,  1),
("The Lord of the Rings: The Return of the King", 2, 2003, '3:21:00', 3,  5),
("Pulp Fiction", 3, 1994, '2:34:00', 2,  1),
("The Lord of the Rings: The Fellowship of the Ring", 2, 2001, '2:58:00', 3,  5),
("The Lord of the Rings: The Two Towers", 2, 2002, '2:59:00', 3,  5);


-- Task 12: Car Rental Database

CREATE DATABASE `car_rental`;

USE `car_rental`;

-- Submit in judge from here:
CREATE TABLE `categories` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `category` VARCHAR(50) NOT NULL,
    `daily_rate` DECIMAL (2,1),
    `weekly_rate` DECIMAL (2,1),
    `monthly_rate` DECIMAL (2,1),
    `weekend_rate` DECIMAL (2,1)
);

INSERT INTO `categories` (`category`)
VALUES ("Coupe"), ("Sedan"), ("Hatchback");

CREATE TABLE `cars` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `plate_number` VARCHAR(10),
    `make` VARCHAR(30),
    `model` VARCHAR(30),
    `car_year` YEAR,
    `category_id` INT,
    `doors` VARCHAR(10),
    `picture` BLOB,
    `car_condition` VARCHAR (30),
    `available` BOOLEAN
);

INSERT INTO `cars` (`plate_number`, `make`, `model`, `car_year`, `category_id`, `doors`, `car_condition`, `available`)
VALUES (NULL, "Mercedes",  "Benz SL 55 AMG 4Matic +", 2022, 1, "2(3)", "new", true),
(NULL, "Fiat",  "500 Hybrid", 2022, 3, "2(3)", "new", true),
(NULL, "Lexus",  "Es 350 ULTRA LUXURY", 2022, 2, "4(5)", "new", true);

CREATE TABLE `employees` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR (30) NOT NULL,
`last_name` VARCHAR (30) NOT NULL,
`title` VARCHAR (30) NOT NULL,
`notes` TEXT
);

INSERT INTO `employees` (`first_name`, `last_name`, `title`)
VALUES ("Georgi", "Georgiev", "saleman"),
("Peter", "Petrov", "cashier"),
("Ivan", "Ivanov", "director");

CREATE TABLE `customers` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `driver_licence_number` VARCHAR (30) NOT NULL,
    `full_name` VARCHAR (50) NOT NULL,
    `address` VARCHAR (100) NOT NULL,
    `city` VARCHAR (50) NOT NULL,
    `zip_code` INT NOT NULL,
    `notes` TEXT
);

INSERT INTO `customers` (`driver_licence_number`, `full_name`, `address`, `city`, `zip_code`, `notes`)
VALUES ("1236547", "Test Full Name", "Test Addres", "Sofia", 1000, "test notes"),
("1236547", "Test Full Name", "Test Addres", "Varna", 9000, "test notes"),
("1236547", "Test Full Name", "Test Addres", "Plovdiv", 4000, "test notes");

CREATE TABLE `rental_orders` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `employee_id` INT,
    `customer_id` INT,
    `car_id` INT,
    `car_condition` VARCHAR (50),
    `tank_level` DOUBLE,
    `kilometrage_start` INT, 
    `kilometrage_end` INT, 
    `total_kilometrage` INT, 
    `start_date` DATE, 
    `end_date` DATE, 
    `total_days` INT, 
    `rate_applied` DOUBLE, 
    `tax_rate` DOUBLE, 
    `order_status` VARCHAR (50), 
    `notes` TEXT
);

INSERT INTO `rental_orders` (`employee_id`, `customer_id`, `car_id`, `car_condition`, 
`tank_level`, `kilometrage_start`, `kilometrage_end`, `total_kilometrage`,
 `start_date`, `end_date`, `total_days`, `rate_applied`, `tax_rate`, `order_status`, `notes`)
 VALUES (1, 1, 1, "perfect", 100.00, 1000, 1400, 400, '2023-04-25', '2023-04-26', 1, 0.5, 0.20, "complited", "test"),
    (2, 2, 2, "perfect", 100.00, 1000, 1400, 400, '2023-04-25', '2023-04-26', 1, 0.5, 0.20, "complited", "test"),
    (3, 3, 3, "perfect", 100.00, 1000, 1400, 400, '2023-04-25', '2023-04-26', 1, 0.5, 0.20, "complited", "test");


-- P13 to P17
CREATE DATABASE `soft_uni`;

USE `soft_uni`;

CREATE TABLE `towns` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL 
);
    
CREATE TABLE `addresses` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `address_text` VARCHAR(100) NOT NULL,
    `town_id` INT,
    CONSTRAINT `fk_town_id` FOREIGN KEY (`town_id`) REFERENCES `towns`(`id`)
 );
    
CREATE TABLE `departments` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL 
 );
    
CREATE TABLE `employees` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL, 
    `middle_name` VARCHAR(50), 
    `last_name` VARCHAR(50) NOT NULL, 
    `job_title` VARCHAR(50) NOT NULL, 
    `department_id` INT NOT NULL, 
    `hire_date` DATE NOT NULL, 
    `salary` DECIMAL(6,2) NOT NULL, 
    `address_id` INT,
	CONSTRAINT `fk_department_id` FOREIGN KEY (`department_id`) REFERENCES `departments`(`id`),
    CONSTRAINT `fk_address_id` FOREIGN KEY (`address_id`) REFERENCES `addresses`(`id`)
);

-- Task 13: Basic Insert --

INSERT INTO `towns` (`name`) 
VALUES ("Sofia"), ("Plovdiv"), ("Varna"), ("Burgas");

INSERT INTO `departments` (`name`) 
VALUES ("Engineering"), ("Sales"), ("Marketing"), ("Software Development"), ("Quality Assurance");

INSERT INTO `employees` (`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`, `salary`)
VALUES ("Ivan", "Ivanov", "Ivanov", ".NET Developer", 4, '2013-02-01', 3500.00),
("Petar", "Petrov", "Petrov", "Senior Engineer", 1, '2004-03-02', 4000.00),
("Maria", "Petrova", "Ivanova", "Intern", 5, '2016-08-28', 525.25),
("Georgi", "Terziev", "Ivanov", "CEO", 2, '2007-12-09', 3000.00),
("Peter", "Pan", "Pan", "Intern", 3, '2016-08-28', 599.88);

-- Task 14: Basic Select All Fields 

SELECT * FROM `towns`;

SELECT * FROM `departments`;

SELECT * FROM `employees`;

-- Task 15: Basic Select All Fields and Order Them

SELECT * FROM `towns` ORDER BY `name`;

SELECT * FROM `departments` ORDER BY `name`;

SELECT * FROM `employees` ORDER BY `salary` DESC, `job_title`, `last_name`, `first_name`;


-- Task 16: Basic Select Some Fields

SELECT `name` FROM `towns` ORDER BY `name`;

SELECT `name` FROM `departments` ORDER BY `name`;

SELECT `first_name`, `last_name`, `job_title`, `salary` 
FROM `employees` 
ORDER BY `salary` DESC, `job_title`, `last_name`, `first_name`;


-- Task 17: Increase Employees Salary 

UPDATE `employees`
SET `salary` = `salary` * 1.1;

SELECT `salary` FROM `employees`;
