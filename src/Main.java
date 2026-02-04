import assignment4.entities.*;
import assignment4.services.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        GuestService guestService = new GuestService();
        ReservationService reservationService = new ReservationService();
        PaymentService paymentService = new PaymentService();
        RoomAvailabilityService roomService = new RoomAvailabilityService();

        try {
            System.out.println(" HOTEL RESERVATION SYSTEM ");

            // 1. Register guest (will NOT duplicate)
            Guest guest = new Guest("Olivia Rare", "rare@mail.com");
            try {
                guestService.registerGuest(guest);
                System.out.println("Guest registered");
            } catch (RuntimeException e) {
                System.out.println("Guest already exists, skipping creation");
            }


            System.out.println("\nAvailable rooms:");
            roomService.getAllRooms()
                    .forEach(r ->
                            System.out.println("Room #" + r.getNumber())
                    );

            int guestId = 1;
            int roomId = 1;

            // 3. Create reservation
            Reservation reservation = new Reservation(
                    guestId,
                    roomId,
                    LocalDate.now().plusDays(1),
                    LocalDate.now().plusDays(3)
            );

            reservationService.createReservation(reservation);
            System.out.println("\nReservation created successfully");


            System.out.println("\n=== DEMO FINISHED ===");

        } catch (Exception e) {
            System.out.println("\nERROR:");
            System.out.println(e.getMessage());
        }
    }
}

