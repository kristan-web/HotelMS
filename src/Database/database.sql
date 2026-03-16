DROP DATABASE IF EXISTS hoteldb;
CREATE DATABASE IF NOT EXISTS hoteldb;
USE hoteldb;

-- 1. Users Table
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(155) NOT NULL,
    last_name VARCHAR(155) NOT NULL,
    password VARCHAR(155) NOT NULL,
    phone VARCHAR(155) NOT NULL,
    email VARCHAR(155) NOT NULL UNIQUE,
    role ENUM('Admin', 'Staff') DEFAULT 'Staff',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- 2. Services Table
CREATE TABLE Services (
    service_id INT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(155) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_minutes INT, -- Changed to INT for better logic than the ERD's TIMESTAMP
    status ENUM('Active', 'Inactive', 'Maintenance') DEFAULT 'Active',
    is_deleted BOOLEAN DEFAULT FALSE
);

--- 3. Guest Table

CREATE TABLE `guests` (
  `guest_id` int(11) NOT NULL PRIMARY KEY,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(155) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) 



-- 4. Reservations Table
CREATE TABLE Reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_id INT,
    room_id int NOT NULL,
    reservation_date DATE DEFAULT (CURRENT_DATE),
    check_in date NOT NULL,
    check_out date NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status enum('CONFIRMED','CHECKED_IN','CHECKED_OUT','CANCELLED') DEFAULT 'CONFIRMED',
    notes text DEFAULT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at timestamp NOT NULL DEFAULT current_timestamp()

    -- Use RESTRICT to ensure data integrity in a soft-delete system
    CONSTRAINT FK_Res_Guests FOREIGN KEY (guest_id) 
        REFERENCES guests(guest_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_Res_Rooms FOREIGN KEY (room_id) 
        REFERENCES rooms(room_id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE `rooms` (
  `room_id` int NOT NULL,
  `room_number` varchar NOT NULL,
  `room_type` enum('SINGLE','DOUBLE','SUITE','DELUXE') NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `capacity` int NOT NULL DEFAULT 1,
  `status` enum('AVAILABLE','OCCUPIED','MAINTENANCE') DEFAULT 'AVAILABLE',
  `description` varchar(300) DEFAULT NULL,
  is_deleted BOOLEAN DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
