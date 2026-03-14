package DAO;
import Model.Customers;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import Database.Db_Connector;

abstract class CustomerDAOTemplate{
    
    abstract String AddCustomerQuery(String first_name, String last_name, 
    String phone_number, String email, String status);
    
    //Returns a list of customers for table display.
    abstract List<Customers> ListOfAllCustomersQuery();
    
    //Method overloading, returns a list of customers that matches the searchbar.
    abstract List<Customers> ListOfAllCustomersQuery(String searchfield);
    
    //Returns an object that contains customer details.
    abstract Customers GetCustomerDetailsQuery(int customer_id);
    
    //Updates customer details. Returns string for JOptionPane message.
    abstract String UpdateCustomerQuery(int customer_id, String fname, 
                                             String lname, String email, String phone, 
                                             String status);
    
    //Deletes customer details. Returns string for JOptionPane message.
    abstract String DeleteCustomerQuery(int customerID);

}



public class CustomerDAO extends CustomerDAOTemplate{
    @Override
    public String AddCustomerQuery(String first_name, String last_name, 
    String phone_number, String email, String status){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return "Can't connect to the database";
            
            String query = "INSERT INTO Customer(first_name, last_name, phone_number, "
            + "email, status) VALUES (?, ?, ?, ?, ?)";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, first_name);
                pst.setString(2, last_name);
                pst.setString(3, phone_number);
                pst.setString(4, email);
                pst.setString(5, status);
                
                int RowsAffected = pst.executeUpdate();
                
                if(RowsAffected > 0) return "Service added successfully.";
            }
        }
        catch(SQLException e){
            return "Failed to add customer to the database";
        }
        return "Failed to add customer to the database";
    }
    
    public List<Customers> ListOfAllCustomersQuery(){
        List<Customers> cstmr = new ArrayList<>();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            String query = "SELECT * FROM CUSTOMER WHERE is_deleted = false";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Customers customer = new Customers();
                    
                    customer.setCustomer_id(rs.getInt("customer_id"));
                    customer.setFirst_name(rs.getString("first_name"));
                    customer.setLast_name(rs.getString("last_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone_number(rs.getString("phone_number"));
                    customer.setStatus(rs.getString("status"));
                    
                    cstmr.add(customer);
                }
            }
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return cstmr;
    }
    
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
                    
                    customer.setCustomer_id(rs.getInt("customer_id"));
                    customer.setFirst_name(rs.getString("first_name"));
                    customer.setLast_name(rs.getString("last_name"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone_number(rs.getString("phone_number"));
                    customer.setStatus(rs.getString("status"));
                    
                    cstmr.add(customer);
                }
            }
            
            
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return cstmr;
    }
    
    public Customers GetCustomerDetailsQuery(int customer_id){
        Customers cstmr = new Customers();
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            
            String query = "SELECT * FROM Customer WHERE customer_id = ? AND is_deleted = false";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, customer_id);
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    cstmr.setCustomer_id(rs.getInt("customer_id"));
                    cstmr.setFirst_name(rs.getString("first_name"));
                    cstmr.setLast_name(rs.getString("last_name"));
                    cstmr.setPhone_number(rs.getString("phone_number"));
                    cstmr.setEmail(rs.getString("email"));
                    cstmr.setStatus(rs.getString("status"));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        
        return cstmr;
    }
    
    public String UpdateCustomerQuery(int customer_id, String fname, String lname, String email, String phone, String status){
        /*
            CONNECT TO DATABASE
            QUERY
            PREPARED STATEMENT
            AFFECTED ROW
            
            IF > 1 RETURN UPDATE SUCCESS
            ELSE FAILED TO UPDATE
        */
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            String query = "UPDATE Customer SET first_name = ?, last_name = ?, "
            + "phone_number = ?, email = ?, status = ? WHERE customer_id = ?";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, fname);
                pst.setString(2, lname);
                pst.setString(3, phone);
                pst.setString(4, email);
                pst.setString(5, status);
                pst.setInt(6, customer_id);
                
                int AffectedRow = pst.executeUpdate();
                
                if(AffectedRow > 0) return "Update Success";
            }
        }
        catch(SQLException e){
            return "Failed to update customer details.";
        }
        
        return "Failed to update customer details.";
    }
    
    public String DeleteCustomerQuery(int customerID){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return "Can't connect to database.";
            
            String query = "UPDATE Customer SET is_deleted = true WHERE customer_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, customerID);
                
                int AffectedRows = pst.executeUpdate();
                
                if(AffectedRows > 0) return "Delete Success.";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return "Failed to delete from database";
        }
        catch(Exception e){
            return "Failed to delete from database";
        }
        return "Failed to delete from database";
    }
}
