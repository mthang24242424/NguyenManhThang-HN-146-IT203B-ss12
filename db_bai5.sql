CREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;

DROP TABLE IF EXISTS Patients;

CREATE TABLE Patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_code VARCHAR(10) UNIQUE,
    full_name VARCHAR(100),
    age INT,
    department VARCHAR(50),
    disease VARCHAR(255),
    admission_days INT
);

INSERT INTO Patients (patient_code, full_name, age, department, disease, admission_days)
VALUES
('P001','Nguyễn Văn A',25,'ICU','Sốt cao',3),
('P002','L''Oréal',30,'ICU','Tim mạch',5),
('P003','D''Arcy',40,'ICU','Hô hấp',7);

DROP PROCEDURE IF EXISTS CALCULATE_DISCHARGE_FEE;

DELIMITER //

CREATE PROCEDURE CALCULATE_DISCHARGE_FEE(
    IN p_id INT,
    OUT total_fee DOUBLE
)
BEGIN
    DECLARE days INT DEFAULT 0;

    SELECT admission_days INTO days
    FROM Patients
    WHERE patient_id = p_id;

    IF days IS NULL THEN
        SET total_fee = 0;
    ELSE
        SET total_fee = days * 500000; -- 500k/ngày
    END IF;

END //

DELIMITER ;