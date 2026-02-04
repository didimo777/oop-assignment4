package assignment4.repositories;

import assignment4.entities.Guest;
import java.util.Optional;

public interface GuestRepository {
    void save(Guest guest);
    Optional<Guest> findById(int id);
    Optional<Guest> findByEmail(String email);
}
