DROP DATABASE IF EXISTS hoteldb;
CREATE DATABASE IF NOT EXISTS hoteldb;
USE hoteldb;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 16, 2026 at 08:58 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hoteldb`
--

-- --------------------------------------------------------

--
-- Table structure for table `audit_logs`
--
DROP DATABASE IF EXISTS hoteldb;
CREATE DATABASE IF NOT EXISTS hoteldb;
USE hoteldb;
CREATE TABLE `audit_logs` (
  `log_id` int(11) NOT NULL,
  `table_name` varchar(100) NOT NULL,
  `record_id` int(11) NOT NULL,
  `action` enum('INSERT','UPDATE','DELETE') NOT NULL,
  `changed_by` int(11) DEFAULT NULL,
  `changed_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `old_values` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`old_values`)),
  `new_values` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`new_values`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `audit_logs`
--



-- --------------------------------------------------------

--
-- Table structure for table `guests`
--

CREATE TABLE `guests` (
  `guest_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(155) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_deleted` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `guests`
--


--
-- Triggers `guests`
--
DELIMITER $$
CREATE TRIGGER `trg_guests_delete` AFTER DELETE ON `guests` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values)
    VALUES (
        'Guests',
        OLD.guest_id,
        'DELETE',
        @current_user_id,
        JSON_OBJECT(
            'guest_id',   OLD.guest_id,
            'first_name', OLD.first_name,
            'last_name',  OLD.last_name,
            'email',      OLD.email,
            'phone',      OLD.phone,
            'address',    OLD.address,
            'is_deleted', OLD.is_deleted
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_guests_insert` AFTER INSERT ON `guests` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, new_values)
    VALUES (
        'Guests',
        NEW.guest_id,
        'INSERT',
        @current_user_id,
        JSON_OBJECT(
            'guest_id',   NEW.guest_id,
            'first_name', NEW.first_name,
            'last_name',  NEW.last_name,
            'email',      NEW.email,
            'phone',      NEW.phone,
            'address',    NEW.address,
            'is_deleted', NEW.is_deleted
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_guests_update` AFTER UPDATE ON `guests` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values, new_values)
    VALUES (
        'Guests',
        NEW.guest_id,
        'UPDATE',
        @current_user_id,
        JSON_OBJECT(
            'guest_id',   OLD.guest_id,
            'first_name', OLD.first_name,
            'last_name',  OLD.last_name,
            'email',      OLD.email,
            'phone',      OLD.phone,
            'address',    OLD.address,
            'is_deleted', OLD.is_deleted
        ),
        JSON_OBJECT(
            'guest_id',   NEW.guest_id,
            'first_name', NEW.first_name,
            'last_name',  NEW.last_name,
            'email',      NEW.email,
            'phone',      NEW.phone,
            'address',    NEW.address,
            'is_deleted', NEW.is_deleted
        )
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` int(11) NOT NULL,
  `guest_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `reservation_date` DATE DEFAULT (CURRENT_DATE),
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `status` enum('CONFIRMED','CHECKED_IN','CHECKED_OUT','CANCELLED') DEFAULT 'CONFIRMED',
  `notes` text DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `reservations`
--
DELIMITER $$
CREATE TRIGGER `trg_reservations_delete` AFTER DELETE ON `reservations` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values)
    VALUES (
        'Reservations',
        OLD.reservation_id,
        'DELETE',
        @current_user_id,
        JSON_OBJECT(
            'reservation_id',   OLD.reservation_id,
            'guest_id',         OLD.guest_id,
            'room_id',          OLD.room_id,
            'check_in',         OLD.check_in,
            'check_out',        OLD.check_out,
            'total_amount',     OLD.total_amount,
            'status',           OLD.status,
            'is_deleted',       OLD.is_deleted
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_reservations_insert` AFTER INSERT ON `reservations` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, new_values)
    VALUES (
        'Reservations',
        NEW.reservation_id,
        'INSERT',
        @current_user_id,
        JSON_OBJECT(
            'reservation_id',   NEW.reservation_id,
            'guest_id',         NEW.guest_id,
            'room_id',          NEW.room_id,
            'check_in',         NEW.check_in,
            'check_out',        NEW.check_out,
            'total_amount',     NEW.total_amount,
            'status',           NEW.status,
            'is_deleted',       NEW.is_deleted
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_reservations_update` AFTER UPDATE ON `reservations` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values, new_values)
    VALUES (
        'Reservations',
        NEW.reservation_id,
        'UPDATE',
        @current_user_id,
        JSON_OBJECT(
            'reservation_id',   OLD.reservation_id,
            'guest_id',         OLD.guest_id,
            'room_id',          OLD.room_id,
            'check_in',         OLD.check_in,
            'check_out',        OLD.check_out,
            'total_amount',     OLD.total_amount,
            'status',           OLD.status,
            'is_deleted',       OLD.is_deleted
        ),
        JSON_OBJECT(
            'reservation_id',   NEW.reservation_id,
            'guest_id',         NEW.guest_id,
            'room_id',          NEW.room_id,
            'check_in',         NEW.check_in,
            'check_out',        NEW.check_out,
            'total_amount',     NEW.total_amount,
            'status',           NEW.status,
            'is_deleted',       NEW.is_deleted
        )
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `room_id` int(11) NOT NULL,
  `room_number` varchar(20) NOT NULL,
  `room_type` enum('SINGLE','DOUBLE','SUITE','DELUXE') NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `capacity` int(11) NOT NULL DEFAULT 1,
  `status` enum('AVAILABLE','OCCUPIED','MAINTENANCE') DEFAULT 'AVAILABLE',
  `description` varchar(300) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `rooms`
--
DELIMITER $$
CREATE TRIGGER `trg_rooms_delete` AFTER DELETE ON `rooms` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values)
    VALUES (
        'Rooms',
        OLD.room_id,
        'DELETE',
        @current_user_id,
        JSON_OBJECT(
            'room_id',     OLD.room_id,
            'room_number', OLD.room_number,
            'room_type',   OLD.room_type,
            'price',       OLD.price,
            'capacity',    OLD.capacity,
            'status',      OLD.status,
            'is_deleted',  OLD.is_deleted
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_rooms_insert` AFTER INSERT ON `rooms` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, new_values)
    VALUES (
        'Rooms',
        NEW.room_id,
        'INSERT',
        @current_user_id,
        JSON_OBJECT(
            'room_id',     NEW.room_id,
            'room_number', NEW.room_number,
            'room_type',   NEW.room_type,
            'price',       NEW.price,
            'capacity',    NEW.capacity,
            'status',      NEW.status,
            'is_deleted',  NEW.is_deleted
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_rooms_update` AFTER UPDATE ON `rooms` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values, new_values)
    VALUES (
        'Rooms',
        NEW.room_id,
        'UPDATE',
        @current_user_id,
        JSON_OBJECT(
            'room_id',     OLD.room_id,
            'room_number', OLD.room_number,
            'room_type',   OLD.room_type,
            'price',       OLD.price,
            'capacity',    OLD.capacity,
            'status',      OLD.status,
            'is_deleted',  OLD.is_deleted
        ),
        JSON_OBJECT(
            'room_id',     NEW.room_id,
            'room_number', NEW.room_number,
            'room_type',   NEW.room_type,
            'price',       NEW.price,
            'capacity',    NEW.capacity,
            'status',      NEW.status,
            'is_deleted',  NEW.is_deleted
        )
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `service_id` int(11) NOT NULL,
  `service_name` varchar(155) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `duration_minutes` int(11) DEFAULT NULL,
  `status` enum('Active','Inactive','Maintenance') DEFAULT 'Active',
  `is_deleted` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `services`
--
DELIMITER $$
CREATE TRIGGER `trg_services_delete` AFTER DELETE ON `services` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values)
    VALUES (
        'Services',
        OLD.service_id,
        'DELETE',
        @current_user_id,
        JSON_OBJECT(
            'service_id',       OLD.service_id,
            'service_name',     OLD.service_name,
            'price',            OLD.price,
            'duration_minutes', OLD.duration_minutes,
            'status',           OLD.status,
            'is_deleted',       OLD.is_deleted
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_services_insert` AFTER INSERT ON `services` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, new_values)
    VALUES (
        'Services',
        NEW.service_id,
        'INSERT',
        @current_user_id,
        JSON_OBJECT(
            'service_id',       NEW.service_id,
            'service_name',     NEW.service_name,
            'price',            NEW.price,
            'duration_minutes', NEW.duration_minutes,
            'status',           NEW.status,
            'is_deleted',       NEW.is_deleted
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_services_update` AFTER UPDATE ON `services` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values, new_values)
    VALUES (
        'Services',
        NEW.service_id,
        'UPDATE',
        @current_user_id,
        JSON_OBJECT(
            'service_id',       OLD.service_id,
            'service_name',     OLD.service_name,
            'price',            OLD.price,
            'duration_minutes', OLD.duration_minutes,
            'status',           OLD.status,
            'is_deleted',       OLD.is_deleted
        ),
        JSON_OBJECT(
            'service_id',       NEW.service_id,
            'service_name',     NEW.service_name,
            'price',            NEW.price,
            'duration_minutes', NEW.duration_minutes,
            'status',           NEW.status,
            'is_deleted',       NEW.is_deleted
        )
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `service_bookings`
--

CREATE TABLE `service_bookings` (
  `booking_id` int(11) NOT NULL,
  `guest_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `scheduled_at` datetime NOT NULL,
  `duration` int(11) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `status` enum('PENDING','CONFIRMED','COMPLETED','CANCELLED') DEFAULT 'PENDING',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `service_bookings`
--
DELIMITER $$
CREATE TRIGGER `trg_svc_bookings_delete` AFTER DELETE ON `service_bookings` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values)
    VALUES (
        'service_bookings',
        OLD.booking_id,
        'DELETE',
        @current_user_id,
        JSON_OBJECT(
            'booking_id',   OLD.booking_id,
            'guest_id',     OLD.guest_id,
            'service_id',   OLD.service_id,
            'scheduled_at', OLD.scheduled_at,
            'duration',     OLD.duration,
            'total',        OLD.total,
            'status',       OLD.status
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_svc_bookings_insert` AFTER INSERT ON `service_bookings` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, new_values)
    VALUES (
        'service_bookings',
        NEW.booking_id,
        'INSERT',
        @current_user_id,
        JSON_OBJECT(
            'booking_id',   NEW.booking_id,
            'guest_id',     NEW.guest_id,
            'service_id',   NEW.service_id,
            'scheduled_at', NEW.scheduled_at,
            'duration',     NEW.duration,
            'total',        NEW.total,
            'status',       NEW.status
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_svc_bookings_update` AFTER UPDATE ON `service_bookings` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values, new_values)
    VALUES (
        'service_bookings',
        NEW.booking_id,
        'UPDATE',
        @current_user_id,
        JSON_OBJECT(
            'booking_id',   OLD.booking_id,
            'guest_id',     OLD.guest_id,
            'service_id',   OLD.service_id,
            'scheduled_at', OLD.scheduled_at,
            'duration',     OLD.duration,
            'total',        OLD.total,
            'status',       OLD.status
        ),
        JSON_OBJECT(
            'booking_id',   NEW.booking_id,
            'guest_id',     NEW.guest_id,
            'service_id',   NEW.service_id,
            'scheduled_at', NEW.scheduled_at,
            'duration',     NEW.duration,
            'total',        NEW.total,
            'status',       NEW.status
        )
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(155) NOT NULL,
  `last_name` varchar(155) NOT NULL,
  `password` varchar(155) NOT NULL,
  `phone` varchar(155) NOT NULL,
  `email` varchar(155) NOT NULL,
  `role` enum('Admin','Staff') DEFAULT 'Staff',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_deleted` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

--
-- Triggers `users`
--
DELIMITER $$
CREATE TRIGGER `trg_users_delete` AFTER DELETE ON `users` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values)
    VALUES (
        'Users',
        OLD.user_id,
        'DELETE',
        @current_user_id,
        JSON_OBJECT(
            'user_id',    OLD.user_id,
            'first_name', OLD.first_name,
            'last_name',  OLD.last_name,
            'email',      OLD.email,
            'phone',      OLD.phone,
            'role',       OLD.role,
            'is_deleted', OLD.is_deleted
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_users_insert` AFTER INSERT ON `users` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, new_values)
    VALUES (
        'Users',
        NEW.user_id,
        'INSERT',
        @current_user_id,
        JSON_OBJECT(
            'user_id',    NEW.user_id,
            'first_name', NEW.first_name,
            'last_name',  NEW.last_name,
            'email',      NEW.email,
            'phone',      NEW.phone,
            'role',       NEW.role,
            'is_deleted', NEW.is_deleted
        )
    );
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_users_update` AFTER UPDATE ON `users` FOR EACH ROW BEGIN
    INSERT INTO audit_logs (table_name, record_id, action, changed_by, old_values, new_values)
    VALUES (
        'Users',
        NEW.user_id,
        'UPDATE',
        @current_user_id,
        JSON_OBJECT(
            'user_id',    OLD.user_id,
            'first_name', OLD.first_name,
            'last_name',  OLD.last_name,
            'email',      OLD.email,
            'phone',      OLD.phone,
            'role',       OLD.role,
            'is_deleted', OLD.is_deleted
        ),
        JSON_OBJECT(
            'user_id',    NEW.user_id,
            'first_name', NEW.first_name,
            'last_name',  NEW.last_name,
            'email',      NEW.email,
            'phone',      NEW.phone,
            'role',       NEW.role,
            'is_deleted', NEW.is_deleted
        )
    );
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `audit_logs`
--
ALTER TABLE `audit_logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `FK_Log_User` (`changed_by`);

--
-- Indexes for table `guests`
--
ALTER TABLE `guests`
  ADD PRIMARY KEY (`guest_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `FK_Res_Guests` (`guest_id`),
  ADD KEY `FK_Res_Rooms` (`room_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`room_id`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`service_id`);

--
-- Indexes for table `service_bookings`
--
ALTER TABLE `service_bookings`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `FK_Svc_Guest` (`guest_id`),
  ADD KEY `FK_Svc_Service` (`service_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `audit_logs`
--
ALTER TABLE `audit_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `guests`
--
ALTER TABLE `guests`
  MODIFY `guest_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `service_bookings`
--
ALTER TABLE `service_bookings`
  MODIFY `booking_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `audit_logs`
--
ALTER TABLE `audit_logs`
  ADD CONSTRAINT `FK_Log_User` FOREIGN KEY (`changed_by`) REFERENCES `users` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `FK_Res_Guests` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`guest_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Res_Rooms` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`) ON UPDATE CASCADE;

--
-- Constraints for table `service_bookings`
--
ALTER TABLE `service_bookings`
  ADD CONSTRAINT `FK_Svc_Guest` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`guest_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Svc_Service` FOREIGN KEY (`service_id`) REFERENCES `services` (`service_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
