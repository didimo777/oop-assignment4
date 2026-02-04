package assignment4.repositories;

import assignment4.entities.Reservation;
import java.time.LocalDate;
import java.util.Optional;

public interface ReservationRepository {
    int save(Reservation reservation);     // returns generated id
    Optional<Reservation> findById(int id);

    boolean hasOverlap(int roomId, LocalDate startDate, LocalDate endDate);
    boolean cancelById(int id);
}
