package assignment4.ReservationComponent.entities;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int guestId;
    private int roomId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(int guestId, int roomId, LocalDate startDate, LocalDate endDate) {
        this.guestId = guestId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Reservation(int id, int guestId, int roomId,
                       LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() { return id; }
    public int getGuestId() { return guestId; }
    public int getRoomId() { return roomId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
}
