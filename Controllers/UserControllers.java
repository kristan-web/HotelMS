package Controllers;
import Model.Users;
import Model.Services;

import DAO.UserDAO;

import Database.Db_Connector;

import java.sql.*;
import java.util.Random;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;

abstract class UserControllersTemplate {
    
    abstract String hashPassword(String password);

    abstract boolean checkPassword(String inputPassword, String storedHash);
    
    abstract boolean ValidateAndRegisterAdminAccount(Users user);
    
    abstract boolean ValidateAndRegisterStaffAccount(Users user);

    abstract String generateOTP();

    abstract void sendOTP(String recipientEmail, String otp);
    
    abstract boolean AuthenticateUserOTP(String generatedOTP, String UserInputOTP);

    abstract boolean ChangeUserPasswordThroughEmail(String email, String password);

    abstract boolean CheckAllUsersIfEmailIsPresent(String email);

    abstract Users AuthenticateStaffLogin(String email, String password);

    abstract Users AuthenticateAdminLogin(String email, String password);
    
    abstract List<Users> ListOfAllUsers();
    
    abstract List<Users> ListOfAllUsers(String searchfield);
    
    abstract Users GetUserDetailsByID(int userID);
}

public class UserControllers extends UserControllersTemplate{
    private static final UserDAO dao = new UserDAO();
    
    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    @Override
    public boolean checkPassword(String inputPassword, String storedHash) {
        return BCrypt.checkpw(inputPassword, storedHash);
    }
    
    @Override
    public boolean ValidateAndRegisterAdminAccount(Users user){
        if(user.getFirst_name().isEmpty() || user.getLast_name().isEmpty()
        || user.getPassword().isEmpty() || user.getEmail().isEmpty() || user.getPhone().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields should be filled.");
            return false;
        }
        
        if(!user.getPassword().equals(user.getConfpass())){
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return false;
        }
        
        try{
            long number = Long.parseLong(user.getPhone());
            
            String hashedpass = hashPassword(user.getPassword());
            user.setPassword(hashedpass);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Contact number must be numerical.");
            return false;
        }
        
        
        if(dao.RegisterAdminQuery(user)){
            JOptionPane.showMessageDialog(null, "Registration success!");
            return true;
        }
        
        return false;
    }

    @Override
    public boolean ValidateAndRegisterStaffAccount(Users user){
        if(user.getFirst_name().isEmpty() || user.getLast_name().isEmpty()
        || user.getPassword().isEmpty() || user.getEmail().isEmpty() || user.getPhone().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields should be filled.");
            return false;
        }
        
        if(!user.getPassword().equals(user.getConfpass())){
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return false;
        }
        
        try{
            long number = Long.parseLong(user.getPhone());
            
            String hashedpass = hashPassword(user.getPassword());
            user.setPassword(hashedpass);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Contact number must be numerical.");
            return false;
        }
        
        if(dao.RegisterStaffQuery(user)){
            JOptionPane.showMessageDialog(null, "Registration success!");
            return true;
        }
        
        return false;
    }
    
    @Override
    public String generateOTP() {
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);
        return String.valueOf(otp);
    }

    @Override
    public void sendOTP(String recipientEmail, String otp) {
        final String senderEmail = "graalhost10@gmail.com";
        final String senderPassword = "evcwojmyapekxcrq";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipientEmail)
            );

            message.setSubject("Your OTP Code");
            message.setText("Your verification OTP is: " + otp);

            Transport.send(message);

            System.out.println("OTP Sent!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean AuthenticateUserOTP(String generatedOTP, String UserInputOTP){
        if(!UserInputOTP.equals(generatedOTP)){
            JOptionPane.showMessageDialog(null, "Incorrect OTP");
            return false;
        }
        JOptionPane.showMessageDialog(null, "OTP is correct");
        return true;
    }
    
    @Override
    public boolean ChangeUserPasswordThroughEmail(String email, String password){
        if(password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Password is empty.");
            return false;
        }
        String hashedPass = hashPassword(password);
        
        if(dao.ChangeUserPasswordThroughEmailQuery(email, hashedPass)){
            JOptionPane.showMessageDialog(null, "Change password success!");
            return true;
        }
        
        return false;
    }
    
    @Override
    public boolean CheckAllUsersIfEmailIsPresent(String email){
        if(email.isEmpty()){
            return false;
        }
        
        return dao.CheckAllUsersIfEmailIsPresentQuery(email);
    }
    
    @Override
    public Users AuthenticateStaffLogin(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter username and password.");
            return null;
        }
        
        Users user = dao.AuthenticateStaffLoginQuery(email);
        
        if(user == null){
            JOptionPane.showMessageDialog(null, "Account does not exist.");
            return null;
        }
        
        if(!checkPassword(password, user.getPassword())){
            JOptionPane.showMessageDialog(null, "Incorrect email or password.");
            return null;
        }
        
        return user;
    }
    
    
    public Users AuthenticateAdminLogin(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter username and password.");
            return null;
        }
        
        Users user = dao.AuthenticateAdminLoginQuery(email);
        
        if(user == null){
            JOptionPane.showMessageDialog(null, "Account does not exist.");
            return null;
        }
        
        if(!checkPassword(password, user.getPassword())){
            JOptionPane.showMessageDialog(null, "Incorrect email or password.");
            return null;
        }
        
        return user;
    }
    
    public List<Users> ListOfAllUsers(){
        return dao.ListOfAllUsersQuery();
    }
    
    public List<Users> ListOfAllUsers(String searchfield){
        return dao.ListOfAllUsersQuery(searchfield);
    }
    
    public Users GetUserDetailsByID(int userID){
        Users user = dao.GetUserDetailsByIDQuery(userID);
        
        if(user == null){
            JOptionPane.showMessageDialog(null, "Failed to get user details.");
            return null;
        }
        
        return user;
    }
    
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
}
