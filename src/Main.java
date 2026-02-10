import assignment4.components.accounting.AccountingComponent;
import assignment4.components.reservation.ReservationComponent;
import assignment4.components.room.RoomManagementComponent;
import assignment4.db.SchemaInit;
import assignment4.entities.*;
import assignment4.exceptions.InvalidDateRangeException;
import assignment4.exceptions.PaymentDeclinedException;
import assignment4.exceptions.RoomNotAvailableException;
import assignment4.patterns.ReservationDetails;
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
            System.out.println(" HOTEL RESERVATION SYSTEM ");

            Guest guest = new Guest("Olivia Rare", "rare@mail.com");
            int guestId = reservationComponent.getOrCreateGuestId(guest);

            LocalDate start = LocalDate.now().plusDays(1);
            LocalDate end = LocalDate.now().plusDays(3);

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
            double amount = accountingComponent.pricePerNight(chosen.getType(), start) * nights;

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
            System.out.println("\nReservation created: id=" + reservationId);

            int paymentId = accountingComponent.processPayment(
                    new Payment(reservationId, details.getAmount(), "PAID")
            );
            System.out.println("Payment created: id=" + paymentId);

            reservationComponent.cancelReservation(reservationId);
            System.out.println("Reservation cancelled: id=" + reservationId);

            System.out.println("\n=== DEMO FINISHED ===");

        } catch (InvalidDateRangeException | RoomNotAvailableException | PaymentDeclinedException e) {
            System.out.println("BUSINESS ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nERROR: " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
