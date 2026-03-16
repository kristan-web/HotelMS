package DAO;
import Database.Db_Connector;
import java.sql.*;
import javax.swing.JOptionPane;

abstract class DashboardDAOTemplate {
    
    // ADMIN
    abstract int getAvailableServicesQuery();
    
    abstract double getTotalRevenueQuery();
    
    abstract int getTotalReservationsQuery();
    
    // -- TOTAL STAFF
    
    //STAFF
    
    //-- Today's Reservations
    
    // -- Today's Revenue
    
    // -- Today's Check-ins
    
    // -- Today's Check-outs
    
}

public class DashboardDAO extends DashboardDAOTemplate{
    
    @Override
    public int getAvailableServicesQuery(){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return -1;
            
            String query = "SELECT COUNT(*) FROM Services WHERE status = 'Active'";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){
                    int returnedRow = rs.getInt(1);
                    return returnedRow;
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Can't connect to the database.");
                return -1;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Can't connect to the database.");
            return -1;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "An error occured.");
            return -1;
        }
    return -1;
    }
    
    public int getInactiveServicesQuery(){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return -1;
            
            String query = "SELECT COUNT(*) FROM Services WHERE status = 'Inactive'";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){
                    int returnedRow = rs.getInt(1);
                    return returnedRow;
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Can't connect to the database.");
                return -1;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Can't connect to the database.");
            return -1;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "An error occured.");
            return -1;
        }
    return -1;        
    }
    
    public double getTotalRevenueQuery(){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return -1;
            
            String query = 
                """
                SELECT 
                    COALESCE(
                        (SELECT SUM(total_amount) 
                        FROM Reservations 
                        WHERE status='CHECKED_OUT' AND is_deleted=FALSE),0)
                        +
                    COALESCE(
                        (SELECT SUM(total) 
                        FROM service_bookings 
                        WHERE status='COMPLETED'),0)
                    AS total_revenue;
                """;
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){
                    double returnedRow = rs.getDouble(1);
                    return returnedRow;
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Can't connect to the database.");
                return -1;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Can't connect to the database.");
            return -1;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "An error occured.");
            return -1;
        }
    return -1;   
    }
    
    public int getTotalReservationsQuery(){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return -1;
            
            String query = 
                """
                    SELECT COUNT(*) AS total_completed_reservations
                    FROM Reservations
                    WHERE status = 'CHECKED_OUT'
                    AND is_deleted = FALSE;
                """;
            
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Can't connect to the database.");
                return -1;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Can't connect to the database.");
            return -1;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "An error occured.");
            return -1;
        }
    return -1;  
    }
}


