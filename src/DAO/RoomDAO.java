package DAO;

import Model.Room;
import Database.Db_Connector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO{

    private Room mapRow(ResultSet rs) throws SQLException {
        Room r = new Room();
        r.setRoomId(rs.getInt("room_id"));
        r.setRoomNumber(rs.getString("room_number"));
        r.setRoomType(rs.getString("room_type"));
        r.setPrice(rs.getDouble("price"));
        r.setCapacity(rs.getInt("capacity"));
        r.setStatus(rs.getString("status"));
        r.setDescription(rs.getString("description"));
        return r;
    }

    public void addRoom(Room r) throws SQLException {
        String sql = "INSERT INTO rooms (room_number, room_type, price, capacity, status, description) VALUES (?,?,?,?,?,?)";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getRoomNumber());
            ps.setString(2, r.getRoomType());
            ps.setDouble(3, r.getPrice());
            ps.setInt(4, r.getCapacity());
            ps.setString(5, r.getStatus() != null ? r.getStatus() : "AVAILABLE");
            ps.setString(6, r.getDescription());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) r.setRoomId(keys.getInt(1));
        }
    }

    public Room getRoomById(int id) throws SQLException {
        String sql = "SELECT * FROM rooms WHERE room_id = ?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? mapRow(rs) : null;
        }
    }

    public List<Room> getAllRooms() throws SQLException {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM rooms ORDER BY room_number";
        try (Connection conn = Db_Connector.getCOnnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) throws SQLException {
        // Rooms NOT booked (confirmed/checked-in) during the requested window
        String sql = """
            SELECT * FROM rooms
            WHERE status = 'AVAILABLE'
              AND room_id NOT IN (
                SELECT room_id FROM reservations
                WHERE status IN ('CONFIRMED','CHECKED_IN')
                  AND check_in  < ?
                  AND check_out > ?
              )
            ORDER BY room_type, price
        """;
        List<Room> list = new ArrayList<>();
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(checkOut));
            ps.setDate(2, Date.valueOf(checkIn));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public void updateRoom(Room r) throws SQLException {
        String sql = "UPDATE rooms SET room_number=?, room_type=?, price=?, capacity=?, status=?, description=? WHERE room_id=?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getRoomNumber());
            ps.setString(2, r.getRoomType());
            ps.setDouble(3, r.getPrice());
            ps.setInt(4, r.getCapacity());
            ps.setString(5, r.getStatus());
            ps.setString(6, r.getDescription());
            ps.setInt(7, r.getRoomId());
            ps.executeUpdate();
        }
    }

    public void updateRoomStatus(int roomId, String status) throws SQLException {
        String sql = "UPDATE rooms SET status = ? WHERE room_id = ?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, roomId);
            ps.executeUpdate();
        }
    }

    public void deleteRoom(int id) throws SQLException {
        String sql = "DELETE FROM rooms WHERE room_id = ?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
