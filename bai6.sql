CREATE DATABASE pharmacy_db;
USE pharmacy_db;

-- Bảng thuốc
CREATE TABLE medicines (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    price DOUBLE,
    stock INT
);

-- Bảng đơn thuốc
CREATE TABLE prescriptions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    medicine_id INT,
    quantity_sold INT,
    sale_date DATE,
    FOREIGN KEY (medicine_id) REFERENCES medicines(id)
);


DELIMITER //

CREATE PROCEDURE CalculatePrescriptionTotal(
    IN p_id INT,
    OUT p_total DECIMAL(18,2)
)
BEGIN
    SELECT m.price * p.quantity_sold
    INTO p_total
    FROM prescriptions p
    JOIN medicines m ON p.medicine_id = m.id
    WHERE p.id = p_id;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE GetDailyRevenue(
    IN p_date DATE,
    OUT p_revenue DECIMAL(18,2)
)
BEGIN
    SELECT SUM(m.price * p.quantity_sold)
    INTO p_revenue
    FROM prescriptions p
    JOIN medicines m ON p.medicine_id = m.id
    WHERE p.sale_date = p_date;
END //

DELIMITER ;