-- SECTION:01 Table Creation

-- CREATE database online_store;
-- USE online_store;


CREATE TABLE brands
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE categories
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE reviews
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    content      TEXT,
    rating       DECIMAL(10, 2) NOT NULL,
    picture_url  VARCHAR(80)    NOT NULL,
    published_at DATETIME       NOT NULL
);

CREATE TABLE products
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(40)    NOT NULL,
    price             DECIMAL(19, 2) NOT NULL,
    quantity_in_stock INT,
    description       TEXT,
    brand_id          INT            NOT NULL,
    category_id       INT            NOT NULL,
    review_id         INT,
    CONSTRAINT fk_products_brands
        FOREIGN KEY (brand_id)
            REFERENCES brands (id),
    CONSTRAINT fk_products_categories
        FOREIGN KEY (category_id)
            REFERENCES categories (id),
    CONSTRAINT fk_products_reviews
        FOREIGN KEY (review_id)
            REFERENCES reviews (id)
);

CREATE TABLE customers
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    first_name    VARCHAR(20) NOT NULL,
    last_name     VARCHAR(20) NOT NULL,
    phone         VARCHAR(30) NOT NULL UNIQUE,
    address       VARCHAR(60) NOT NULL,
    discount_card BIT(1)      NOT NULL DEFAULT 0
);

CREATE TABLE orders
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    order_datetime DATETIME NOT NULL,
    customer_id    INT      NOT NULL,
    CONSTRAINT fk_orders_customers
        FOREIGN KEY (customer_id)
            REFERENCES customers (id)
);

CREATE TABLE orders_products
(
    order_id   INT,
    product_id INT,
    CONSTRAINT fk_op_orders
        FOREIGN KEY (order_id)
            REFERENCES orders (id),
    CONSTRAINT fk_op_products
        FOREIGN KEY (product_id)
            REFERENCES products (id)
);

-- SECTION: 02 CRUD
-- P02 Insert

INSERT INTO reviews(content, picture_url, published_at, rating)
SELECT SUBSTRING(p.description, 1, 15), REVERSE(p.name), '2010-10-10', p.price / 8
FROM products as p
WHERE p.id >= 5;

-- P03 Update

UPDATE products
SET quantity_in_stock = quantity_in_stock - 5
WHERE quantity_in_stock BETWEEN 60 AND 70;

-- P04 Delete

DELETE c
FROM customers AS c
         LEFT JOIN orders o on c.id = o.customer_id
WHERE o.customer_id IS NULL;

-- SECTION: 03 Querying

-- P05 Categories

SELECT id, name
FROM categories
ORDER BY name DESC;

-- P06 Quantity

SELECT id, brand_id, name, quantity_in_stock
FROM products
WHERE price > 1000
  AND quantity_in_stock < 30
ORDER BY quantity_in_stock, id;

-- P07 Review

SELECT id, content, rating, picture_url, published_at
FROM reviews
WHERE content LIKE 'My%'
  AND LENGTH(content) > 61
ORDER BY rating DESC;

-- P08 First customers

SELECT CONCAT_WS(' ', c.first_name, c.last_name) AS full_name,
       c.address                                 AS address,
       o.order_datetime                          AS order_date
FROM customers AS c
         JOIN orders o on c.id = o.customer_id
WHERE YEAR(o.order_datetime) <= '2018'
ORDER BY full_name DESC;

-- P09 Best categories

SELECT COUNT(p.id)              AS items_count,
       c.name                   AS name,
       SUM(p.quantity_in_stock) AS total_quantity
FROM categories AS c
         JOIN products p on c.id = p.category_id
GROUP BY c.id, c.name
ORDER BY items_count DESC, total_quantity ASC
LIMIT 5;

-- Section: 04 Programmability
-- P10 Extract client cards count

CREATE FUNCTION udf_customer_products_count(name VARCHAR(30))
    RETURNS INT
BEGIN
    DECLARE total_products INT;

    SELECT COUNT(op.product_id)
    INTO total_products
    FROM customers AS c
             JOIN orders o on c.id = o.customer_id
             JOIN orders_products op on o.id = op.order_id
    WHERE c.first_name = name;

    RETURN total_products;

END;

-- P11.	Reduce price

CREATE PROCEDURE udp_reduce_price(IN category_name VARCHAR(50))
BEGIN
    UPDATE products AS p
        JOIN reviews r ON p.review_id = r.id
        JOIN categories c ON p.category_id = c.id
    SET p.price = p.price * 0.7
    WHERE r.rating < 4
      AND c.name = category_name;
END;