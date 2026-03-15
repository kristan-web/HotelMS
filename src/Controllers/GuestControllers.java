package Controllers;

import DAO.GuestDAO;
import Model.Guests;
import java.util.List;
import javax.swing.JOptionPane;

abstract class GuestControllersTemplate {
    public static GuestDAO dao = new GuestDAO();
 
    abstract boolean AddGuestProcess(Guests customer);

    abstract List<Guests> ListOfAllGuests();
    
    abstract List<Guests> ListOfAllGuests(String searchfield);
    
    abstract List<Guests> ListOfAllDeletedGuests();
    
    abstract List<Guests> ListOfAllDeletedGuests(String searchfield);
    
    abstract Guests GetGuestDetailsByID(int customer_id);
    
    abstract boolean UpdateGuestDetails(Guests customer);
    
    abstract boolean DeleteGuestByID(int customerID);
    
    abstract boolean RestoreGuestByID(String customerID);
}





public class GuestControllers extends GuestControllersTemplate{
    @Override
    public boolean AddGuestProcess(Guests guest){
        if(guest.getFirst_name().isEmpty() || guest.getLast_name().isEmpty() 
        || guest.getPhone().isEmpty() || guest.getEmail().isEmpty() 
        || guest.getAddress().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields should be filled.");
            return false;
        }
        
        try{
            long phone = Long.parseLong(guest.getPhone());

            if(dao.AddGuestQuery(guest)){
                JOptionPane.showMessageDialog(null, "Guest added successfully!");
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
    public List<Guests> ListOfAllGuests(){
        return dao.ListOfAllGuestsQuery();
    }
    
    @Override
    public List<Guests> ListOfAllGuests(String searchfield){
        return dao.ListOfAllGuestsQuery(searchfield);
    }
    
    @Override
    public Guests GetGuestDetailsByID(int guest_id){
        return dao.GetGuestDetailsQuery(guest_id);
    }
    
    @Override
    public boolean UpdateGuestDetails(Guests guest){
        if(guest.getFirst_name().isEmpty() || guest.getLast_name().isEmpty() 
        || guest.getPhone().isEmpty() || guest.getEmail().isEmpty() 
        || guest.getAddress().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields should be filled.");
            return false;
        }
        
        try{
            int phone = Integer.parseInt(guest.getPhone());
            int cstID = Integer.parseInt(guest.getGuest_id());
            
            if(dao.UpdateGuestQuery(guest)){
                JOptionPane.showMessageDialog(null, "Guest details updated!");
                return true;
            }
            
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to update guest details.");
            return false;
        }
    }
    
    @Override
    public boolean DeleteGuestByID(int GuestID){
        if(dao.DeleteGuestQuery(GuestID)){
            JOptionPane.showMessageDialog(null, "Guest delete success.");
            return true;
        }
        
        
        return false;
    }
    
    
    @Override
    public List<Guests> ListOfAllDeletedGuests(){
        return dao.ListOfAllDeletedGuestsQuery();
    }
    
    @Override
    public List<Guests> ListOfAllDeletedGuests(String searchfield){
        return dao.ListOfAllDeletedGuestsQuery(searchfield);
    }
    
    @Override
    public boolean RestoreGuestByID(String GuestID){
        try{
            int guest_id = Integer.parseInt(GuestID);
            
            if(dao.RestoreGuestByIDQuery(guest_id)){
                JOptionPane.showMessageDialog(null, "Guest is restored.");
                return true;
            }
            
            return false;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Failed to restore guest.");
            return false;
        }
    }
}
