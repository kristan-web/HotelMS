package Controllers;

import DAO.ServiceBookingDAO;
import Model.ServiceBooking;
import java.time.LocalDateTime;
import java.util.List;

public class ServiceBookingControllers {
 
    private static final ServiceBookingDAO dao = new ServiceBookingDAO();
 
    public String AddBookingProcess(int guestId, int serviceId,
                                    LocalDateTime scheduledAt,
                                    int duration, double total) {
        if (guestId   <= 0) return "Please select a valid guest.";
        if (serviceId <= 0) return "Please select a valid service.";
        if (scheduledAt == null) return "Scheduled time is missing.";
 
        ServiceBooking b = new ServiceBooking();
        b.setGuestId    (guestId);
        b.setServiceId  (serviceId);
        b.setScheduledAt(scheduledAt);
        b.setDuration   (duration);
        b.setTotal      (total);
 
        return dao.AddBookingQuery(b);
    }
 
    public List<ServiceBooking> ListAllBookings() {
        return dao.ListAllBookingsQuery();
    }
 
    public String CancelBooking(int bookingId) {
        if (bookingId <= 0) return "No booking selected.";
        return dao.CancelBookingQuery(bookingId);
    }
}