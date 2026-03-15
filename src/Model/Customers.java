package Model;
public class Customers {
    //ATTRIBUTES
    private String customer_id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;
    private String status;
    private String address;
    //SETTERS AND GETTERS
    
    public Customers() {}

    public Customers(String firstName, String lastName, String email, String phone, String address) {
        this.first_name = firstName;
        this.last_name  = lastName;
        this.email     = email;
        this.phone_number     = phone;
        this.address   = address;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCustomer_address() {
        return address;
    }
    public String setCustomer_address(String address) {
        return this.address = address;
    }
    public String getFullName() { return first_name + " " + last_name; }

    @Override
    public String toString() { return getFullName(); }
}
