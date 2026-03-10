package Controllers;
import java.util.List;
import Model.Customers;
import DAO.CustomerDAO;
import Views.CustomerManagement.*;

public class Customer_Controllers {
    public static CustomerDAO dao = new CustomerDAO();
    
    public List<Customers> GetAllCustomerDetails(){
        return dao.FetchAllCustomerFromDB();
    }
    
    public List<Customers> GetAllCustomerDetails(String searchfield){
        return dao.FetchAllCustomerFromDB(searchfield);
    }
    
     public void GetCustomerDetailsByID(int customer_id){
         Customers cstmr;
         cstmr = dao.GetCustomerDetailsFromDB(customer_id);
         
         if(cstmr != null){
             EditCustomerView dialog = new EditCustomerView();
             dialog.loadCustomerData(cstmr);
             dialog.setLocationRelativeTo(null);
             dialog.setVisible(true);
         }
     };
     
    public String UpdateCustomerDetails(String customerID, String customerfName, 
    String customerlName, String customerEmail, String customerPhone, String customerStatus)
    {
        if(customerID.isEmpty() || customerfName.isEmpty() || customerlName.isEmpty() || customerEmail.isEmpty() || 
        customerPhone.isEmpty() || customerStatus.isEmpty()){
            return "All fields must be filled.";
        }
        
        try{
            int cstID = Integer.parseInt(customerID);
            
            
            return dao.UpdateCustomerInDatabase(cstID, customerfName, 
            customerlName, customerEmail, customerPhone, customerStatus);
           
        }
        catch(Exception e){
            return "idk why it doesnt worl";
        }
    }
    
    public String DeleteServiceByID(int customerID){
        return dao.DeleteServiceToDatabase(customerID);
    }
}
