package Controllers;

import DAO.CustomerDAO;
import Model.Customers;
import java.util.List;
import Views.CustomerManagement.*;

abstract class CustomerControllersTemplate {
    public static CustomerDAO dao = new CustomerDAO();
    
    //Returns a string as a message for JOptionPane, adds customer details.
    abstract String AddCustomerProcess(String first_name, String last_name, String phone_number, String email, String status);

    //Returns a list of customers for displaying in table.
    abstract List<Customers> ListOfAllCustomers();
    
    //Method overloading, returns a list of customers that matches the search bar.
    abstract List<Customers> ListOfAllCustomers(String searchfield);
    
    //Returns a list of a deleted customers for table display.
    abstract List<Customers> ListOfAllDeletedCustomers();
    
    //Method overloading, returns a list of deleted customers that matches the search bar.
    abstract List<Customers> ListOfAllDeletedCustomers(String searchfield);
    
    //Fetch single object from the datbase, pass the values to the dialog.
    abstract void GetCustomerDetailsByID(int customer_id);
    
    //Returns a string as a message for JOptionPane, updates customer details. 
    abstract String UpdateCustomerDetails(String customerID, String customerfName, 
                                          String customerlName, String customerEmail, 
                                          String customerPhone, String customerStatus);
    
    //Soft delete customers by changing is_deleted to true.
    abstract String DeleteCustomerByID(int customerID);
    
    //Returns a string as a message for JOptionPane. Restores selected row.
    abstract String RestoreCustomerByID(String customerID);
}





public class CustomerControllers extends CustomerControllersTemplate{
    @Override
    public String AddCustomerProcess(String first_name, String last_name, 
    String phone_number, String email, String status){
        if(first_name.isEmpty() || last_name.isEmpty() || phone_number.isEmpty() || email.isEmpty()){
            return "All fields should be filled.";
        }
        
        try{
            int phone = Integer.parseInt(phone_number);
            
            String message = dao.AddCustomerQuery(first_name, last_name, phone_number, email, status);
            
            
            return message;
        }
        catch(Exception e){
            return "Failed to add customer.";
        }
    }
    
    @Override
    public List<Customers> ListOfAllCustomers(){
        return dao.ListOfAllCustomersQuery();
    }
    
    @Override
    public List<Customers> ListOfAllCustomers(String searchfield){
        return dao.ListOfAllCustomersQuery(searchfield);
    }
    
    @Override
    public void GetCustomerDetailsByID(int customer_id){
        Customers cstmr;
        cstmr = dao.GetCustomerDetailsQuery(customer_id);
         
        if(cstmr != null){
            EditCustomerView dialog = new EditCustomerView();
            dialog.loadCustomerData(cstmr);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }
     
    @Override
    public String UpdateCustomerDetails(String customerID, String customerfName, 
                                          String customerlName, String customerEmail, 
                                          String customerPhone, String customerStatus)
    {
        if(customerID.isEmpty() || customerfName.isEmpty() || customerlName.isEmpty() || customerEmail.isEmpty() || 
        customerPhone.isEmpty() || customerStatus.isEmpty()){
            return "All fields must be filled.";
        }
        
        try{
            int phone = Integer.parseInt(customerPhone);
            int cstID = Integer.parseInt(customerID);
            
            return dao.UpdateCustomerQuery(cstID, customerfName, 
            customerlName, customerEmail, customerPhone, customerStatus);
           
        }
        catch(Exception e){
            return "Failed to update customer details.";
        }
    }
    
    @Override
    public String DeleteCustomerByID(int customerID){
        return dao.DeleteCustomerQuery(customerID);
    }
    
    @Override
    public List<Customers> ListOfAllDeletedCustomers(){
        return dao.ListOfAllDeletedCustomersQuery();
    }
    
    @Override
    public List<Customers> ListOfAllDeletedCustomers(String searchfield){
        return dao.ListOfAllDeletedCustomersQuery(searchfield);
    }
    
    @Override
    public String RestoreCustomerByID(String customerID){
        try{
            int customer_id = Integer.parseInt(customerID);
            
            String message = dao.RestoreCustomerByIDQuery(customer_id);
            
            return message;
        }
        catch(Exception e){
            return "Failed to restore service.";
        }
    }
}
