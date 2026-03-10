package Controllers;
import Model.*;
import DAO.*;
import javax.swing.JOptionPane;
import java.util.List;
import Views.ServiceManagement.EditServiceView;

public class Service_Controllers {
    
    public ServiceDAO dao;
    public Service_Controllers(){
        dao = new ServiceDAO();
    }
    public boolean AddServiceToDatabase(String service_name, String service_duration, String service_price, String service_status){
        Services service = new Services();
        /*
            INSTANTIATE SERVICE MODEL
            CHECK IF ONE OF THE FIELD IS EMPTY
            IF YES, RETURN FALSE
            IF NONE,
                PARSE THE INCORRECT DATATYPE
                PASS THE PARAMETER VALUES TO SERVICE MODEL ATTRIBUTES
                CALL ADD SERVICE QUERY FROM DAO AND THEN RETURN TRUE OR FALSE
        */
        
        if(service_name.isEmpty() || service_duration.isEmpty() || service_price.isEmpty()){
            JOptionPane.showMessageDialog(null, "All fields are required.");
            return false; 
        }
        
        try{
            double price = Double.parseDouble(service_price);
            int duration = Integer.parseInt(service_duration);
            
            service.setServiceName(service_name);
            service.setDurationMinutes(duration);
            service.setPrice(price);
            service.setStatus(service_status);
            
            return dao.AddServiceQuery(service);
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Price and Duration must be numbers.");
            return false;
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, "Invalid input.");
            return false;
        }
    }
    
    public List<Services> fetchAllServices(){
        // CALLS DAO FUNCTION
        return dao.getAllServices();
    }
    
    public List<Services>DynamicSearchTable(String searchfield){
        return dao.getAllServices(searchfield);
    }
    
    public void GetServiceDetailsByID(int serviceID){
        /*
            CALLS DAO FUNCTION
            PASS THE SERVICE ID PARAMETER AS ARGUMENT
            CREATE VARIABLE THAT HOLDS THE RETURN TYPE OF DAO FUNCTION
            CALL THE EDIT VIEW FRAME
            PASS THE VALUE INSIDE THE VARIABLE TO THE TEXT FIELDS
        */
        
        Services s = null;
        s = dao.GetServiceDetailsByID(serviceID);
        
        if(s != null){
            EditServiceView dialog = new EditServiceView();
            dialog.loadServiceData(s);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            
        }
    }
    
    public boolean UpdateSelectedService(String sid, String sname, String sprice, String sduration, String sstatus){
        if(sid.trim().isEmpty() || sname.trim().isEmpty() || sprice.trim().isEmpty() 
        || sduration.trim().isEmpty() || sstatus.trim().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields should be filled.");
            return false;
        }
        
        try{
           int serviceID = Integer.parseInt(sid);
           double price = Double.parseDouble(sprice);
           int duration = Integer.parseInt(sduration); 
            
           return dao.UpdateServiceToDatabase(serviceID, sname, price, duration, sstatus);
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, "Price and Duration must be numbers.");
           return false; 
        }
    }
    
    public boolean DeleteServiceByID(int service_id){
        return dao.DeleteServiceToDatabase(service_id);
    }
}
