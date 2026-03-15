package DAO;
import Model.Customers;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import Database.Db_Connector;
import javax.swing.JOptionPane;

abstract class CustomerDAOTemplate{
  
    abstract boolean AddCustomerQuery(Customers customer);
    
    abstract List<Customers> ListOfAllCustomersQuery();

    abstract List<Customers> ListOfAllCustomersQuery(String searchfield);

    abstract Customers GetCustomerDetailsQuery(int customer_id);
    
    abstract boolean UpdateCustomerQuery(Customers customer);

    abstract boolean DeleteCustomerQuery(int customerID);

    abstract List<Customers> ListOfAllDeletedCustomersQuery();

    abstract List<Customers> ListOfAllDeletedCustomersQuery(String searchfield);

    abstract boolean RestoreCustomerByIDQuery(int customerID);
    
    abstract Customers mapRow(ResultSet rs) throws SQLException;
    
    abstract Customers getGuestByEmail(String email) throws SQLException;

}



public class CustomerDAO extends CustomerDAOTemplate{
    
    public Customers mapRow(ResultSet rs) throws SQLException {
        Customers g = new Customers();
        g.setCustomer_id(rs.getString("guest_id"));
        g.setFirst_name(rs.getString("first_name"));
        g.setLast_name(rs.getString("last_name"));
        g.setEmail(rs.getString("email"));
        g.setPhone_number(rs.getString("phone"));
        g.setCustomer_address(rs.getString("address"));
        return g;
    }
    
    @Override
    public boolean AddCustomerQuery(Customers customer){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return false;
            
            String query = "INSERT INTO Customer(first_name, last_name, phone_number, "
            + "email, status) VALUES (?, ?, ?, ?, ?)";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, customer.getFirst_name());
                pst.setString(2, customer.getLast_name());
                pst.setString(3, customer.getPhone_number());
                pst.setString(4, customer.getEmail());
                pst.setString(5, customer.getStatus());
                
                int RowsAffected = pst.executeUpdate();
                
                if(RowsAffected > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to add customer to database.");
                return false;
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Failed to add customer to database.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Failed to add customer to database.");
        return false;
    }
    
    @Override
    public List<Customers> ListOfAllCustomersQuery(){
        List<Customers> cstmr = new ArrayList<>();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            String query = "SELECT * FROM CUSTOMER WHERE is_deleted = false";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Customers customer = new Customers();
                    
                    customer.setCustomer_id(rs.getString("customer_id"));
                    customer.setFirst_name(rs.getString("first_name"));
                    customer.setLast_name(rs.getString("last_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone_number(rs.getString("phone_number"));
                    customer.setStatus(rs.getString("status"));
                    
                    cstmr.add(customer);
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
        return cstmr;
    }
    
    @Override
    public List<Customers> ListOfAllCustomersQuery(String searchfield){
        List<Customers> cstmr = new ArrayList<>();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            String query = "SELECT * FROM CUSTOMER WHERE first_name LIKE ? AND is_deleted = false";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, "%" + searchfield + "%");
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Customers customer = new Customers();
                    
                    customer.setCustomer_id(rs.getString("customer_id"));
                    customer.setFirst_name(rs.getString("first_name"));
                    customer.setLast_name(rs.getString("last_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone_number(rs.getString("phone_number"));
                    customer.setStatus(rs.getString("status"));
                    
                    cstmr.add(customer);
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
        return cstmr;
    }
    
    @Override
    public Customers GetCustomerDetailsQuery(int customer_id){
        Customers cstmr = new Customers();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            
            String query = "SELECT * FROM Customer WHERE customer_id = ? AND is_deleted = false";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, customer_id);
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    cstmr.setCustomer_id(rs.getString("customer_id"));
                    cstmr.setFirst_name(rs.getString("first_name"));
                    cstmr.setLast_name(rs.getString("last_name"));
                    cstmr.setPhone_number(rs.getString("phone_number"));
                    cstmr.setEmail(rs.getString("email"));
                    cstmr.setEmail(rs.getString("address"));
                    cstmr.setStatus(rs.getString("status"));
                }
            }
            catch(Exception e){
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        
        return cstmr;
    }
    
    @Override
    public boolean UpdateCustomerQuery(Customers customer){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            String query = "UPDATE Customer SET first_name = ?, last_name = ?, "
            + "phone_number = ?, email = ?, status = ? WHERE customer_id = ?";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, customer.getFirst_name());
                pst.setString(2, customer.getLast_name());
                pst.setString(3, customer.getPhone_number());
                pst.setString(4, customer.getEmail());
                pst.setString(5, customer.getStatus());
                pst.setInt(6, Integer.parseInt(customer.getCustomer_id()));
                
                int AffectedRow = pst.executeUpdate();
                
                if(AffectedRow > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to update customer details.");
                return false;
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Failed to update customer details.");
            return false;
        }
        
        
        JOptionPane.showMessageDialog(null, "Failed to update customer details.");
        return false;
    }
    
    @Override
    public boolean DeleteCustomerQuery(int customerID){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return false;
            
            String query = "UPDATE Customer SET is_deleted = true, status = 'Inactive' WHERE customer_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, customerID);
                
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
    public List<Customers> ListOfAllDeletedCustomersQuery(){
        List<Customers> ListOfDeletedCustomers = new ArrayList<>();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            
            String query = "SELECT * FROM Customer WHERE is_deleted = true";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Customers customer = new Customers();
                    
                    customer.setCustomer_id(rs.getString("customer_id"));
                    customer.setFirst_name(rs.getString("first_name"));
                    customer.setLast_name(rs.getString("last_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone_number(rs.getString("phone_number"));
                    customer.setStatus(rs.getString("status"));
                    
                    ListOfDeletedCustomers.add(customer);
                }
                
                return ListOfDeletedCustomers;
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
    public List<Customers> ListOfAllDeletedCustomersQuery(String searchfield){
        List<Customers> ListOfDeletedCustomers = new ArrayList<>();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            
            String query = "SELECT * FROM Customer WHERE first_name LIKE ? AND is_deleted = true";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, "%" + searchfield + "%");
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Customers customer = new Customers();
                    
                    customer.setCustomer_id(rs.getString("customer_id"));
                    customer.setFirst_name(rs.getString("first_name"));
                    customer.setLast_name(rs.getString("lastt_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone_number(rs.getString("phone_number"));
                    customer.setStatus(rs.getString("status"));
                    
                    ListOfDeletedCustomers.add(customer);
                }
                
                return ListOfDeletedCustomers;
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
    public boolean RestoreCustomerByIDQuery(int customerID){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null){ 
                JOptionPane.showMessageDialog(null, "Failed to restore customer");
                return false;
            }
            
            String query = "UPDATE Customer SET is_deleted = false, status = 'Active' WHERE customer_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, customerID);
                
                int RowsAffected = pst.executeUpdate();
                
                if(RowsAffected > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to restore customer");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to restore customer");
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to restore customer");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Failed to restore customer");
        return false;
    }
    
    public Customers getGuestByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM guests WHERE email = ?";
        try (Connection conn = Db_Connector.getCOnnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? mapRow(rs) : null;
        }
    }
}
