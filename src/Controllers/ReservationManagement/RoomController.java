// ── controller/RoomController.java ───────────────────────────────────────────
package Controllers.ReservationManagement;

import DAO.ReservationManagement.RoomDAO;
import Model.ReservationManagement.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RoomController {

    private final RoomDAO roomDAO = new RoomDAO();

    public String addRoom(String number, String type, String priceStr, String capacityStr, String desc) {
        if (number.isBlank() || type.isBlank() || priceStr.isBlank())
            return "ERROR: Room number, type, and price are required.";
        try {
            double price    = Double.parseDouble(priceStr);
            int    capacity = Integer.parseInt(capacityStr);
            if (price <= 0)    return "ERROR: Price must be positive.";
            if (capacity <= 0) return "ERROR: Capacity must be at least 1.";
            Room r = new Room(number.trim(), type, price, capacity, desc.trim());
            roomDAO.addRoom(r);
            return "SUCCESS: Room added successfully.";
        } catch (NumberFormatException e) {
            return "ERROR: Price and capacity must be valid numbers.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String updateRoomStatus(int roomId, String status) {
        try {
            roomDAO.updateRoomStatus(roomId, status);
            return "SUCCESS: Room status updated.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String deleteRoom(int id) {
        try {
            roomDAO.deleteRoom(id);
            return "SUCCESS: Room deleted.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public List<Room> getAllRooms() {
        try { return roomDAO.getAllRooms(); }
        catch (SQLException e) { e.printStackTrace(); return List.of(); }
    }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        try { return roomDAO.getAvailableRooms(checkIn, checkOut); }
        catch (SQLException e) { e.printStackTrace(); return List.of(); }
    }
}
