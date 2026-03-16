package Controllers;
import Model.Users;
import DAO.UserDAO;
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
    
    abstract List<Users> ListOfAllDeletedUsers();
    
    abstract List<Users> ListOfAllDeletedUsers(String searchfield);
    
    abstract Users GetUserDetailsByID(int userID);
    
    abstract boolean UpdateUserByID(Users user);
    
    abstract boolean DeleteUserByID(int userID);
    
    abstract boolean RestoreUserByID(String userID);
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
        
        if(CheckAllUsersIfEmailIsPresent(user.getEmail())){
            JOptionPane.showMessageDialog(null, "Email is already taken.");
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
        
        if(CheckAllUsersIfEmailIsPresent(user.getEmail())){
            JOptionPane.showMessageDialog(null, "Email is already taken.");
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

            JOptionPane.showMessageDialog(null, "OTP has been sent!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please enter registered email to receive OTP.");
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
    
    public List<Users> ListOfAllDeletedUsers(){
        return dao.ListOfAllDeletedUsersQuery();
    }
    
    public List<Users> ListOfAllDeletedUsers(String searchfield){
        return dao.ListOfAllDeletedUsersQuery(searchfield);
    }
    
    public Users GetUserDetailsByID(int userID){
        Users user = dao.GetUserDetailsByIDQuery(userID);
        
        if(user == null){
            JOptionPane.showMessageDialog(null, "Failed to get user details.");
            return null;
        }
        
        return user;
    }
    
    public boolean UpdateUserByID(Users user){
    
        if(user.getFirst_name().isEmpty()|| user.getLast_name().isEmpty() || 
        user.getEmail().isEmpty() || user.getPhone().isEmpty() || user.getRole().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields should be filled.");
            return false;
        }
        
        try{
           int user_ID = Integer.parseInt(user.getUser_id());
           long phone = Long.parseLong(user.getPhone());
            
           if(dao.UpdateUserQuery(user)){
               JOptionPane.showMessageDialog(null, "User updated successfully!");
               return true;
           }
           
           return false;
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, "Failed to update user details.");
           return false; 
        }
    }
    
    public boolean DeleteUserByID(int userID){
        if(dao.DeleteUserByIDQuery(userID)){
            JOptionPane.showMessageDialog(null, "User is deleted.");
            return true;
        }
        
        return false;
    }
    
    public boolean RestoreUserByID(String userID){
        try{
            int user_id = Integer.parseInt(userID);
            if(dao.RestoreServiceByIDQuery(user_id)){
                JOptionPane.showMessageDialog(null, "Service is restored.");
                return true;
            }
            
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to restore service.");
            return false;
        }
    }
}
