package Model.ReservationManagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    
    private int       reservationId;
    private Guest     guest;        // ✅ uses Guest, not Customers
    private Room      room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String    status;       // CONFIRMED | CHECKED_IN | CHECKED_OUT | CANCELLED
    private double    totalAmount;
    private String    notes;
 
    public Reservation() {}
 
    public Reservation(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.guest    = guest;
        this.room     = room;
        this.checkIn  = checkIn;
        this.checkOut = checkOut;
        this.status   = "CONFIRMED";
        this.totalAmount = calculateTotal();
    }
 
    public double calculateTotal() {
        if (checkIn != null && checkOut != null && room != null) {
            long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
            return nights * room.getPrice();
        }
        return 0;
    }
 
    public long getNights() {
        if (checkIn != null && checkOut != null)
            return ChronoUnit.DAYS.between(checkIn, checkOut);
        return 0;
    }
 
    // ── Getters & Setters ─────────────────────────────────────────────────────
    public int       getReservationId()          { return reservationId; }
    public void      setReservationId(int v)      { this.reservationId = v; }
    public Guest     getGuest()                   { return guest; }
    public void      setGuest(Guest v)            { this.guest = v; }
    public Room      getRoom()                    { return room; }
    public void      setRoom(Room v)              { this.room = v; }
    public LocalDate getCheckIn()                 { return checkIn; }
    public void      setCheckIn(LocalDate v)      { this.checkIn = v; }
    public LocalDate getCheckOut()                { return checkOut; }
    public void      setCheckOut(LocalDate v)     { this.checkOut = v; }
    public String    getStatus()                  { return status; }
    public void      setStatus(String v)          { this.status = v; }
    public double    getTotalAmount()             { return totalAmount; }
    public void      setTotalAmount(double v)     { this.totalAmount = v; }
    public String    getNotes()                   { return notes; }
    public void      setNotes(String v)           { this.notes = v; }
 
    /** Used by JComboBox display in ReservationPanel */
    @Override
    public String toString() {
        return "Res #" + reservationId + " — " + (guest != null ? guest.getFullName() : "");
    }

}
