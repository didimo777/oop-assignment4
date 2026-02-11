package assignment4.RoomManagementComponent;

import assignment4.RoomManagementComponent.entities.Room;
import assignment4.ReservationComponent.exceptions.InvalidDateRangeException;
import assignment4.RoomManagementComponent.repositories.RoomRepository;
import assignment4.RoomManagementComponent.repositories.RoomRepositoryImpl;
import assignment4.RoomManagementComponent.service.RoomAvailabilityService;
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
