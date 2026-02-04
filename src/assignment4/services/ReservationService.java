package assignment4.services;

import assignment4.entities.Reservation;
import assignment4.repositories.*;

public class ReservationService {

    private final ReservationRepository reservationRepository = new ReservationRepositoryImpl();
    private final GuestRepository guestRepository = new GuestRepositoryImpl();
    private final RoomRepository roomRepository = new RoomRepositoryImpl();

    public void createReservation(Reservation r) {

        if (guestRepository.findById(r.getGuestId()).isEmpty())
            throw new RuntimeException("Guest not found");

        if (roomRepository.findById(r.getRoomId()).isEmpty())
            throw new RuntimeException("Room not found");

        if (r.getStartDate().isAfter(r.getEndDate()))
            throw new RuntimeException("Invalid date range");

        reservationRepository.save(r);
    }
}
