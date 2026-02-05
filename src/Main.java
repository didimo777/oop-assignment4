import assignment4.db.SchemaInit;
import assignment4.entities.*;
import assignment4.exceptions.*;
import assignment4.patterns.PricingPolicy;
import assignment4.patterns.ReservationDetails;
import assignment4.services.*;
import assignment4.util.SearchResult;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        SchemaInit.ensureSchema();

        GuestService guestService = new GuestService();
        ReservationService reservationService = new ReservationService();
        PaymentService paymentService = new PaymentService();
        RoomAvailabilityService roomService = new RoomAvailabilityService();

        try {
            System.out.println(" HOTEL RESERVATION SYSTEM ");

            Guest guest = new Guest("Olivia Rare", "rare@mail.com");
            int guestId;
            try {
                guestId = guestService.registerGuest(guest); // сделай чтобы возвращал id (см ниже)
            } catch (RuntimeException e) {
                guestId = guestService.findGuestIdByEmail(guest.getEmail()); // сделай метод (см ниже)
            }

            LocalDate start = LocalDate.now().plusDays(1);
            LocalDate end = LocalDate.now().plusDays(3);

            SearchResult<Room> available = roomService.searchAvailableRooms(start, end);

            System.out.println("\nAvailable rooms:");
            available.getItems().forEach(r ->
                    System.out.println(r.getId() + " | " + r.getNumber() + " | " + r.getType())
            );

            if (available.getItems().isEmpty()) {
                System.out.println("No rooms available.");
                return;
            }

            Room chosen = available.getItems().get(0);

            double pricePerNight = PricingPolicy.getInstance().pricePerNight(chosen.getType(), start);
            long nights = java.time.temporal.ChronoUnit.DAYS.between(start, end);
            double amount = pricePerNight * nights;
            ReservationDetails details = ReservationDetails.builder()
                    .guestId(guestId)
                    .roomId(chosen.getId())
                    .roomType(chosen.getType())
                    .startDate(start)
                    .endDate(end)
                    .breakfast(true)
                    .amount(amount)
                    .build();


            int reservationId = reservationService.createReservation(
                    new Reservation(details.getGuestId(), details.getRoomId(), details.getStartDate(), details.getEndDate())
            );
            System.out.println("\nReservation created: id=" + reservationId);


            int paymentId = paymentService.processPayment(new Payment(reservationId, details.getAmount(), "PAID"));
            System.out.println("Payment created: id=" + paymentId);


            reservationService.cancelReservation(reservationId);
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
