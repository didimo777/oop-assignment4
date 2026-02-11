package assignment4.ReservationComponent.services;

import assignment4.ReservationComponent.repositories.GuestRepository;
import assignment4.ReservationComponent.repositories.GuestRepositoryImpl;
import assignment4.ReservationComponent.repositories.ReservationRepository;
import assignment4.ReservationComponent.repositories.ReservationRepositoryImpl;
import assignment4.RoomManagementComponent.repositories.RoomRepository;
import assignment4.RoomManagementComponent.repositories.RoomRepositoryImpl;
import assignment4.ReservationComponent.entities.Reservation;
import assignment4.ReservationComponent.exceptions.InvalidDateRangeException;
import assignment4.RoomManagementComponent.exceptions.RoomNotAvailableException;

public class ReservationService {

    private final ReservationRepository reservations;
    private final GuestRepository guests;
    private final RoomRepository rooms;

    public ReservationService() {
        this(new ReservationRepositoryImpl(), new GuestRepositoryImpl(), new RoomRepositoryImpl());
    }

    public ReservationService(ReservationRepository reservations, GuestRepository guests, RoomRepository rooms) {
        this.reservations = reservations;
        this.guests = guests;
        this.rooms = rooms;
    }

    public int createReservation(Reservation r) throws InvalidDateRangeException, RoomNotAvailableException {
        if (r.getStartDate() == null || r.getEndDate() == null || !r.getStartDate().isBefore(r.getEndDate())) {
            throw new InvalidDateRangeException("Invalid date range");
        }

        if (guests.findById(r.getGuestId()).isEmpty()) {
            throw new IllegalArgumentException("Guest not found");
        }
        if (rooms.findById(r.getRoomId()).isEmpty()) {
            throw new IllegalArgumentException("Room not found");
        }

        if (reservations.hasOverlap(r.getRoomId(), r.getStartDate(), r.getEndDate())) {
            throw new RoomNotAvailableException("Room not available");
        }

        return reservations.save(r);
    }

    public void cancelReservation(int reservationId) {
        if (!reservations.cancelById(reservationId)) {
            throw new IllegalArgumentException("Reservation not found");
        }
    }
}
