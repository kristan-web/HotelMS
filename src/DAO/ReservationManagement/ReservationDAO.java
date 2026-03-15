package DAO.ReservationManagement;

import Model.Customers;
import Model.ReservationManagement.Reservation;
import Model.ReservationManagement.Room;
import Database.Db_Connector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    /** Maps a full JOIN result row into a Reservation (with nested Guest & Room) */
    private Reservation mapRow(ResultSet rs) throws SQLException {
        Reservation r = new Reservation();
        r.setReservationId(rs.getInt("reservation_id"));
        r.setCheckIn(rs.getDate("check_in").toLocalDate());
        r.setCheckOut(rs.getDate("check_out").toLocalDate());
        r.setStatus(rs.getString("status"));
        r.setTotalAmount(rs.getDouble("total_amount"));
        r.setNotes(rs.getString("notes"));

        Customers g = new Customers();
        g.setCustomer_id(rs.getString("customer_id"));
        g.setFirst_name(rs.getString("first_name"));
        g.setLast_name(rs.getString("last_name"));
        g.setEmail(rs.getString("email"));
        g.setPhone_number(rs.getString("phone"));
        r.setGuest(g);

        Room room = new Room();
        room.setRoomId(rs.getInt("room_id"));
        room.setRoomNumber(rs.getString("room_number"));
        room.setRoomType(rs.getString("room_type"));
        room.setPrice(rs.getDouble("price"));
        r.setRoom(room);

        return r;
    }

    private static final String SELECT_ALL_SQL = """
        SELECT rv.*, 
               g.customer_id, g.first_name, g.last_name, g.email, g.phone,
               rm.room_id, rm.room_number, rm.room_type, rm.price
        FROM reservations rv
        JOIN customers g  ON rv.guest_id = g.customer_id
        JOIN rooms  rm ON rv.room_id  = rm.room_id
    """;

    public void addReservation(Reservation r) throws SQLException {
        String sql = "INSERT INTO reservations (customer_id, room_id, check_in, check_out, status, total_amount, notes) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, Integer.parseInt(r.getGuest().getCustomer_id()));
            ps.setInt(2, r.getRoom().getRoomId());
            ps.setDate(3, Date.valueOf(r.getCheckIn()));
            ps.setDate(4, Date.valueOf(r.getCheckOut()));
            ps.setString(5, r.getStatus() != null ? r.getStatus() : "CONFIRMED");
            ps.setDouble(6, r.getTotalAmount());
            ps.setString(7, r.getNotes());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) r.setReservationId(keys.getInt(1));
        }
    }

    public Reservation getReservationById(int id) throws SQLException {
        String sql = SELECT_ALL_SQL + " WHERE rv.reservation_id = ?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? mapRow(rs) : null;
        }
    }

    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> list = new ArrayList<>();
        String sql = SELECT_ALL_SQL + " ORDER BY rv.check_in DESC";
        try (Connection conn = Db_Connector.getCOnnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<Reservation> getReservationsByGuest(int guestId) throws SQLException {
        List<Reservation> list = new ArrayList<>();
        String sql = SELECT_ALL_SQL + " WHERE rv.customer_id = ? ORDER BY rv.check_in DESC";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, guestId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<Reservation> getReservationsByStatus(String status) throws SQLException {
        List<Reservation> list = new ArrayList<>();
        String sql = SELECT_ALL_SQL + " WHERE rv.status = ? ORDER BY rv.check_in";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public void updateReservation(Reservation r) throws SQLException {
        String sql = "UPDATE reservations SET customer_id=?, room_id=?, check_in=?, check_out=?, status=?, total_amount=?, notes=? WHERE reservation_id=?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(r.getGuest().getCustomer_id()));
            ps.setInt(2, r.getRoom().getRoomId());
            ps.setDate(3, Date.valueOf(r.getCheckIn()));
            ps.setDate(4, Date.valueOf(r.getCheckOut()));
            ps.setString(5, r.getStatus());
            ps.setDouble(6, r.getTotalAmount());
            ps.setString(7, r.getNotes());
            ps.setInt(8, r.getReservationId());
            ps.executeUpdate();
        }
    }

    public void updateStatus(int reservationId, String newStatus) throws SQLException {
        String sql = "UPDATE reservations SET status = ? WHERE reservation_id = ?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, reservationId);
            ps.executeUpdate();
        }
    }

    public void deleteReservation(int id) throws SQLException {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut) throws SQLException {
        String sql = """
            SELECT COUNT(*) FROM reservations
            WHERE room_id = ?
              AND status IN ('CONFIRMED','CHECKED_IN')
              AND check_in  < ?
              AND check_out > ?
        """;
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roomId);
            ps.setDate(2, Date.valueOf(checkOut));
            ps.setDate(3, Date.valueOf(checkIn));
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) == 0; // 0 conflicts = available
        }
    }
}
