CREATE DATABASE IF NOT EXISTS ss12;
USE ss12;

DROP TABLE IF EXISTS Doctors;

CREATE TABLE Doctors (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    doctor_code VARCHAR(10) UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    specialty VARCHAR(50) NOT NULL,
    experience_years INT NOT NULL,
    base_salary DOUBLE NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO Doctors (doctor_code, full_name, specialty, experience_years, base_salary, password) VALUES
('D001','BS. Nguyễn Văn A', 'Nội khoa', 10, 20000000, '123'),
('D002','BS. Lê Thị Bình', 'Nhi khoa', 5, 15000000, '123'),
('D003','BS. Trần Văn C', 'Ngoại khoa', 8, 18000000, '123'),
('D004','BS. Phạm Minh Anh', 'Nhi khoa', 3, 12000000, '123');

DROP PROCEDURE IF EXISTS GET_SURGERY_FEE;

DELIMITER //

CREATE PROCEDURE GET_SURGERY_FEE(
    IN surgery_id INT,
    OUT total_cost DECIMAL(10,2)
)
BEGIN
    DECLARE cost DECIMAL(10,2) DEFAULT 0;

    SET cost = surgery_id * 1000;

    SET total_cost = cost;
END //

DELIMITER ;

DROP PROCEDURE IF EXISTS calculate_duty_fee;

DELIMITER //

CREATE PROCEDURE calculate_duty_fee(
    IN doctorId INT,
    OUT dutyFee DOUBLE
)
BEGIN
    DECLARE salary DOUBLE DEFAULT 0;

    SELECT base_salary INTO salary
    FROM Doctors
    WHERE doctor_id = doctorId
    LIMIT 1;

    IF salary IS NULL THEN
        SET dutyFee = 0;
    ELSE
        SET dutyFee = salary * 0.1;
    END IF;

END //

DELIMITER ;

DROP TABLE IF EXISTS Vitals;

CREATE TABLE Vitals (
    patient_id INT PRIMARY KEY,
    temperature DOUBLE,
    heart_rate INT
);

INSERT INTO Vitals (patient_id, temperature, heart_rate)
VALUES (1, 36.5, 75);


DROP TABLE IF EXISTS Results;

CREATE TABLE Results (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data VARCHAR(255)
);