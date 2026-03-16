// ── controller/ReservationController.java ────────────────────────────────────
package Controllers;

import DAO.ReservationDAO;
import Model.Guests;
import Model.Reservation;
import Model.Room;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationController {
 
    private final ReservationDAO reservationDAO = new ReservationDAO();
 
    // ── Make reservation ──────────────────────────────────────────────────────
    public String makeReservation(Guests guest, Room room,
                                  LocalDate checkIn, LocalDate checkOut,
                                  String notes) {
        if (guest    == null) return "ERROR: Please select a guest.";
        if (room     == null) return "ERROR: Please select a room.";
        if (checkIn  == null) return "ERROR: Please select a check-in date.";
        if (checkOut == null) return "ERROR: Please select a check-out date.";
        if (!checkOut.isAfter(checkIn))
            return "ERROR: Check-out must be after check-in.";
        if (checkIn.isBefore(LocalDate.now()))
            return "ERROR: Check-in date cannot be in the past.";
 
        try {
            if (!reservationDAO.isRoomAvailable(room.getRoomId(), checkIn, checkOut))
                return "ERROR: Room " + room.getRoomNumber() +
                       " is not available for the selected dates.";
 
            Reservation r = new Reservation(guest, room, checkIn, checkOut);
            r.setNotes(notes);
            r.setTotalAmount(r.calculateTotal());
            reservationDAO.addReservation(r);
 
            return String.format(
                "SUCCESS: Reservation confirmed! Total: ₱%.2f for %d night(s).",
                r.getTotalAmount(), r.getNights());
 
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }
 
    // ── Check in ──────────────────────────────────────────────────────────────
    public String checkIn(int reservationId) {
        try {
            Reservation r = reservationDAO.getReservationById(reservationId);
            if (r == null) return "ERROR: Reservation not found.";
            if (!"CONFIRMED".equals(r.getStatus()))
                return "ERROR: Only CONFIRMED reservations can be checked in.";
            reservationDAO.updateStatus(reservationId, "CHECKED_IN");
            return "SUCCESS: Guest checked in.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }
 
    // ── Check out ─────────────────────────────────────────────────────────────
    public String checkOut(int reservationId) {
        try {
            Reservation r = reservationDAO.getReservationById(reservationId);
            if (r == null) return "ERROR: Reservation not found.";
            if (!"CHECKED_IN".equals(r.getStatus()))
                return "ERROR: Only CHECKED_IN reservations can be checked out.";
            reservationDAO.updateStatus(reservationId, "CHECKED_OUT");
            return "SUCCESS: Guest checked out. Thank you!";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }
 
    // ── Cancel ────────────────────────────────────────────────────────────────
    public String cancelReservation(int reservationId) {
        try {
            Reservation r = reservationDAO.getReservationById(reservationId);
            if (r == null) return "ERROR: Reservation not found.";
            if ("CHECKED_OUT".equals(r.getStatus()) || "CANCELLED".equals(r.getStatus()))
                return "ERROR: This reservation cannot be cancelled.";
            reservationDAO.updateStatus(reservationId, "CANCELLED");
            return "SUCCESS: Reservation cancelled.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }
 
    // ── Queries ───────────────────────────────────────────────────────────────
    public List<Reservation> getAllReservations() {
        try   { return reservationDAO.getAllReservations(); }
        catch (SQLException e) { e.printStackTrace(); return new ArrayList<>(); }
    }
 
    public List<Reservation> getReservationsByGuest(int guestId) {
        try   { return reservationDAO.getReservationsByGuest(guestId); }
        catch (SQLException e) { e.printStackTrace(); return new ArrayList<>(); }
    }
 
    public List<Reservation> getByStatus(String status) {
        try   { return reservationDAO.getReservationsByStatus(status); }
        catch (SQLException e) { e.printStackTrace(); return new ArrayList<>(); }
    }
}
