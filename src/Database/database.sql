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
    is_occupied BOOLEAN DEFAULT FALSE,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- 3. Customer Table
CREATE TABLE guests      (
    guest_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(155) NOT NULL,
    last_name VARCHAR(155) NOT NULL,
    phone VARCHAR(155),
    email VARCHAR(155) NOT NULL UNIQUE,
    address VARCHAR(155) DEFAULT NULL,
    status ENUM('Active', 'Inactive') DEFAULT 'Active',
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at timestamp not null default current_timestamp()
);

CREATE TABLE rooms (
  room_id int NOT NULL primary key AUTO_INCREMENT,
  room_number varchar(10) NOT NULL,
  room_type enum('SINGLE','DOUBLE','SUITE','DELUXE') NOT NULL,
  price decimal(10,2) NOT NULL,
  capacity int NOT NULL DEFAULT 1,
  status enum('AVAILABLE','OCCUPIED','MAINTENANCE') DEFAULT 'AVAILABLE',
  description varchar(300) DEFAULT NULL
);

CREATE TABLE reservations (
  reservation_id int NOT NULL primary key AUTO_INCREMENT,
  guest_id int NOT NULL,
  room_id int NOT NULL,
  check_in date NOT NULL,
  check_out date NOT NULL,
  status enum('CONFIRMED','CHECKED_IN','CHECKED_OUT','CANCELLED') DEFAULT 'CONFIRMED',
  total_amount decimal(10,2) DEFAULT NULL,
  notes text DEFAULT NULL,
  created_at timestamp NOT NULL DEFAULT current_timestamp(),
  FOREIGN KEY (guest_id) REFERENCES guests(guest_id),
  FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

