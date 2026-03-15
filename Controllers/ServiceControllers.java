package Controllers;
import java.util.List;
import Model.Services;
import DAO.ServiceDAO;
import javax.swing.JOptionPane;

abstract class ServiceControllersTemplate {
    abstract boolean AddServiceProcess(Services service);

    abstract List<Services> ListOfAllServices();
    
    abstract List<Services> ListOfAllServices(String searchfield);
    
    abstract List<Services> ListOfAllDeletedServices();
    
    abstract List<Services> ListOfAllDeletedServices(String seachfield);
    
    abstract Services GetServiceDetailsByID(int serviceID);

    abstract boolean UpdateServiceDetails(Services service);

    abstract boolean DeleteServiceByID(int service_id);

    abstract boolean RestoreServiceByID(String service_id);
    
}




public class ServiceControllers extends ServiceControllersTemplate{
    private static final ServiceDAO dao = new ServiceDAO();
   
    @Override
    public boolean AddServiceProcess(Services service){
        if(service.getServiceName().isEmpty() || service.getDurationMinutes().isEmpty() || service.getPrice().isEmpty()){
            JOptionPane.showMessageDialog(null, "All fields should be filled.");
            return false;
        }

        try{
            double price = Double.parseDouble(service.getPrice());
            int duration = Integer.parseInt(service.getDurationMinutes());
   
            if(price <= 0 || duration <= 0){
                JOptionPane.showMessageDialog(null, "Duration and Price must be greater than 0.");
                return false;
            }
            
            if(dao.AddServiceQuery(service)){
                JOptionPane.showMessageDialog(null, "Service added successfully!");
                return true;
            }
            
            return false;
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Failed to add service.");
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to add service.");
            return false;
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
    public List<Services> ListOfAllDeletedServices(){
        return dao.ListOfAllDeletedServicesQuery();
    }
    
    @Override
    public List<Services> ListOfAllDeletedServices(String seachfield){
        return dao.ListOfAllDeletedServicesQuery(seachfield);
    }
    
    @Override
    public Services GetServiceDetailsByID(int serviceID){
        Services service = dao.GetServiceDetailsByID(serviceID);
        
        if(service == null){
            JOptionPane.showMessageDialog(null, "Failed to get service details.");
            return service;
        }
        
        return service;
    }
    
    @Override
    public boolean UpdateServiceDetails(Services service){        
        if(service.getServiceID().isEmpty() || service.getServiceName().isEmpty() 
        || service.getDurationMinutes().isEmpty() || service.getPrice().isEmpty() || service.getStatus().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields should be filled.");
            return false;
        }
        
        try{
           int serviceID = Integer.parseInt(service.getServiceID());
           double price = Double.parseDouble(service.getPrice());
           int duration = Integer.parseInt(service.getDurationMinutes()); 
           
           if(price <= 0 || duration <= 0){
               JOptionPane.showMessageDialog(null, "Duration and Price must be greater than 0.");
               return false;
           }
            
           if(dao.UpdateServiceQuery(service)){
               JOptionPane.showMessageDialog(null, "Service updated successfully!");
               return true;
           }
           
           return false;
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, "Failed to update service details.");
           return false; 
        }
    }
    
    @Override
    public boolean DeleteServiceByID(int service_id){
        if(dao.DeleteServiceQuery(service_id)){
            JOptionPane.showMessageDialog(null, "Service is deleted.");
            return true;
        }
        
        return false;
    }
    
    
    @Override
    public boolean RestoreServiceByID(String service_id){
        try{
            int serviceID = Integer.parseInt(service_id);
            if(dao.RestoreServiceByIDQuery(serviceID)){
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
