package assignment4.patterns;

import assignment4.entities.RoomType;

import java.time.LocalDate;

public final class ReservationDetails {
    private final int guestId;
    private final int roomId;
    private final RoomType roomType;
    private final LocalDate startDate;
    private final LocalDate endDate;

    // options
    private final boolean breakfast;

    // payment info
    private final double amount;

    private ReservationDetails(Builder b) {
        this.guestId = b.guestId;
        this.roomId = b.roomId;
        this.roomType = b.roomType;
        this.startDate = b.startDate;
        this.endDate = b.endDate;
        this.breakfast = b.breakfast;
        this.amount = b.amount;
    }

    public int getGuestId() { return guestId; }
    public int getRoomId() { return roomId; }
    public RoomType getRoomType() { return roomType; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public boolean isBreakfast() { return breakfast; }
    public double getAmount() { return amount; }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private int guestId;
        private int roomId;
        private RoomType roomType;
        private LocalDate startDate;
        private LocalDate endDate;
        private boolean breakfast;
        private double amount;

        public Builder guestId(int guestId) { this.guestId = guestId; return this; }
        public Builder roomId(int roomId) { this.roomId = roomId; return this; }
        public Builder roomType(RoomType roomType) { this.roomType = roomType; return this; }
        public Builder startDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public Builder endDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public Builder breakfast(boolean breakfast) { this.breakfast = breakfast; return this; }
        public Builder amount(double amount) { this.amount = amount; return this; }

        public ReservationDetails build() { return new ReservationDetails(this); }
    }
}
