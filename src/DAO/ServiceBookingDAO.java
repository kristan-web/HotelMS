package DAO;

import Database.Db_Connector;
import Model.ServiceBooking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

abstract class ServiceBookingDAOTemplate {
    abstract String              AddBookingQuery(ServiceBooking booking);
    abstract List<ServiceBooking> ListAllBookingsQuery();
    abstract String              CancelBookingQuery(int bookingId);
}

public class ServiceBookingDAO extends ServiceBookingDAOTemplate {

    // ── INSERT ────────────────────────────────────────────────────────────────
    @Override
    public String AddBookingQuery(ServiceBooking booking) {
        try (Connection dbconn = Db_Connector.getCOnnection()) {
            if (dbconn == null) return "Can't connect to the database.";

            String query =
                "INSERT INTO service_bookings (guest_id, service_id, scheduled_at, duration, total) " +
                "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pst = dbconn.prepareStatement(query)) {
                pst.setInt      (1, booking.getGuestId());
                pst.setInt      (2, booking.getServiceId());
                pst.setTimestamp(3, Timestamp.valueOf(booking.getScheduledAt()));
                pst.setInt      (4, booking.getDuration());
                pst.setDouble   (5, booking.getTotal());

                int rows = pst.executeUpdate();
                if (rows > 0) return "Service booked successfully.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to save booking.";
        } catch (Exception e) {
            return "Failed to save booking.";
        }
        return "Failed to save booking.";
    }

    // ── SELECT all (JOIN guests + services for display) ───────────────────────
    @Override
    public List<ServiceBooking> ListAllBookingsQuery() {
        List<ServiceBooking> list = new ArrayList<>();

        try (Connection dbconn = Db_Connector.getCOnnection()) {
            if (dbconn == null) return null;

            String query =
                "SELECT sb.*, " +
                "  CONCAT(g.first_name, ' ', g.last_name) AS guest_name, " +
                "  s.service_name " +
                "FROM service_bookings sb " +
                "JOIN guests   g ON sb.guest_id   = g.guest_id " +
                "JOIN services s ON sb.service_id = s.service_id " +
                "ORDER BY sb.scheduled_at DESC";

            try (PreparedStatement pst = dbconn.prepareStatement(query)) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ServiceBooking b = new ServiceBooking();
                    b.setBookingId  (rs.getInt      ("booking_id"));
                    b.setGuestId    (rs.getInt      ("guest_id"));
                    b.setGuestName  (rs.getString   ("guest_name"));
                    b.setServiceId  (rs.getInt      ("service_id"));
                    b.setServiceName(rs.getString   ("service_name"));
                    b.setScheduledAt(rs.getTimestamp("scheduled_at").toLocalDateTime());
                    b.setDuration   (rs.getInt      ("duration"));
                    b.setTotal      (rs.getDouble   ("total"));
                    b.setStatus     (rs.getString   ("status"));
                    list.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    // ── CANCEL (soft status update) ───────────────────────────────────────────
    @Override
    public String CancelBookingQuery(int bookingId) {
        try (Connection dbconn = Db_Connector.getCOnnection()) {
            if (dbconn == null) return "Can't connect to the database.";

            String query = "UPDATE service_bookings SET status = 'CANCELLED' WHERE booking_id = ?";
            try (PreparedStatement pst = dbconn.prepareStatement(query)) {
                pst.setInt(1, bookingId);
                if (pst.executeUpdate() > 0) return "Booking cancelled successfully.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to cancel booking.";
        } catch (Exception e) {
            return "Failed to cancel booking.";
        }
        return "Failed to cancel booking.";
    }
}
