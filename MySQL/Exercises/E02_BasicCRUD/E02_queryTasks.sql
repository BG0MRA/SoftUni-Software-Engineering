-- P01.Find All Information About Departments 
SELECT * FROM departments
ORDER BY department_id ASC;

-- P02.Find all Department Names
SELECT `name` FROM departments
ORDER BY `department_id` ASC;

-- 03.Find Salary of Each Employee
SELECT first_name, last_name, salary
FROM employees
ORDER BY employee_id ASC;

-- P04.Find Full Name of Each Employee
SELECT first_name, middle_name, last_name
FROM employees
ORDER BY employee_id;

-- P05.Find Email Address of Each Employee
SELECT CONCAT(first_name, ".", last_name, "@softuni.bg") AS full_email_address
FROM employees;

-- P06.Find All Different Employee’s Salaries
SELECT DISTINCT Salary
FROM employees;

-- P07.Find all Information About Employees
SELECT * 
FROM employees
WHERE job_title = "Sales Representative"
ORDER BY employee_id;

-- P08.Find Names of All Employees by Salary in Range
SELECT first_name, last_name, job_title
FROM employees
WHERE salary BETWEEN 20000 AND 30000
ORDER BY employee_id;

-- P09.Find Names of All Employees
SELECT CONCAT_WS(" ", first_name, middle_name, last_name) AS "Full Name"
FROM employees
WHERE salary IN(25000, 14000, 12500,23600);

-- P10.Find All Employees Without Manager
SELECT first_name, last_name
FROM employees
WHERE manager_id IS NULL;

-- P11.Find All Employees with Salary More Than
SELECT first_name, last_name, salary
FROM employees
WHERE salary > 50000
ORDER BY salary DESC;

-- P12.Find 5 Best Paid Employees
SELECT first_name, last_name
FROM employees
ORDER BY salary DESC
LIMIT 5;

-- P13.Find All Employees Except Marketing
SELECT first_name, last_name
FROM employees
WHERE department_id != 4;

-- P14.Sort Employees Table
SELECT *
FROM employees
ORDER BY
salary DESC,
first_name ASC,
last_name DESC,
middle_name ASC;

-- P15.Create View Employees with Salaries
CREATE VIEW v_employees_salaries AS
SELECT first_name, last_name, salary
FROM employees;

-- P16.Create View Employees with Job Titles
CREATE VIEW v_employees_job_titles AS
SELECT CONCAT_WS(" ", first_name, middle_name, last_name) AS full_name, job_title
FROM employees;

-- P17.Distinct Job Titles
SELECT DISTINCT job_title
FROM employees
ORDER BY job_title ASC;

-- P18.Find First 10 Started Projects
SELECT *
FROM projects
ORDER BY start_date ASC,`name` ASC, project_id ASC
LIMIT 10;

-- P19.Last 7 Hired Employees
SELECT first_name, last_name, hire_date
FROM employees
ORDER BY hire_date DESC
LIMIT 7;

-- P20.Increase Salaries
UPDATE employees
SET salary = salary * 1.12
WHERE department_id IN(1, 2, 4, 11);
SELECT salary
FROM employees;

-- P21.All Mountain Peaks
USE geography;

SELECT peak_name
FROM peaks
ORDER BY peak_name ASC;

-- P22.Biggest Countries by Population
SELECT country_name, population
FROM countries
WHERE continent_code = "EU"
ORDER BY population DESC, country_name ASC
LIMIT 30;

-- P23.Countries and Currency (Euro / Not Euro)
SELECT country_name, country_code, IF(currency_code ='EUR', 'Euro', 'Not Euro') AS currency
FROM countries
WHERE currency_code IS NOT NULL
ORDER BY country_name;

-- P24.All Diablo Characters
SELECT `name`
FROM `characters`
ORDER BY `name`;