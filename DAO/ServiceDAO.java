package DAO;
import Database.*;
import java.sql.*;
import Model.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

abstract class ServiceDAOTemplate{

    abstract boolean AddServiceQuery(Services service);

    abstract List<Services> ListOfAllServicesQuery();
    
    abstract List<Services> ListOfAllServicesQuery(String searchfield);
    
    abstract List<Services> ListOfAllDeletedServicesQuery();
    
    abstract List<Services> ListOfAllDeletedServicesQuery(String searchfield);
    
    abstract Services GetServiceDetailsByID(int serviceID);
    
    abstract boolean UpdateServiceQuery(Services service);
    
    abstract boolean DeleteServiceQuery(int service_id);
    
    abstract boolean RestoreServiceByIDQuery(int service_id);  
}





public class ServiceDAO extends ServiceDAOTemplate{
    
    @Override
    public boolean AddServiceQuery(Services service){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null){
                JOptionPane.showMessageDialog(null, "Can't connect to the database.");
                return false;
            }
            
            String query = "INSERT INTO Services (service_name, price, duration_minutes, status) "
                    + "VALUES (?, ?, ?, ?)";
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, service.getServiceName());
                pst.setDouble(2, Double.parseDouble(service.getPrice()));
                pst.setInt(3, Integer.parseInt(service.getDurationMinutes()));
                pst.setString(4, service.getStatus());
                
                int rowsAffected = pst.executeUpdate();
                
                if(rowsAffected > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to add service.");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add service.");
            return false;
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to add service.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Failed to add service.");
        return false;
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
                    
                    service.setServiceID(rs.getString("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    service.setDurationMinutes(rs.getString("duration_minutes"));
                    service.setPrice(rs.getString("price"));
                    service.setStatus(rs.getString("status"));
                    
                    servicesList.add(service);
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
                    
                    service.setServiceID(rs.getString("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    service.setDurationMinutes(rs.getString("duration_minutes"));
                    service.setPrice(rs.getString("price"));
                    service.setStatus(rs.getString("status"));
                    
                    servicesList.add(service);
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
        catch(Exception e){
            return null;
        }
        
        return servicesList;
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
                    
                    service.setServiceID(rs.getString("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    service.setDurationMinutes(rs.getString("duration_minutes"));
                    service.setPrice(rs.getString("price"));
                    service.setStatus(rs.getString("status"));
                    
                    servicesList.add(service);
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
                    
                    service.setServiceID(rs.getString("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    service.setDurationMinutes(rs.getString("duration_minutes"));
                    service.setPrice(rs.getString("price"));
                    service.setStatus(rs.getString("status"));
                    
                    servicesList.add(service);
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
                if(rs.next()){
                    service = new Services();
                    
                    service.setServiceID(rs.getString("service_id"));
                    service.setServiceName(rs.getString("service_name"));
                    service.setPrice(rs.getString("price"));
                    service.setDurationMinutes(rs.getString("duration_minutes"));
                    service.setStatus(rs.getString("status"));
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to get service details.");
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to get service details.");
            return null;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to get service details.");
            return null;
        }
        
        return service;
    }
    
    @Override
    public boolean UpdateServiceQuery(Services service){        
        try(Connection dbconn = Db_Connector.getCOnnection()){
           if(dbconn == null){
               JOptionPane.showMessageDialog(null, "Can't connect to the database.");
                return false;
           }
           
           String query = "UPDATE Services SET service_name = ?, duration_minutes = ?, price = ?, status = ? "
           + "WHERE service_id = ?";
           
           try(PreparedStatement pst = dbconn.prepareStatement(query)){
               pst.setString(1, service.getServiceName());
               pst.setInt(2, Integer.parseInt(service.getDurationMinutes()));
               pst.setDouble(3, Double.parseDouble(service.getPrice()));
               pst.setString(4, service.getStatus());
               pst.setInt(5, Integer.parseInt(service.getServiceID()));
               
               int RowsAffected = pst.executeUpdate();
               
               if(RowsAffected > 0) return true;
           }
           catch(Exception e){
               JOptionPane.showMessageDialog(null, "Failed to update service details.");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update service details.");
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to update service details.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Failed to update service details.");
        return false;
    }
    
    @Override
    public boolean DeleteServiceQuery(int service_id){       
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null){
                JOptionPane.showMessageDialog(null, "Can't connect to the database");
                return false;
            }
            
            String query = "UPDATE Services SET is_deleted = true, status = 'Inactive' WHERE service_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, service_id);
                
                int RowsAffected = pst.executeUpdate();
                
                if(RowsAffected > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to delete service.");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to delete service.");
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to delete service.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Failed to delete service.");
        return false;
    }
    
    @Override
    public boolean RestoreServiceByIDQuery(int service_id){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null){
                JOptionPane.showMessageDialog(null, "Can't connect to the database");
                return false;
            }
            
            String query = "UPDATE Services SET is_deleted = false WHERE service_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, service_id);
                
                int RowsAffected = pst.executeUpdate();
                
                if(RowsAffected > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to restore service.");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to restore service.");
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to restore service.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Failed to restore service.");
        return false;
    }
}
