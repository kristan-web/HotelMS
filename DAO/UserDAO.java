package DAO;
import Model.Users;
import Database.Db_Connector;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

abstract class UserDAOTemplate {
    
    abstract boolean RegisterAdminQuery(Users user);
    
    abstract boolean RegisterStaffQuery(Users user);
   
    abstract boolean ChangeUserPasswordThroughEmailQuery(String email, String hashedPass);

    abstract boolean CheckAllUsersIfEmailIsPresentQuery(String email);

    abstract Users AuthenticateStaffLoginQuery(String email);

    abstract Users AuthenticateAdminLoginQuery(String email);  
    
    abstract boolean CheckIfDatabaseHasAdminQuery();
    
    abstract List<Users> ListOfAllUsersQuery();
    
    abstract List<Users> ListOfAllUsersQuery(String searchfield);
    
    abstract Users GetUserDetailsByIDQuery(int userID);
}





public class UserDAO extends UserDAOTemplate{
    @Override
    public boolean RegisterAdminQuery(Users user){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null){
                JOptionPane.showMessageDialog(null, "Failed to connect to database");
                return false;
            }
            
            String query = "INSERT INTO USERS (first_name, last_name, password, phone, email, role)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, user.getFirst_name());
                pst.setString(2, user.getLast_name());
                pst.setString(3, user.getPassword());
                pst.setString(4, user.getPhone());
                pst.setString(5, user.getEmail());
                pst.setString(6, "Admin");
                
                int ReturnedRow = pst.executeUpdate();
                
                if(ReturnedRow > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Registration failed. Email is already taken.");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Registration failed. Email is already taken.");
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Registration failed.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Registration failed.");
        return false;
    }
    
    @Override
    public boolean RegisterStaffQuery(Users user){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null){
                JOptionPane.showMessageDialog(null, "Failed to connect to database");
                return false;
            }
            
            String query = "INSERT INTO USERS (first_name, last_name, password, phone, email, role)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, user.getFirst_name());
                pst.setString(2, user.getLast_name());
                pst.setString(3, user.getPassword());
                pst.setString(4, user.getPhone());
                pst.setString(5, user.getEmail());
                pst.setString(6, "Staff");
                
                int ReturnedRow = pst.executeUpdate();
                
                if(ReturnedRow > 0) return true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Registration failed. Email is already taken.");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Registration failed. Email is already taken.");
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Registration failed.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Registration failed.");
        return false;
    }
    
    @Override
    public boolean ChangeUserPasswordThroughEmailQuery(String email, String hashedPass){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null){
                JOptionPane.showMessageDialog(null, "Failed to connect to database");
                return false;
            }
            
            String query = "UPDATE USERS SET password = ? WHERE email = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, hashedPass);
                pst.setString(2, email);
                
                int affectedRows = pst.executeUpdate();
                
                if(affectedRows > 0){
                    return true;
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Change password failed.");
                return false;
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Change password failed.");
            return false;
        }
        JOptionPane.showMessageDialog(null, "Change password failed.");
        return false;
    }
    
    @Override
    public boolean CheckAllUsersIfEmailIsPresentQuery(String email){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null){
                JOptionPane.showMessageDialog(null, "Can't connect to the database.");
                return false;
            }
            
            String query = "SELECT email FROM Users WHERE email = ?"; 
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, email);
                
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){
                    return true;
                }
                
                JOptionPane.showMessageDialog(null, "Email is not registered.");
                return false;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to search database.");
                return false;
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to search database.");
                return false;
        }
    }
    
    @Override
    public Users AuthenticateStaffLoginQuery(String email){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            
            String query = "SELECT * FROM Users WHERE email = ? AND role = 'Staff'"; 
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, email);
                
                ResultSet rs = pst.executeQuery();
                Users user = new Users();
                
                while(rs.next()){
                    user.setUser_id(rs.getString("user_id"));
                    user.setFirst_name(rs.getString("first_name"));
                    user.setLast_name(rs.getString("last_name"));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    
                    return user;
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
        return null;
    }
    
    @Override
    public Users AuthenticateAdminLoginQuery(String email){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            
            String query = "SELECT * FROM Users WHERE email = ? AND role = 'Admin'"; 
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, email);
                
                ResultSet rs = pst.executeQuery();
                Users user = new Users();
                
                while(rs.next()){
                    user.setUser_id(rs.getString("user_id"));
                    user.setFirst_name(rs.getString("first_name"));
                    user.setLast_name(rs.getString("last_name"));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    
                    return user;
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
        return null;
    }
    
    public boolean CheckIfDatabaseHasAdminQuery(){
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null){
                JOptionPane.showMessageDialog(null, "Can't connect to the database.");
                return false;
            }
            
            String query = "SELECT * FROM Users WHERE role = 'Admin'";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){
                    return true;
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Query failed to perform");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Query failed to perform");
            return false;
        }
    return false;
    }
    
    public List<Users> ListOfAllUsersQuery(){
        List<Users> usersList = new ArrayList<>();
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if (dbconn == null) return null;
            
            String query = "SELECT * from Users WHERE is_deleted = false";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Users user = new Users();
                    
                    user.setUser_id(rs.getString("user_id"));
                    user.setFirst_name(rs.getString("first_name"));
                    user.setLast_name(rs.getString("last_name"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    
                    usersList.add(user);
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
        
        return usersList;
    }
    
    public List<Users> ListOfAllUsersQuery(String searchfield){
        List<Users> usersList = new ArrayList<>();
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if (dbconn == null) return null;
            
            String query = "SELECT * from Users WHERE first_name LIKE ? AND is_deleted = false";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setString(1, "%" + searchfield + "%");
                
                ResultSet rs = pst.executeQuery();
                
                while(rs.next()){
                    Users user = new Users();
                    
                    user.setUser_id(rs.getString("user_id"));
                    user.setFirst_name(rs.getString("first_name"));
                    user.setLast_name(rs.getString("last_name"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    
                    usersList.add(user);
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
        
        return usersList;
    }
    
    public Users GetUserDetailsByIDQuery(int userID){
        Users user = null;
        
        try(Connection dbconn = Db_Connector.getCOnnection()){
            if(dbconn == null) return null;
            
            String query = "SELECT * FROM Users WHERE user_id = ?";
            try(PreparedStatement pst = dbconn.prepareStatement(query)){
                pst.setInt(1, userID);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    user = new Users();
                    
                    user.setUser_id(rs.getString("user_id"));
                    user.setFirst_name("first_name");
                    user.setLast_name("last_name");
                    user.setPassword("password");
                    user.setEmail("email");
                    user.setPhone("phone");
                    user.setRole("role");
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Failed to get user details.");
                return null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to get user details.");
            return null;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to get user details.");
            return null;
        }
        
        return user;
    }
}
