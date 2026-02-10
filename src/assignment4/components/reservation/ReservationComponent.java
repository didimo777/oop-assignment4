package assignment4.components.reservation;

import assignment4.entities.Guest;
import assignment4.entities.Reservation;
import assignment4.exceptions.InvalidDateRangeException;
import assignment4.exceptions.RoomNotAvailableException;
import assignment4.repositories.*;
import assignment4.services.GuestService;
import assignment4.services.ReservationService;

public class ReservationComponent {

    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    private final GuestService guestService;
    private final ReservationService reservationService;

    public ReservationComponent() {
        this(new GuestRepositoryImpl(), new ReservationRepositoryImpl(), new RoomRepositoryImpl());
    }

    public ReservationComponent(GuestRepository guestRepository,
                                ReservationRepository reservationRepository,
                                RoomRepository roomRepository) {
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;

        this.guestService = new GuestService(guestRepository);
        this.reservationService = new ReservationService(reservationRepository, guestRepository, roomRepository);
    }

    public int getOrCreateGuestId(Guest guest) {
        try {
            return guestService.registerGuest(guest);
        } catch (RuntimeException e) {
            return guestService.findGuestIdByEmail(guest.getEmail());
        }
    }

    public int createReservation(Reservation reservation)
            throws InvalidDateRangeException, RoomNotAvailableException {
        return reservationService.createReservation(reservation);
    }

    public void cancelReservation(int reservationId) {
        reservationService.cancelReservation(reservationId);
    }
}
