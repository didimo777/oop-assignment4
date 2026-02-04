package assignment4.services;

import assignment4.entities.Room;
import assignment4.exceptions.InvalidDateRangeException;
import assignment4.repositories.RoomRepository;
import assignment4.repositories.RoomRepositoryImpl;
import assignment4.util.SearchResult;

import java.time.LocalDate;

public class RoomAvailabilityService {

    private final RoomRepository roomRepository;

    public RoomAvailabilityService() {
        this(new RoomRepositoryImpl());
    }

    public RoomAvailabilityService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public SearchResult<Room> searchAvailableRooms(LocalDate startDate, LocalDate endDate)
            throws InvalidDateRangeException {

        if (startDate == null || endDate == null || !startDate.isBefore(endDate)) {
            throw new InvalidDateRangeException("Invalid date range");
        }

        return new SearchResult<>(roomRepository.findAvailable(startDate, endDate));
    }
}
