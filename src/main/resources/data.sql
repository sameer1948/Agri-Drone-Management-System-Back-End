INSERT INTO drone (drone_id, drone_name, drone_owner_name, drone_owner_phone) VALUES
('1','Phantom Pro 4', 'Alex Rivera', '+1-555-0192'),
('2','Mavic 3 Cine', 'Sarah Jenkins', '+1-555-0143'),
('5','SkyViper X10', 'Rahul Sharma', '+91-98765-43210'),
('3','Matrice 300 RTK', 'Elena Rostova', '+44-20-7946-0912'),
('4','Autel EVO Lite+', 'Marcus Chen', '+1-555-0188');



-- Initial Rate
INSERT INTO rate (price) VALUES (250.00);

-- Sample Drone Spraying Logs
INSERT INTO logs (booking_id, drone_id, create_date, create_time, crop_name, crop_owner_name, crop_owner_phone, crop_size, crop_village, tank_usage, amount, activity_status, payment_status) VALUES
    (100, '1', '2026-09-15', '08:30:00', 'Paddy', 'Ramesh Kumar', '+91-9876543210', 4.5, 'Kishanpur', 9.0, 2250.00, 'CONFIRMED','PAID'),
    (200, '1', '2026-09-16', '09:15:00', 'Cotton', 'Suresh Patel', '+91-9123456780', 2.0, 'Navagaon', 4.0, 1000.00, 'CONFIRMED','PENDING'),
    (300, '2', '2026-09-17', '16:45:00', 'Sugarcane', 'Anil Verma', '+91-9988776655', 6.0, 'Rampur', 12.0, 3000.00, 'CONFIRMED','PAID');


INSERT INTO booking (booking_id, booking_date, booking_day, crop_name, crop_owner_name, crop_owner_phone, crop_size, crop_village, price, amount, booking_notes, booking_status) VALUES
    (100, '2026-09-21', 'Monday', 'Paddy', 'Ramesh Kumar', '+91-9876543210', 4.50, 'Kishanpur', 250.00, 2250.00,'','PENDING'),
    (200, '2026-09-22', 'Tuesday', 'Cotton', 'Suresh Patel', '+91-9123456780', 2.00, 'Navagaon', 250.00, 1250.00,'','PENDING'),
    (300, '2026-09-23', 'Wednesday', 'Sugarcane', 'Anil Verma', '+91-9988776655', 6.00, 'Rampur', 250.00, 4500.00,'','CONFIRMED');