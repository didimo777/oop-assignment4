package assignment4.AccauntingComponent.entities;

public class Payment {
    private int id;
    private int reservationId;
    private double amount;
    private String status;

    public Payment(int reservationId, double amount, String status) {
        this.reservationId = reservationId;
        this.amount = amount;
        this.status = status;
    }

    public int getReservationId() { return reservationId; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
}
