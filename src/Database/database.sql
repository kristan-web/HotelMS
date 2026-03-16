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
    role ENUM('Admin', 'Staaff') DEFAULT 'Staff',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- 2. Services Table
CREATE TABLE Services (
    service_id INT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(155) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_minutes INT,
    status ENUM('Active', 'Inactive', 'Maintenance') DEFAULT 'Active',
    is_deleted BOOLEAN DEFAULT FALSE
);

-- 3. Guests Table
CREATE TABLE Guests (
    guest_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(155) NOT NULL UNIQUE,
    phone VARCHAR(20) DEFAULT NULL,
    address VARCHAR(200) DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Rooms Table
CREATE TABLE Rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(20) NOT NULL, 
    room_type ENUM('SINGLE','DOUBLE','SUITE','DELUXE') NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    capacity INT NOT NULL DEFAULT 1,
    status ENUM('AVAILABLE','OCCUPIED','MAINTENANCE') DEFAULT 'AVAILABLE',
    description VARCHAR(300) DEFAULT NULL,
    is_deleted BOOLEAN DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 5. Reservations Table (Room Bookings)
CREATE TABLE Reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_id INT NOT NULL,
    room_id INT NOT NULL,
    reservation_date DATE DEFAULT (CURRENT_DATE),
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status ENUM('CONFIRMED','CHECKED_IN','CHECKED_OUT','CANCELLED') DEFAULT 'CONFIRMED',
    notes TEXT DEFAULT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT FK_Res_Guests FOREIGN KEY (guest_id) 
        REFERENCES Guests(guest_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_Res_Rooms FOREIGN KEY (room_id) 
        REFERENCES Rooms(room_id) ON UPDATE CASCADE ON DELETE RESTRICT
);

-- 6. Service Bookings Table (Spa, Gym, etc.)
CREATE TABLE service_bookings (
  booking_id INT AUTO_INCREMENT PRIMARY KEY,
  guest_id INT NOT NULL,
  service_id INT NOT NULL,
  scheduled_at DATETIME NOT NULL,
  duration INT NOT NULL, -- Duration in minutes
  total DECIMAL(10,2) NOT NULL,
  status ENUM('PENDING','CONFIRMED','COMPLETED','CANCELLED') DEFAULT 'PENDING',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),

  CONSTRAINT FK_Svc_Guest FOREIGN KEY (guest_id) 
    REFERENCES Guests(guest_id) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT FK_Svc_Service FOREIGN KEY (service_id) 
    REFERENCES Services(service_id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;