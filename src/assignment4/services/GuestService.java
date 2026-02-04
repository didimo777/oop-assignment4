package assignment4.services;

import assignment4.entities.Guest;
import assignment4.repositories.*;

public class GuestService {

    private final GuestRepository guestRepository = new GuestRepositoryImpl();

    public void registerGuest(Guest guest) {
        if (guestRepository.findByEmail(guest.getEmail()).isPresent()) {
            throw new RuntimeException("Guest with this email already exists");
        }
        guestRepository.save(guest);
    }
}
