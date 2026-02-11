package assignment4.ReservationComponent;

import assignment4.ReservationComponent.repositories.GuestRepository;
import assignment4.ReservationComponent.repositories.GuestRepositoryImpl;
import assignment4.ReservationComponent.repositories.ReservationRepository;
import assignment4.ReservationComponent.repositories.ReservationRepositoryImpl;
import assignment4.RoomManagementComponent.repositories.RoomRepository;
import assignment4.RoomManagementComponent.repositories.RoomRepositoryImpl;
import assignment4.ReservationComponent.entities.Guest;
import assignment4.ReservationComponent.entities.Reservation;
import assignment4.ReservationComponent.exceptions.InvalidDateRangeException;
import assignment4.RoomManagementComponent.exceptions.RoomNotAvailableException;
import assignment4.ReservationComponent.services.GuestService;
import assignment4.ReservationComponent.services.ReservationService;

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
