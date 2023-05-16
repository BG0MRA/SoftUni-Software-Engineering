-- P01.Find All Information About Departments 
SELECT * from departments
ORDER BY department_id ASC;

-- P02.Find all Department Names
SELECT `name` from departments
ORDER BY `department_id` ASC;

-- 03.Find Salary of Each Employee
SELECT first_name, last_name, salary
FROM employees
ORDER BY employee_id ASC;