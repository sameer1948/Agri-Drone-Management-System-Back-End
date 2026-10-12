CREATE TABLE IF NOT EXISTS drone (
    drone_id VARCHAR(50)  PRIMARY KEY,
    drone_name VARCHAR(50) NOT NULL,
    drone_owner_name VARCHAR(100) NOT NULL,
    drone_owner_phone VARCHAR(20) NOT NULL,
    drone_status VARCHAR(20) NOT NULL CHECK (drone_status IN ('ACTIVE', 'INACTIVE'))
    );

CREATE TABLE IF NOT EXISTS rate (
    rate_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    price DECIMAL(10, 2) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    effective_from TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    effective_to TIMESTAMP NULL DEFAULT NULL,
    change_reason VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS village (
    village_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS crop (
    crop_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS booking (
    booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_date DATE NOT NULL,
    booking_day VARCHAR(15) NOT NULL,
    crop_name VARCHAR(100) NOT NULL,
    crop_owner_name VARCHAR(100) NOT NULL,
    crop_owner_phone VARCHAR(20) NOT NULL,
    crop_size DECIMAL(10, 2) NOT NULL,
    crop_village VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    booking_notes VARCHAR(1000) NULL,
    booking_status VARCHAR(20) NOT NULL CHECK (booking_status IN ('CONFIRMED', 'CANCELLED', 'PENDING'))
    );

CREATE TABLE IF NOT EXISTS logs (
    log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    drone_id VARCHAR(50) NOT NULL,
    create_date DATE NOT NULL,
    create_time TIME NOT NULL,
    crop_name VARCHAR(100) NOT NULL,
    crop_owner_name VARCHAR(100) NOT NULL,
    crop_owner_phone VARCHAR(20) NOT NULL,
    crop_size DOUBLE NOT NULL,
    crop_village VARCHAR(100) NOT NULL,
    tank_usage DOUBLE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    complete_date DATE,
    complete_time TIME,
    activity_status VARCHAR(20) NOT NULL CHECK (activity_status IN ('CONFIRMED', 'COMPLETED', 'CANCELLED')),
    payment_status VARCHAR(20) NOT NULL CHECK (payment_status IN ('PAID', 'PENDING'))
    );

CREATE TABLE IF NOT EXISTS payments (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    log_id BIGINT UNIQUE NOT NULL,
    payment_status VARCHAR(20) NOT NULL CHECK (payment_status IN ('PAID', 'PENDING')),
    total_amount DECIMAL(10, 2) NOT NULL,
    paid_amount DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    pending_amount DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    payment_date DATE,
    payment_time TIME,
    last_payment_date DATE,
    last_payment_time TIME,
    payment_type VARCHAR(50),
    payment_notes VARCHAR(10000)
    --CONSTRAINT fk_payments_log FOREIGN KEY (log_id) REFERENCES logs(log_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS repairs (
    repair_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    repair_note VARCHAR(10000) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    repair_date DATE NOT NULL,
    repair_time TIME NOT NULL,
    location VARCHAR(150) NOT NULL,
    booking_id BIGINT
    --CONSTRAINT fk_repairs_booking FOREIGN KEY (booking_id)  REFERENCES booking(booking_id) ON DELETE SET NULL
    );