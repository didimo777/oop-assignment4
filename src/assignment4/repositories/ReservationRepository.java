package assignment4.repositories;

import assignment4.entities.Reservation;
import java.util.Optional;

public interface ReservationRepository {
    void save(Reservation reservation);
    Optional<Reservation> findById(int id);
}

