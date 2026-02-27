CREATE DATABASE IF NOT EXISTS HotelReservationSystem;
USE HotelReservationSystem;

-- 1. Users Table
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(155) NOT NULL,
    last_name VARCHAR(155) NOT NULL,
    password VARCHAR(155) NOT NULL,
    email VARCHAR(155) NOT NULL UNIQUE,
    role ENUM('Admin', 'Staff', 'Manager') DEFAULT 'Staff',
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

-- 3. Customer Table
CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(155) NOT NULL,
    last_name VARCHAR(155) NOT NULL,
    phone_number VARCHAR(155),
    email VARCHAR(155) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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

---

### Automated Soft-Delete Triggers

These triggers ensure that if you "delete" a customer or service by setting `is_deleted = TRUE`, the corresponding reservations are automatically updated to reflect that the link is now hidden or cancelled.



#### Trigger: Handle Customer Soft-Delete
This trigger monitors the `Customer` table. If a customer is marked as deleted, it sets the `customer_id` in their reservations to `NULL` (so you keep the financial data but lose the personal link) and marks the reservation as deleted too.

```sql
DELIMITER //

CREATE TRIGGER after_customer_soft_delete
AFTER UPDATE ON Customer
FOR EACH ROW
BEGIN
    -- If the is_deleted flag was changed from FALSE to TRUE
    IF OLD.is_deleted = FALSE AND NEW.is_deleted = TRUE THEN
        UPDATE Reservations 
        SET customer_id = NULL, 
            is_deleted = TRUE,
            status = 'Cancelled'
        WHERE customer_id = NEW.customer_id;
    END IF;
END; //

DELIMITER ;

DELIMITER //

CREATE TRIGGER after_service_soft_delete
AFTER UPDATE ON Services
FOR EACH ROW
BEGIN
    IF OLD.is_deleted = FALSE AND NEW.is_deleted = TRUE THEN
        UPDATE Reservations 
        SET service_id = NULL,
            status = 'Cancelled'
        WHERE service_id = NEW.service_id AND status = 'Pending';
    END IF;
END; //

DELIMITER ;