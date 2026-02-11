package assignment4.ReservationComponent.services;

import assignment4.ReservationComponent.entities.Guest;
import assignment4.ReservationComponent.repositories.GuestRepository;
import assignment4.ReservationComponent.repositories.GuestRepositoryImpl;

public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService() {
        this(new GuestRepositoryImpl());
    }

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public int registerGuest(Guest guest) {
        if (guestRepository.findByEmail(guest.getEmail()).isPresent()) {
            throw new RuntimeException("Guest with this email already exists");
        }
        return guestRepository.save(guest);
    }

    public int findGuestIdByEmail(String email) {
        return guestRepository.findByEmail(email)
                .map(Guest::getId)
                .orElseThrow(() -> new RuntimeException("Guest not found by email"));
    }
}
