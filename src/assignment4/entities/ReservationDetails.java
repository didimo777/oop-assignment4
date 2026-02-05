package assignment4.entities;

public class ReservationDetails {

    private final Room room;
    private final int nights;
    private final String paymentMethod;

    private ReservationDetails(Builder builder) {
        this.room = builder.room;
        this.nights = builder.nights;
        this.paymentMethod = builder.paymentMethod;
    }

    public Room getRoom() { return room; }
    public int getNights() { return nights; }
    public String getPaymentMethod() { return paymentMethod; }

    public static class Builder {
        private Room room;
        private int nights;
        private String paymentMethod;

        public Builder room(Room room) {
            this.room = room;
            return this;
        }

        public Builder nights(int nights) {
            this.nights = nights;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public ReservationDetails build() {
            return new ReservationDetails(this);
        }
    }
}
