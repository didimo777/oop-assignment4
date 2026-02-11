package assignment4.ReservationComponent.repositories;

import assignment4.ReservationComponent.entities.Guest;
import java.util.Optional;

public interface GuestRepository {
    int save(Guest guest);                 // returns generated id
    Optional<Guest> findById(int id);
    Optional<Guest> findByEmail(String email);
}
