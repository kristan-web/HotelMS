package Model;

import java.time.LocalDateTime;

public class ServiceBooking {

    private int           bookingId;
    private int           guestId;
    private String        guestName;    // joined from guests table for display
    private int           serviceId;
    private String        serviceName;  // joined from services table for display
    private LocalDateTime scheduledAt;
    private int           duration;
    private double        total;
    private String        status;

    public ServiceBooking() {}

    // ── Getters ───────────────────────────────────────────────────────────────
    public int           getBookingId()   { return bookingId; }
    public int           getGuestId()     { return guestId; }
    public String        getGuestName()   { return guestName; }
    public int           getServiceId()   { return serviceId; }
    public String        getServiceName() { return serviceName; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public int           getDuration()    { return duration; }
    public double        getTotal()       { return total; }
    public String        getStatus()      { return status; }

    // ── Setters ───────────────────────────────────────────────────────────────
    public void setBookingId(int bookingId)               { this.bookingId   = bookingId; }
    public void setGuestId(int guestId)                   { this.guestId     = guestId; }
    public void setGuestName(String guestName)            { this.guestName   = guestName; }
    public void setServiceId(int serviceId)               { this.serviceId   = serviceId; }
    public void setServiceName(String serviceName)        { this.serviceName = serviceName; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
    public void setDuration(int duration)                 { this.duration    = duration; }
    public void setTotal(double total)                    { this.total       = total; }
    public void setStatus(String status)                  { this.status      = status; }
}
