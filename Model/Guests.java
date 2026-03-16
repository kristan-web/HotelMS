package Model;
public class Guests {
    //ATTRIBUTES
    private String guest_id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String address;

    public Guests() {}

    public Guests(String firstName, String lastName, String email, String phone, String address) {
        this.first_name = firstName;
        this.last_name  = lastName;
        this.email     = email;
        this.phone     = phone;
        this.address   = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(String guest_id) {
        this.guest_id = guest_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String toString(){
        return first_name + " " + last_name;
    }

    public String getFullName(){
        return first_name + " " + last_name;
    }
}
