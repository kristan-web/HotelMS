package DAO;
import Model.Guests;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import Database.Db_Connector;
import javax.swing.JOptionPane;

abstract class GuestDAOTemplate{
  
    abstract boolean AddGuestQuery(Guests guest);
    
    abstract List<Guests> ListOfAllGuestsQuery();

    abstract List<Guests> ListOfAllGuestsQuery(String searchfield);

    abstract Guests GetGuestDetailsQuery(int guest_id);
    
    abstract boolean UpdateGuestQuery(Guests guest);

    abstract boolean DeleteGuestQuery(int guestID);

    abstract List<Guests> ListOfAllDeletedGuestsQuery();

    abstract List<Guests> ListOfAllDeletedGuestsQuery(String searchfield);

    abstract boolean RestoreGuestByIDQuery(int guestID);

}



public class GuestDAO extends GuestDAOTemplate{
    @Override
    public boolean AddGuestQuery(Guests guest){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return false;
            
            String query = "INSERT INTO Guests(first_name, last_name, phone, "
            + "email, address) VALUES (?, ?, ?, ?, ?)";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, guest.getFirst_name());
                pst.setString(2, guest.getLast_name());
                pst.setString(3, guest.getPhone());
                pst.setString(4, guest.getEmail());
                pst.setString(5, guest.getAddress());
                
                int RowsAffected = pst.executeUpdate();
                
                if(RowsAffected > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to add guest to database.");
                return false;
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Failed to add guest to database.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Failed to add guest to database.");
        return false;
    }
    
    @Override
    public List<Guests> ListOfAllGuestsQuery(){
        List<Guests> gst = new ArrayList<>();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            String query = "SELECT * FROM Guests WHERE is_deleted = false";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Guests guest = new Guests();
                    
                    guest.setGuest_id(rs.getString("guest_id"));
                    guest.setFirst_name(rs.getString("first_name"));
                    guest.setLast_name(rs.getString("last_name"));
                    guest.setEmail(rs.getString("email"));
                    guest.setPhone(rs.getString("phone"));
                    guest.setAddress(rs.getString("address"));
                    guest.setStatus(rs.getString("status"));
                    
                    gst.add(guest);
                }
            }
            catch(Exception e){
                return null;
            }
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return gst;
    }
    
    @Override
    public List<Guests> ListOfAllGuestsQuery(String searchfield){
        List<Guests> gst = new ArrayList<>();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            String query = "SELECT * FROM Guests WHERE first_name LIKE ? AND is_deleted = false";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, "%" + searchfield + "%");
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Guests guest = new Guests();
                    
                    guest.setGuest_id(rs.getString("guest_id"));
                    guest.setFirst_name(rs.getString("first_name"));
                    guest.setLast_name(rs.getString("last_name"));
                    guest.setEmail(rs.getString("email"));
                    guest.setPhone(rs.getString("phone"));
                    guest.setAddress(rs.getString("address"));
                    guest.setStatus(rs.getString("status"));
                    
                    gst.add(guest);
                }
            }
            catch(Exception e){
                return null;
            }
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return gst;
    }
    
    @Override
    public Guests GetGuestDetailsQuery(int guest_id){
        Guests guest = new Guests();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            
            String query = "SELECT * FROM Guests WHERE guest_id = ? AND is_deleted = false";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, guest_id);
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    guest.setGuest_id(rs.getString("guest_id"));
                    guest.setFirst_name(rs.getString("first_name"));
                    guest.setLast_name(rs.getString("last_name"));
                    guest.setEmail(rs.getString("email"));
                    guest.setPhone(rs.getString("phone"));
                    guest.setAddress(rs.getString("address"));
                    guest.setStatus(rs.getString("status"));
                }
            }
            catch(Exception e){
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        
        return guest;
    }
    
    @Override
    public boolean UpdateGuestQuery(Guests guest){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            String query = "UPDATE Guests SET first_name = ?, last_name = ?, "
            + "phone = ?, email = ?, status = ?, address = ? WHERE guest_id = ?";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, guest.getFirst_name());
                pst.setString(2, guest.getLast_name());
                pst.setString(3, guest.getPhone());
                pst.setString(4, guest.getEmail());
                pst.setString(5, guest.getStatus());
                pst.setString(6, guest.getAddress());
                pst.setInt(7, Integer.parseInt(guest.getGuest_id()));
                
                int AffectedRow = pst.executeUpdate();
                
                if(AffectedRow > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to update guest details.");
                return false;
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Failed to update guest details.");
            return false;
        }
        
        
        JOptionPane.showMessageDialog(null, "Failed to update guest details.");
        return false;
    }
    
    @Override
    public boolean DeleteGuestQuery(int guestID){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return false;
            
            String query = "UPDATE Guests SET is_deleted = true, status = 'Inactive' WHERE guest_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, guestID);
                
                int AffectedRows = pst.executeUpdate();
                
                if(AffectedRows > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to delete from database.");
                return false;
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Failed to delete from database.");
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to delete from database.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Failed to delete from database.");
        return false;
    }
    
    @Override
    public List<Guests> ListOfAllDeletedGuestsQuery(){
        List<Guests> ListOfDeletedGuests = new ArrayList<>();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            
            String query = "SELECT * FROM Guests WHERE is_deleted = true";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Guests guest = new Guests();
                    
                    guest.setGuest_id(rs.getString("guest_id"));
                    guest.setFirst_name(rs.getString("first_name"));
                    guest.setLast_name(rs.getString("last_name"));
                    guest.setEmail(rs.getString("email"));
                    guest.setPhone(rs.getString("phone"));
                    guest.setStatus(rs.getString("status"));
                    guest.setAddress(rs.getString("address"));
                    
                    ListOfDeletedGuests.add(guest);
                }
                
                return ListOfDeletedGuests;
            }
            catch(Exception e){
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        catch(Exception e){
            return null;
        }
    }
    
    @Override
    public List<Guests> ListOfAllDeletedGuestsQuery(String searchfield){
        List<Guests> ListOfDeletedGuests = new ArrayList<>();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            
            String query = "SELECT * FROM Guests WHERE first_name LIKE ? AND is_deleted = true";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, "%" + searchfield + "%");
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Guests guest = new Guests();
                    
                    guest.setGuest_id(rs.getString("guest_id"));
                    guest.setFirst_name(rs.getString("first_name"));
                    guest.setLast_name(rs.getString("last_name"));
                    guest.setEmail(rs.getString("email"));
                    guest.setPhone(rs.getString("phone"));
                    guest.setStatus(rs.getString("status"));
                    guest.setAddress(rs.getString("address"));
                    
                    ListOfDeletedGuests.add(guest);
                }
                
                return ListOfDeletedGuests;
            }
            catch(Exception e){
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        catch(Exception e){
            return null;
        }
    }
    
    @Override
    public boolean RestoreGuestByIDQuery(int guestID){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null){ 
                JOptionPane.showMessageDialog(null, "Failed to restore guest");
                return false;
            }
            
            String query = "UPDATE Guests SET is_deleted = false, status = 'Active' WHERE guest_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, guestID);
                
                int RowsAffected = pst.executeUpdate();
                
                if(RowsAffected > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to restore guest.");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to restore guest.");
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to restore guest.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Failed to restore guest.");
        return false;
    }
}
