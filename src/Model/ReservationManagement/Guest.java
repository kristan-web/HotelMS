package Model.ReservationManagement;

public class Guest {
    private int    guestId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public Guest() {}

    public Guest(String firstName, String lastName, String email, String phone, String address) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
        this.phone     = phone;
        this.address   = address;
    }

    // Getters & Setters
    public int    getGuestId()              { return guestId; }
    public void   setGuestId(int guestId)   { this.guestId = guestId; }
    public String getFirstName()            { return firstName; }
    public void   setFirstName(String v)    { this.firstName = v; }
    public String getLastName()             { return lastName; }
    public void   setLastName(String v)     { this.lastName = v; }
    public String getEmail()                { return email; }
    public void   setEmail(String v)        { this.email = v; }
    public String getPhone()                { return phone; }
    public void   setPhone(String v)        { this.phone = v; }
    public String getAddress()              { return address; }
    public void   setAddress(String v)      { this.address = v; }

    public String getFullName() { return firstName + " " + lastName; }

    @Override
    public String toString() { return getFullName(); } // used in JComboBox
}
