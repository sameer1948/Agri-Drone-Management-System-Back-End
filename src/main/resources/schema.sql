CREATE TABLE IF NOT EXISTS drone (
    droneid VARCHAR(50)  PRIMARY KEY,
    dronename VARCHAR(50) NOT NULL,
    droneownername VARCHAR(100) NOT NULL,
    droneownerphone VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS rate (
    price DECIMAL(10, 2) NOT NULL
    );

CREATE TABLE IF NOT EXISTS logs (
    serial_no BIGINT AUTO_INCREMENT PRIMARY KEY,
    log_date DATE NOT NULL,
    log_time TIME NOT NULL,
    crop_name VARCHAR(100) NOT NULL,
    crop_owner_name VARCHAR(100) NOT NULL,
    crop_owner_phone VARCHAR(20) NOT NULL,
    crop_size DOUBLE NOT NULL,
    crop_village VARCHAR(100) NOT NULL,
    tank_usage DOUBLE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_status VARCHAR(20) NOT NULL CHECK (payment_status IN ('PAID', 'PENDING'))
    );

