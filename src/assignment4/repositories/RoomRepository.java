package assignment4.repositories;

import assignment4.entities.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Optional<Room> findById(int id);
    List<Room> findAll();
    List<Room> findAvailable(LocalDate startDate, LocalDate endDate);
}
