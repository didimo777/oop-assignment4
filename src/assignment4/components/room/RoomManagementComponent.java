package assignment4.components.room;

import assignment4.entities.Room;
import assignment4.exceptions.InvalidDateRangeException;
import assignment4.repositories.RoomRepository;
import assignment4.repositories.RoomRepositoryImpl;
import assignment4.services.RoomAvailabilityService;
import assignment4.util.SearchResult;

import java.time.LocalDate;
import java.util.List;

public class RoomManagementComponent {

    private final RoomRepository roomRepository;
    private final RoomAvailabilityService availabilityService;

    public RoomManagementComponent() {
        this(new RoomRepositoryImpl());
    }

    public RoomManagementComponent(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
        this.availabilityService = new RoomAvailabilityService(roomRepository);
    }

    public SearchResult<Room> searchAvailableRooms(LocalDate start, LocalDate end)
            throws InvalidDateRangeException {
        return availabilityService.searchAvailableRooms(start, end);
    }

    public List<Room> listAllRooms() {
        return roomRepository.findAll();
    }
}
