CREATE DATABASE IF NOT EXISTS HotelReservationSystem;
USE HotelReservationSystem;

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
    is_occupied BOOLEAN DEFAULT FALSE,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- 3. Customer Table
CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(155) NOT NULL,
    last_name VARCHAR(155) NOT NULL,
    phone_number VARCHAR(155),
    email VARCHAR(155) NOT NULL UNIQUE,
    status ENUM('Active', 'Inactive') DEFAULT 'ACTIVE',
    is_deleted BOOLEAN DEFAULT FALSE
);

-- 4. Reservations Table
CREATE TABLE Reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    service_id INT,
    reservation_date DATE DEFAULT (CURRENT_DATE),
    start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_time TIMESTAMP NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status ENUM('Pending', 'Confirmed', 'Cancelled', 'Completed') DEFAULT 'Pending',
    is_deleted BOOLEAN DEFAULT FALSE,

    -- Use RESTRICT to ensure data integrity in a soft-delete system
    CONSTRAINT FK_Res_Customer FOREIGN KEY (customer_id) 
        REFERENCES Customer(customer_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_Res_Service FOREIGN KEY (service_id) 
        REFERENCES Services(service_id) ON UPDATE CASCADE ON DELETE RESTRICT
);
