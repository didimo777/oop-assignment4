import assignment4.AccauntingComponent.AccountingComponent;
import assignment4.AccauntingComponent.entities.Payment;
import assignment4.ReservationComponent.entities.Guest;
import assignment4.ReservationComponent.entities.Reservation;
import assignment4.RoomManagementComponent.entities.Room;
import assignment4.ReservationComponent.ReservationComponent;
import assignment4.RoomManagementComponent.RoomManagementComponent;
import assignment4.db.SchemaInit;
import assignment4.ReservationComponent.exceptions.InvalidDateRangeException;
import assignment4.AccauntingComponent.exceptions.PaymentDeclinedException;
import assignment4.RoomManagementComponent.exceptions.RoomNotAvailableException;
import assignment4.ReservationComponent.pattern.ReservationDetails;
import assignment4.util.SearchResult;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {

        SchemaInit.ensureSchema();

        RoomManagementComponent roomComponent = new RoomManagementComponent();
        ReservationComponent reservationComponent = new ReservationComponent();
        AccountingComponent accountingComponent = new AccountingComponent();

        try {
            System.out.println("===== HOTEL RESERVATION SYSTEM =====");

            Guest guest = new Guest("Olivia Rare", "rare@mail.com");
            int guestId = reservationComponent.getOrCreateGuestId(guest);

            LocalDate start = LocalDate.now().plusDays(1);
            LocalDate end = LocalDate.now().plusDays(3);

            System.out.println("\nBooking dates:");
            System.out.println("Check-in:  " + start);
            System.out.println("Check-out: " + end);

            SearchResult<Room> available = roomComponent.searchAvailableRooms(start, end);

            System.out.println("\nAvailable rooms:");
            available.getItems().forEach(r ->
                    System.out.println(r.getId() + " | " + r.getNumber() + " | " + r.getType())
            );

            if (available.getItems().isEmpty()) {
                System.out.println("No rooms available.");
                return;
            }

            Room chosen = available.getItems().get(0);

            long nights = ChronoUnit.DAYS.between(start, end);
            double pricePerNight = accountingComponent.pricePerNight(chosen.getType(), start);
            double amount = pricePerNight * nights;

            System.out.println("\nSelected room: " + chosen.getNumber());
            System.out.println("Room type: " + chosen.getType());
            System.out.println("Nights: " + nights);
            System.out.println("Price per night: " + pricePerNight);
            System.out.println("Total amount: " + amount);

            ReservationDetails details = ReservationDetails.builder()
                    .guestId(guestId)
                    .roomId(chosen.getId())
                    .roomType(chosen.getType())
                    .startDate(start)
                    .endDate(end)
                    .breakfast(true)
                    .amount(amount)
                    .build();

            int reservationId = reservationComponent.createReservation(
                    new Reservation(details.getGuestId(), details.getRoomId(), details.getStartDate(), details.getEndDate())
            );

            System.out.println("\nReservation created:");
            System.out.println("Reservation ID: " + reservationId);
            System.out.println("From " + start + " to " + end);

            int paymentId = accountingComponent.processPayment(
                    new Payment(reservationId, details.getAmount(), "PAID")
            );

            System.out.println("\nPayment created:");
            System.out.println("Payment ID: " + paymentId);
            System.out.println("Amount paid: " + amount);

            reservationComponent.cancelReservation(reservationId);

            System.out.println("\nReservation cancelled: id=" + reservationId);
            System.out.println("\n===== DEMO FINISHED =====");

        } catch (InvalidDateRangeException | RoomNotAvailableException | PaymentDeclinedException e) {
            System.out.println("BUSINESS ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nERROR: " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
