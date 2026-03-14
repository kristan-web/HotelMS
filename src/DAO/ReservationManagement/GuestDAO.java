package DAO.ReservationManagement; // Or your preferred package

import Model.ReservationManagement.Guest;
import Database.Db_Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {

    private Guest mapRow(ResultSet rs) throws SQLException {
        Guest g = new Guest();
        g.setGuestId(rs.getInt("guest_id"));
        g.setFirstName(rs.getString("first_name"));
        g.setLastName(rs.getString("last_name"));
        g.setEmail(rs.getString("email"));
        g.setPhone(rs.getString("phone"));
        g.setAddress(rs.getString("address"));
        return g;
    }

    public void addGuest(Guest g) throws SQLException {
        String sql = "INSERT INTO guests (first_name, last_name, email, phone, address) VALUES (?,?,?,?,?)";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, g.getFirstName());
            ps.setString(2, g.getLastName());
            ps.setString(3, g.getEmail());
            ps.setString(4, g.getPhone());
            ps.setString(5, g.getAddress());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) g.setGuestId(keys.getInt(1));
        }
    }

    public Guest getGuestById(int id) throws SQLException {
        String sql = "SELECT * FROM guests WHERE guest_id = ?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? mapRow(rs) : null;
        }
    }

    public Guest getGuestByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM guests WHERE email = ?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? mapRow(rs) : null;
        }
    }

    public List<Guest> getAllGuests() throws SQLException {
        List<Guest> list = new ArrayList<>();
        String sql = "SELECT * FROM guests ORDER BY last_name, first_name";
        try (Connection conn = Db_Connector.getCOnnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public void updateGuest(Guest g) throws SQLException {
        String sql = "UPDATE guests SET first_name=?, last_name=?, email=?, phone=?, address=? WHERE guest_id=?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, g.getFirstName());
            ps.setString(2, g.getLastName());
            ps.setString(3, g.getEmail());
            ps.setString(4, g.getPhone());
            ps.setString(5, g.getAddress());
            ps.setInt(6, g.getGuestId());
            ps.executeUpdate();
        }
    }

    public void deleteGuest(int id) throws SQLException {
        String sql = "DELETE FROM guests WHERE guest_id = ?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}