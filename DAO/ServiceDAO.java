package DAO;
import Database.*;
import java.sql.*;
import Model.*;
import java.util.ArrayList;
import java.util.List;
public class ServiceDAO {
    
    public boolean AddServiceQuery(Services service){
        /*
            CONNECT TO DATABASE
            CREATE A QUERY
            PASS THE SERVICES MODEL ATTRIBUTES TO QUERY VALUES
            CHECK IF THERE ARE ROWS AFFECTED
            IF ROWS AFFECTED > 1 RETURN TRUE
        */
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return false;
            
            String query = "INSERT INTO Services (service_name, price, duration_minutes, status) "
                    + "VALUES (?, ?, ?, ?)";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, service.getServiceName());
                pst.setDouble(2, service.getPrice());
                pst.setInt(3, service.getDurationMinutes());
                pst.setString(4, service.getStatus());
                
                int rowsAffected = pst.executeUpdate();
                
                return rowsAffected > 0;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        
        }
    }
    
    public List<Services> getAllServices(){
        /*
            CREATES A LIST
            CONNECT TO DATABASE
            EXECUTE SELECT QUERY
            WHILE RS.NEXT() IS TRUE, INSTANTIATE A SERVICE MODEL AND ADD TO THE LIST
            AFTER THE LOOP, RETURN THE LIST
            
        */
        List<Services> servicesList = new ArrayList<>();
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if (dbconn == null) return servicesList;
            
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
        }
        
        return servicesList;
    }
    
    public List<Services> getAllServices(String text){
        List<Services> servicesList = new ArrayList<>();
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if (dbconn == null) return servicesList;
            
            String query = "SELECT * from Services WHERE service_name LIKE ? AND is_deleted = false";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, "%" + text + "%");
                
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
        }
        
        return servicesList;
    }
    
    public Services GetServiceDetailsByID(int serviceID){
        Services s = null;
        /*
            CONNECT TO DATABASE
            SELECT QUERY
            PASS THE DETAILS TO SERVICES MODEL
            RETURN THE MODEL
        */
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            String query = "SELECT * FROM Services WHERE service_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, serviceID);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    s = new Services();
                    s.setServiceID(rs.getInt("service_id"));
                    s.setServiceName(rs.getString("service_name"));
                    s.setPrice(rs.getDouble("price"));
                    s.setDurationMinutes(rs.getInt("duration_minutes"));
                    s.setStatus(rs.getString("status"));
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return s;
    }
    
    public boolean UpdateServiceToDatabase(int service_id, String service_name, double service_price, 
    int service_duration, String service_status)
    {
        /*
            CONNECT TO DATABASE
            QUERY
            PREPARE STATEMENT
            EXECUTE QUERY
            RETURN TRUE OR FALSE
        */
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
           if(dbconn == null) return false;
           
           String query = "UPDATE Services SET service_name = ?, duration_minutes = ?, price = ?, status = ? "
           + "WHERE service_id = ?";
           
           try(PreparedStatement pst = dbconn.prepareStatement(query)){
               pst.setString(1, service_name);
               pst.setInt(2, service_duration);
               pst.setDouble(3, service_price);
               pst.setString(4, service_status);
               pst.setInt(5, service_id);
               
               int RowsAffected = pst.executeUpdate();
               
               return RowsAffected > 0;
           }
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean DeleteServiceToDatabase(int service_id){
        /*
            CONNECT TO DATABASE
            QUERY
            PREPARE STATEMENT
            RETURNED ROWS
            IF ROWS > 0 RETURN TRUE
            ELSE FALSE
        */
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            String query = "UPDATE Services SET is_deleted = true WHERE service_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, service_id);
                
                int RowsAffected = pst.executeUpdate();
                
                return RowsAffected > 0;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
