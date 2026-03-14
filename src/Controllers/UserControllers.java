package Controllers;
import Model.Users;
import DAO.UserDAO;
import java.util.Random;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/*
    STATUS: EDITING NOT DONE! 
    
    WRITE GOOD COMMENTS
    REFACTOR CODE
    ADD FUNCTIONS
*/

abstract class UserControllersTemplate {
    //Returns a hashed string, used for password before inserting to database.
    abstract String hashPassword(String password);
    
    //Returns TRUE if password match from the storedHash. FALSE if not.
    abstract boolean checkPassword(String inputPassword, String storedHash);
    
    //Returns a string for JOptionPane message. 
    //Validate Admin Details and Call RegisterValidateUser method.
    abstract String ValidateAdminRegistration(String firstName, String lastName, String email,
            String phone, String password, String confpass);
    
    //Returns a string for JOptionPane message.
    //Validate staff details and call RegisterValidatedStaff method.
    abstract String ValidateStaffRegistration(String firstName, String lastName, String email,
            String phone, String password, String confpass);
    
    //Returns a string for JOptionPane message.
    //Call DAO method and register the passed value from ValidateUserRegistration.
    abstract String RegisterValidatedAdmin(Users user);
    
    //Returns a string for JOptionPane message.
    //Call DAO method and register the passed value from ValidateStaffRegistration.
    abstract String RegisterValidatedStaff(Users user);
    
    //Returns a String of numbers for OTP.
    abstract String generateOTP();
    
    //Sends the OTP to the email indicated.
    abstract void sendOTP(String recipientEmail, String otp);
    
    //Returns a string for JOptionPane.
    //Compare the email-generated OTP to the userInputOTP;
    abstract String AuthenticateUserOTP(String generatedOTP, String UserInputOTP);
    
    //Returns a string for JOptionPane.
    //Call DAO to change the user password WHERE email = true;
    abstract String ChangeUserPasswordThroughEmail(String email, String password);
    
    //Returns TRUE if email is found in the database. FALSE if not.
    abstract boolean CheckAllUsersIfEmailIsPresent(String email);
    
    //Returns a string for JOptionPane message.
    //Authenticate staff account.
    abstract Users AuthenticateStaffLogin(String email, String password);
    
    //Returns a string for JOptionPane message.
    //Authenticate admin account.
    abstract Users AuthenticateAdminLogin(String email, String password);
}





//================================== DERIVED CLASS ======================================







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
    public String ValidateAdminRegistration(String firstName, String lastName, String email,
            String phone, String password, String confpass)
    {    
        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() 
        || phone.isEmpty() || password.isEmpty() || confpass.isEmpty())
        {
           return  "All fields should be filled.";
        }
        
        if(!password.equals(confpass)){
            return "Passwords do not match.";
        }
        
        try{
            long phone_num = Long.parseLong(phone);
            String hashedPass = hashPassword(password);
            Users user = new Users();
            
            user.setFirst_name(firstName);
            user.setLast_name(lastName);
            user.setPassword(hashedPass);
            user.setEmail(email);
            user.setPhone(phone_num);
            
            String message = RegisterValidatedAdmin(user);
            
            return message;
        }
        catch(Exception e){
            return "Registration failed.";
        }
    }
    
    @Override
    public String ValidateStaffRegistration(String firstName, String lastName, String email,
            String phone, String password, String confpass)
    {
        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() 
        || phone.isEmpty() || password.isEmpty() || confpass.isEmpty())
        {
           return  "All fields should be filled.";
        }
        
        if(!password.equals(confpass)){
            return "Passwords do not match.";
        }
        
        try{
            long phone_num = Long.parseLong(phone);
            String hashedPass = hashPassword(password);
            Users user = new Users();
            
            user.setFirst_name(firstName);
            user.setLast_name(lastName);
            user.setPassword(hashedPass);
            user.setEmail(email);
            user.setPhone(phone_num);
            
            String message = RegisterValidatedStaff(user);
            
            return message;
        }
        catch(Exception e){
            return "Invalid input.";
        }
    }
    
    @Override
    public String RegisterValidatedAdmin(Users user){
        return dao.RegisterAdminQuery(user);
    }
    
    @Override
    public String RegisterValidatedStaff(Users user){
        return dao.RegisterStaffQuery(user);
    }
    
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
    public String AuthenticateUserOTP(String generatedOTP, String UserInputOTP){
        if(!UserInputOTP.equals(generatedOTP)){
            return "Incorrect OTP.";
        }
        return "Correct OTP.";
    }
    
    @Override
    public String ChangeUserPasswordThroughEmail(String email, String password){
        if(password.isEmpty()){
            return "Password field is empty. Enter your new password";
        }
        
        String hashedPass = hashPassword(password);
        return dao.ChangeUserPasswordThroughEmailQuery(email, hashedPass);
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
        
        if(checkPassword(password, user.getPassword())){
            return user;
        }
        else{
            JOptionPane.showMessageDialog(null, "Incorrect username or password");
            return null;
        }
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
        
        if(checkPassword(password, user.getPassword())){
            return user;
        }
        else{
            JOptionPane.showMessageDialog(null, "Incorrect username or password");
            return null;
        }
    }
}
