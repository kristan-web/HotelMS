package Controllers;

import DAO.CustomerDAO;
import Model.Customers;
import java.util.List;
import javax.swing.JOptionPane;

abstract class CustomerControllersTemplate {
    public static CustomerDAO dao = new CustomerDAO();
 
    abstract boolean AddCustomerProcess(Customers customer);

    abstract List<Customers> ListOfAllCustomers();
    
    abstract List<Customers> ListOfAllCustomers(String searchfield);
    
    abstract List<Customers> ListOfAllDeletedCustomers();
    
    abstract List<Customers> ListOfAllDeletedCustomers(String searchfield);
    
    abstract Customers GetCustomerDetailsByID(int customer_id);
    
    abstract boolean UpdateCustomerDetails(Customers customer);
    
    abstract boolean DeleteCustomerByID(int customerID);
    
    abstract boolean RestoreCustomerByID(String customerID);
}





public class CustomerControllers extends CustomerControllersTemplate{
    @Override
    public boolean AddCustomerProcess(Customers customer){
        if(customer.getFirst_name().isEmpty() || customer.getLast_name().isEmpty() 
        || customer.getPhone_number().isEmpty() || customer.getEmail().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields should be filled.");
            return false;
        }
        
        try{
            int phone = Integer.parseInt(customer.getPhone_number());

            if(dao.AddCustomerQuery(customer)){
                JOptionPane.showMessageDialog(null, "Customer added successfully!");
                return true;
            }
            
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Contact number must be numerical.");
            return false;
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
    public Customers GetCustomerDetailsByID(int customer_id){
        return dao.GetCustomerDetailsQuery(customer_id);
    }
    
    @Override
    public boolean UpdateCustomerDetails(Customers customer){
        if(customer.getCustomer_id().isEmpty() || customer.getFirst_name().isEmpty() || customer.getLast_name().isEmpty() 
        || customer.getEmail().isEmpty() || customer.getPhone_number().isEmpty() || customer.getStatus().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields should be filled.");
            return false;
        }
        
        try{
            int phone = Integer.parseInt(customer.getPhone_number());
            int cstID = Integer.parseInt(customer.getCustomer_id());
            
            if(dao.UpdateCustomerQuery(customer)){
                JOptionPane.showMessageDialog(null, "Customer details updated!");
                return true;
            }
            
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to update customer details.");
            return false;
        }
    }
    
    @Override
    public boolean DeleteCustomerByID(int customerID){
        if(dao.DeleteCustomerQuery(customerID)){
            JOptionPane.showMessageDialog(null, "Customer delete success.");
            return true;
        }
        
        
        return false;
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
    public boolean RestoreCustomerByID(String customerID){
        try{
            int customer_id = Integer.parseInt(customerID);
            
            if(dao.RestoreCustomerByIDQuery(customer_id)){
                JOptionPane.showMessageDialog(null, "Customer is restored.");
                return true;
            }
            
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to restore customer.");
            return false;
        }
    }
}
