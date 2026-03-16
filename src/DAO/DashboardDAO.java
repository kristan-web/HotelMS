package DAO;
import Database.Db_Connector;
import java.sql.*;
import javax.swing.JOptionPane;

abstract class DashboardDAOTemplate {
    abstract int getAvailableServicesQuery();
    /*
    abstract int getOccupiedServicesQuery();
    */
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
    
 /*   public int getOccupiedServicesQuery(){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return -1;
            
            String query = "SELECT COUNT(*) FROM Services WHERE status = 'Active' AND is_occupied = true";
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
*/
}


