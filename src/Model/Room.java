package Model;

public class Room {
    private int    roomId;
    private String roomNumber;
    private String roomType;   // SINGLE, DOUBLE, SUITE, DELUXE
    private double price;
    private int    capacity;
    private String status;     // AVAILABLE, OCCUPIED, MAINTENANCE
    private String description;

    public Room() {}

    public Room(String roomNumber, String roomType, double price, int capacity, String description) {
        this.roomNumber  = roomNumber;
        this.roomType    = roomType;
        this.price       = price;
        this.capacity    = capacity;
        this.description = description;
        this.status      = "AVAILABLE";
    }

    // Getters & Setters
    public int    getRoomId()              { return roomId; }
    public void   setRoomId(int v)         { this.roomId = v; }
    public String getRoomNumber()          { return roomNumber; }
    public void   setRoomNumber(String v)  { this.roomNumber = v; }
    public String getRoomType()            { return roomType; }
    public void   setRoomType(String v)    { this.roomType = v; }
    public double getPrice()               { return price; }
    public void   setPrice(double v)       { this.price = v; }
    public int    getCapacity()            { return capacity; }
    public void   setCapacity(int v)       { this.capacity = v; }
    public String getStatus()              { return status; }
    public void   setStatus(String v)      { this.status = v; }
    public String getDescription()         { return description; }
    public void   setDescription(String v) { this.description = v; }

    @Override
    public String toString() {
        return String.format("Room %s (%s) - ₱%.2f/night", roomNumber, roomType, price);
    }
}
