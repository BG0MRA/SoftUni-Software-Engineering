-- P01. Find Names of All Employees by First Name

SELECT first_name, last_name
FROM employees
WHERE LOWER(first_name) LIKE 'Sa%'
GROUP BY employee_id;

-- P02. Find Names of All Employees by Last Name

SELECT first_name, last_name
FROM employees
WHERE LOWER(last_name) LIKE '%ei%'
ORDER BY employee_id;

-- P03. Find First Names of All Employess

SELECT first_name
FROM employees
WHERE department_id IN (3,10) 
AND YEAR(hire_date) BETWEEN 1995 AND 2005
ORDER BY employee_id;

-- P04. Find All Employees Except Engineers

SELECT first_name, last_name
FROM employees
WHERE job_title NOT LIKE '%engineer%'
ORDER BY employee_id;

-- P05. Find Towns with Name Length

SELECT `name`
FROM towns
WHERE LENGTH(`name`) IN (5,6)
ORDER BY `name`;

-- P06. Find Towns Starting With

SELECT `town_id`, `name`
FROM towns
WHERE `name` LIKE 'M%' OR `name` LIKE 'K%' OR `name` LIKE 'B%' OR `name` LIKE 'E%'
ORDER BY `name`;

-- P07. Find Towns Not Starting With

SELECT `town_id`, `name`
FROM towns
WHERE `name` NOT LIKE ('R%') AND `name` NOT LIKE ('B%') AND `name` NOT LIKE ('D%')
ORDER BY `name`;

-- P08. Create View Employees Hired After

CREATE VIEW v_employees_hired_after_2000 AS
SELECT first_name, last_name
FROM employees
WHERE YEAR(hire_date) > 2000;

-- P09. Length of Last Name

SELECT first_name, last_name
FROM employees
WHERE LENGTH(last_name) = 5;

-- P10. Countries Holding 'A'

SELECT country_name, iso_code
FROM countries
WHERE LENGTH(country_name) - LENGTH(REPLACE(LOWER(country_name), 'a', '')) >= 3
ORDER BY iso_code;

-- P11. Mix of Peak and River Names

SELECT p.peak_name, r.river_name, LOWER(CONCAT((p.peak_name), SUBSTRING(r.river_name, 2))) AS mix
FROM peaks AS p, rivers AS r
WHERE LOWER(SUBSTRING(p.peak_name, -1)) = LOWER(SUBSTRING(r.river_name, 1, 1))
ORDER BY mix;

-- P12. Games From 2011 and 2012 Year

SELECT `name`, DATE_FORMAT(`start`, '%Y-%m-%d') AS 'start'
FROM `games`
WHERE YEAR(`start`) >= 2011 AND YEAR(`start`) <= 2012
ORDER BY `start`, `name`
LIMIT 50;

-- P13. User Email Providers

SELECT user_name,
  LOWER(REGEXP_REPLACE(email, '.*@', '')) AS email_provider
FROM users
ORDER BY email_provider ASC, user_name ASC;

-- P14. Get Users with IP Address Like Pattern

SELECT `user_name`, `ip_address`
FROM `users`
WHERE `ip_address` LIKE '___.1%.%.___'
ORDER BY `user_name`;

-- P15. Show All Games with Duration

SELECT `name` AS 'game',
	IF(HOUR(`start`) BETWEEN 0 AND 11, 'Morning', 
		IF(HOUR(`start`) BETWEEN 12 AND 17, 'Afternoon', 'Evening'))
        AS 'Part of the Day',
	IF(`duration`<= 3, 'Extra Short',
		IF(`duration` > 3 AND `duration` <= 6, 'Short',
        IF(`duration` > 6 AND `duration` <= 10, 'Long', 'Extra Long'))) 
        AS 'duration'
FROM `games`;

-- P16. Orders Table

SELECT
	`product_name`,
    `order_date`,
    ADDDATE(`order_date`, INTERVAL 3 DAY) AS `pay_due`,
    ADDDATE(`order_date`, INTERVAL 1 MONTH) AS `deliver_due`
FROM `orders`;
