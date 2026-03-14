package Controllers;
import java.util.List;
import Model.Services;
import Views.ServiceManagement.*;
import DAO.ServiceDAO;

abstract class ServiceControllersTemplate {
    //Returns a string for JOptionPane, adds a service to the database.
    abstract String AddServiceProcess(String service_name, 
    String service_duration, String service_price, String service_status);
    
    //Returns a list of services for table display.
    abstract List<Services> ListOfAllServices();
    
    //Returns a list of services that matches the search bar.
    abstract List<Services> ListOfAllServices(String searchfield);
    
    abstract List<Services> ListOfAllDeletedServices();
    
    abstract List<Services> ListOfAllDeletedServices(String seachfield);
    
    //Fetch a single object from the database and pass the values to a dialog.
    abstract void GetServiceDetailsByID(int serviceID);
    
    //Returns a string for JOptionPane, updates a service to the database.
    abstract String UpdateServiceDetails(String sid, String sname, 
    String sprice, String sduration, String sstatus);
    
    //Returns a string for JOptionPane, updates a service to the database.
    abstract String DeleteServiceByID(int service_id);
    
    //Restores a service by passing serviceID as argument
    abstract String RestoreServiceByID(String service_id);
    
}




public class ServiceControllers extends ServiceControllersTemplate{
    private static final ServiceDAO dao = new ServiceDAO();
    private static final Services service = new Services();
   
    @Override
    public String AddServiceProcess(String service_name, 
    String service_duration, String service_price, String service_status)
    {
        if(service_name.isEmpty() || service_duration.isEmpty() || service_price.isEmpty()){
            return "All fields are required";

        }

        try{
            double price = Double.parseDouble(service_price);
            int duration = Integer.parseInt(service_duration);

            service.setServiceName(service_name);
            service.setDurationMinutes(duration);
            service.setPrice(price);
            service.setStatus(service_status);
            
            String message = dao.AddServiceQuery(service); 
            
            
            return message;
        }
        catch(NumberFormatException e){
            return "Price and Duration must be numbers";
        }
        catch(Exception e){
            return "Failed to add service to the database";
        }
    }
    
    @Override
    public List<Services> ListOfAllServices(){
        return dao.ListOfAllServicesQuery();
    }
    
    @Override
    public List<Services> ListOfAllServices(String searchfield){
        return dao.ListOfAllServicesQuery(searchfield);
    }
    
    @Override
    public void GetServiceDetailsByID(int serviceID){
        Services s;
        s = dao.GetServiceDetailsByID(serviceID);
        
        if(s != null){
            EditServiceView dialog = new EditServiceView();
            dialog.loadServiceData(s);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }
    
    @Override
    public String UpdateServiceDetails(String sid, String sname, 
    String sprice, String sduration, String sstatus)
    {
        if(sid.trim().isEmpty() || sname.trim().isEmpty() || sprice.trim().isEmpty() 
        || sduration.trim().isEmpty() || sstatus.trim().isEmpty())
        {
            return "All fields should be filled.";
        }
        
        try{
           int serviceID = Integer.parseInt(sid);
           double price = Double.parseDouble(sprice);
           int duration = Integer.parseInt(sduration); 
            
           String message = dao.UpdateServiceQuery(serviceID,sname, price, duration, sstatus);
           
           
           return message;
        }
        catch(Exception e){
           return "Failed to update service details."; 
        }
    }
    
    @Override
    public String DeleteServiceByID(int service_id){
        return dao.DeleteServiceQuery(service_id);
    }
    
    @Override
    public List<Services> ListOfAllDeletedServices(){
        return dao.ListOfAllDeletedServicesQuery();
    }
    
    @Override
    public List<Services> ListOfAllDeletedServices(String seachfield){
        return dao.ListOfAllDeletedServicesQuery(seachfield);
    }
    
    @Override
    public String RestoreServiceByID(String service_id){
        try{
            int serviceID = Integer.parseInt(service_id);
            
            String message = dao.RestoreServiceByIDQuery(serviceID);
            
            return message;
        }
        catch(Exception e){
            return "Failed to restore service.";
        }
    }
}
