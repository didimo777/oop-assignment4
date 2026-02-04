package assignment4.repositories;

import assignment4.entities.Guest;
import java.util.Optional;

public interface GuestRepository {
    int save(Guest guest);                 // returns generated id
    Optional<Guest> findById(int id);
    Optional<Guest> findByEmail(String email);
}
