package assignment4.RoomManagementComponent.service;

import assignment4.RoomManagementComponent.entities.Room;
import assignment4.ReservationComponent.exceptions.InvalidDateRangeException;
import assignment4.RoomManagementComponent.repositories.RoomRepository;
import assignment4.RoomManagementComponent.repositories.RoomRepositoryImpl;
import assignment4.util.SearchResult;
import java.util.List;
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

        List<Room> availableRooms = roomRepository.findAvailable(startDate, endDate);
        List<Room> filteredRooms = availableRooms.stream()
                .filter(room -> room != null)
                .toList();

        return new SearchResult<>(filteredRooms);
    }
}
