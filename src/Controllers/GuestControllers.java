package Controllers;

import DAO.GuestDAO;
import Model.Guests;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.swing.JOptionPane;
import Debugger.Debugger;

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
        Debugger.Debugger("I AM INSIDE ADD GUEST PROCESS");
        if(guest.getFirst_name().isEmpty() || guest.getLast_name().isEmpty() 
        || guest.getPhone().isEmpty() || guest.getEmail().isEmpty() 
        || guest.getAddress().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "All fields should be filled.");
            return false;
        }
        
        Debugger.Debugger("ALL FIELDS ARE NOT EMPTY");
        
        try{
            Debugger.Debugger("I AM INSIDE THE TRY BLOCK");
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
    public String addGuest(String firstName, String lastName, String email, String phone, String address) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty())
            return "ERROR: First name, last name, and email are required.";
        if (!email.matches("^[\\w.%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}$"))
            return "ERROR: Invalid email format.";
        try {
            if (dao.getGuestByEmail(email) != null)
                return "ERROR: A guest with this email already exists.";
            Guests g = new Guests(firstName.trim(), lastName.trim(), email.trim(), phone.trim(), address.trim());
            dao.addGuest(g);
            return "SUCCESS: Guest added successfully.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }
    public List<Guests> getAllGuests() {
        try { return dao.getAllGuests(); }
        catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, "Failed to load guests: " + e.getMessage());
            return new ArrayList<>(); 
    }
    }
    public String deleteGuest(int id) {
        try {
            dao.deleteGuest(id);
            return "SUCCESS: Guest deleted.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }
    public String updateGuest(Guests g) {
        try {
            dao.updateGuest(g);
            return "SUCCESS: Guest updated.";
        } catch (SQLException e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
