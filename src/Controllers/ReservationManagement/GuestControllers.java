// ── controller/GuestController.java ──────────────────────────────────────────
package Controllers.ReservationManagement;

import Model.ReservationManagement.Guest;
import DAO.ReservationManagement.GuestDAO;

import java.sql.SQLException;
import java.util.List;

public class GuestControllers {
    private final GuestDAO guestDAO = new GuestDAO();
        
    public String addGuest(String firstName, String lastName, String email, String phone, String address) {
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank())
            return "ERROR: First name, last name, and email are required.";
        if (!email.matches("^[\\w.%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$"))
            return "ERROR: Invalid email format.";
        try {
            if (guestDAO.getGuestByEmail(email) != null)
                return "ERROR: A guest with this email already exists.";
            Guest g = new Guest(firstName.trim(), lastName.trim(), email.trim(), phone.trim(), address.trim());
            guestDAO.addGuest(g);
            return "SUCCESS: Guest added successfully.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String updateGuest(Guest g) {
        try {
            guestDAO.updateGuest(g);
            return "SUCCESS: Guest updated.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public String deleteGuest(int id) {
        try {
            guestDAO.deleteGuest(id);
            return "SUCCESS: Guest deleted.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public List<Guest> getAllGuests() {
        try { return guestDAO.getAllGuests(); }
        catch (SQLException e) { e.printStackTrace(); return List.of(); }
    }

    public Guest getGuestById(int id) {
        try { return guestDAO.getGuestById(id); }
        catch (SQLException e) { e.printStackTrace(); return null; }
    }
}
