package DAO;
import Database.*;
import java.sql.*;
import Model.*;
import java.util.ArrayList;
import java.util.List;

abstract class ServiceDAOTemplate{
    //Returns a string for JOptionPane, adds a service to the database.
    abstract String AddServiceQuery(Services service);
    
    //Returns a list of all services for table display.
    abstract List<Services> ListOfAllServicesQuery();
    
    //Returns a list of all services that matches the search bar.
    abstract List<Services> ListOfAllServicesQuery(String searchfield);
    
    abstract List<Services> ListOfAllDeletedServicesQuery();
    
    abstract List<Services> ListOfAllDeletedServicesQuery(String searchfield);
    
    //Returns an object that contains service details.
    abstract Services GetServiceDetailsByID(int serviceID);
    
    //Returns a string for JOptionPane, updates a service to the database.
    abstract String UpdateServiceQuery(int service_id, String service_name, double service_price, 
    int service_duration, String service_status);
    
    //Returns a string for JOptionPane, deletes a service to the database.
    abstract String DeleteServiceQuery(int service_id);
    
    //Returns a string for JOptionPane, restores a service to the database.
    abstract String RestoreServiceByIDQuery(int service_id);
    
            
    
}
public class ServiceDAO extends ServiceDAOTemplate{
    
    @Override
    public String AddServiceQuery(Services service){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return "Can't connect to the database";
            
            String query = "INSERT INTO Services (service_name, price, duration_minutes, status) "
                    + "VALUES (?, ?, ?, ?)";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, service.getServiceName());
                pst.setDouble(2, service.getPrice());
                pst.setInt(3, service.getDurationMinutes());
                pst.setString(4, service.getStatus());
                
                int rowsAffected = pst.executeUpdate();
                
                if(rowsAffected > 0) return "Service added successfully.";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return "Failed to add service.";
        
        }
        catch(Exception e){
            return "Failed to add service.";
        }
        return "Failed to add service.";
    }
    
    @Override
    public List<Services> ListOfAllServicesQuery(){
        List<Services> servicesList = new ArrayList<>();
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if (dbconn == null) return null;
            
            String query = "SELECT * from Services WHERE is_deleted = false";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Services service = new Services();
                    
                    service.setServiceID(rs.getInt("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    service.setDurationMinutes(rs.getInt("duration_minutes"));
                    service.setPrice(rs.getDouble("price"));
                    service.setStatus(rs.getString("status"));
                    
                    servicesList.add(service);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        catch(Exception e){
            return null;
        }
        
        return servicesList;
    }
    
    @Override
    public List<Services> ListOfAllServicesQuery(String searchfield){
        List<Services> servicesList = new ArrayList<>();
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if (dbconn == null) return null;
            
            String query = "SELECT * from Services WHERE service_name LIKE ? AND is_deleted = false";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, "%" + searchfield + "%");
                
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Services service = new Services();
                    
                    service.setServiceID(rs.getInt("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    service.setDurationMinutes(rs.getInt("duration_minutes"));
                    service.setPrice(rs.getDouble("price"));
                    service.setStatus(rs.getString("status"));
                    
                    servicesList.add(service);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        catch(Exception e){
            return null;
        }
        
        return servicesList;
    }
    
    @Override
    public Services GetServiceDetailsByID(int serviceID){
        Services service = null;
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            
            String query = "SELECT * FROM Services WHERE service_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, serviceID);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    service = new Services();
                    service.setServiceID(rs.getInt("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    service.setPrice(rs.getDouble("price"));
                    service.setDurationMinutes(rs.getInt("duration_minutes"));
                    service.setStatus(rs.getString("status"));
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        catch(Exception e){
            return null;
        }
        
        return service;
    }
    
    @Override
    public String UpdateServiceQuery(int service_id, String service_name, double service_price, 
    int service_duration, String service_status)
    {        
        try(Connection dbconn = Db_Connector.getCOnnection()){
           if(dbconn == null) return "Can't connect to the database";
           
           String query = "UPDATE Services SET service_name = ?, duration_minutes = ?, price = ?, status = ? "
           + "WHERE service_id = ?";
           
           try(PreparedStatement pst = dbconn.prepareStatement(query)){
               pst.setString(1, service_name);
               pst.setInt(2, service_duration);
               pst.setDouble(3, service_price);
               pst.setString(4, service_status);
               pst.setInt(5, service_id);
               
               int RowsAffected = pst.executeUpdate();
               
               if(RowsAffected > 0) return "Service updated successfully.";
           }
        }
        catch(SQLException e){
            e.printStackTrace();
            return "Failed to update service details";
        }
        catch(Exception e){
            return "Failed to update service details";
        }
        return "Failed to update service details";
    }
    
    @Override
    public String DeleteServiceQuery(int service_id){       
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return "Can't connect to the database";
            
            String query = "UPDATE Services SET is_deleted = true, status = 'Inactive' WHERE service_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, service_id);
                
                int RowsAffected = pst.executeUpdate();
                
                if(RowsAffected > 0) return "Service deleted successfully";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return "Failed to update service details";
        }
        catch(Exception e){
            return "Failed to update service details";
        }
        return "Failed to update service details";
    }
    
    @Override
    public List<Services> ListOfAllDeletedServicesQuery(){
        List<Services> servicesList = new ArrayList<>();
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if (dbconn == null) return null;
            
            String query = "SELECT * from Services WHERE is_deleted = true";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Services service = new Services();
                    
                    service.setServiceID(rs.getInt("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    service.setDurationMinutes(rs.getInt("duration_minutes"));
                    service.setPrice(rs.getDouble("price"));
                    service.setStatus(rs.getString("status"));
                    
                    servicesList.add(service);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        catch(Exception e){
            return null;
        }
        
        return servicesList;
    }
    
    @Override
    public List<Services> ListOfAllDeletedServicesQuery(String searchfield){
        List<Services> servicesList = new ArrayList<>();
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if (dbconn == null) return null;
            
            String query = "SELECT * from Services WHERE service_name LIKE ? AND is_deleted = true";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, "%" + searchfield + "%");
                
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Services service = new Services();
                    
                    service.setServiceID(rs.getInt("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    service.setDurationMinutes(rs.getInt("duration_minutes"));
                    service.setPrice(rs.getDouble("price"));
                    service.setStatus(rs.getString("status"));
                    
                    servicesList.add(service);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        catch(Exception e){
            return null;
        }
        
        return servicesList;
    }
    
    @Override
    public String RestoreServiceByIDQuery(int service_id){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return "Can't connect to the database";
            
            String query = "UPDATE Services SET is_deleted = false WHERE service_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, service_id);
                
                int RowsAffected = pst.executeUpdate();
                
                if(RowsAffected > 0) return "Service restored successfully";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return "Failed to update service details";
        }
        catch(Exception e){
            return "Failed to update service details";
        }
        return "Failed to update service details";
    }
}
