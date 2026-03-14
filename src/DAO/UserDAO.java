package DAO;
import Model.Users;
import Database.Db_Connector;
import java.sql.*;

abstract class UserDAOTemplate {
    //Returns a string for JOptionPane message.
    //Insert model details to users table in database.
    abstract String RegisterAdminQuery(Users user);
    
    //Returns a string for JOptionPane message.
    //Insert model details to users table in database.
    abstract String RegisterStaffQuery(Users user);
    
    //
    abstract String ChangeUserPasswordThroughEmailQuery(String email, String hashedPass);
    
    //
    abstract boolean CheckAllUsersIfEmailIsPresentQuery(String email);
}

public class UserDAO extends UserDAOTemplate{
    @Override
    public String RegisterAdminQuery(Users user){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return "Failed to connect to database";
            
            String query = "INSERT INTO USERS (first_name, last_name, password, phone, email, role)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, user.getFirst_name());
                pst.setString(2, user.getLast_name());
                pst.setString(3, user.getPassword());
                pst.setLong(4, user.getPhone());
                pst.setString(5, user.getEmail());
                pst.setString(6, "Admin");
                
                int ReturnedRow = pst.executeUpdate();
                
                if(ReturnedRow > 0) return "Admin registration success.";
            }
            catch(Exception e){
                return "Registration failed. Email is already taken.";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return "Registration failed.";
        }
        catch(Exception e){
            return "Registration failed.";
        }
        return "Registration failed";
    }
    
    @Override
    public String RegisterStaffQuery(Users user){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return "Failed to connect to database";
            
            String query = "INSERT INTO USERS (first_name, last_name, password, phone, email, role)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, user.getFirst_name());
                pst.setString(2, user.getLast_name());
                pst.setString(3, user.getPassword());
                pst.setLong(4, user.getPhone());
                pst.setString(5, user.getEmail());
                pst.setString(6, "Staff");
                
                int ReturnedRow = pst.executeUpdate();
                
                if(ReturnedRow > 0) return "Staff registration success.";
            }
            catch(Exception e){
                return "Registration failed. Email is already taken.";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return "Registration failed.";
        }
        catch(Exception e){
            return "Registration failed.";
        }
        return "Registration failed";
    }
    
    @Override
    public String ChangeUserPasswordThroughEmailQuery(String email, String hashedPass){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return "Failed to connect to database";
            
            String query = "UPDATE USERS SET password = ? WHERE email = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, hashedPass);
                pst.setString(2, email);
                
                int affectedRows = pst.executeUpdate();
                
                if(affectedRows > 0){
                    return "Password changed successfully.";
                }
            }
            catch(Exception e){
                return "Change password failed";
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
            return "Failed to connect to database.";
        }
        return "Failed to connect to database.";
    }
    
    @Override
    public boolean CheckAllUsersIfEmailIsPresentQuery(String email){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return false;
            
            String query = "SELECT email FROM Users WHERE email = ? AND role = 'Staff'"; 
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, email);
                
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    return true;
                }
            }
            catch(Exception e){
                return false;
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
