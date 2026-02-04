package assignment4.services;

import assignment4.entities.Room;
import assignment4.repositories.RoomRepository;
import assignment4.repositories.RoomRepositoryImpl;

import java.util.List;

public class RoomAvailabilityService {

    private final RoomRepository roomRepository = new RoomRepositoryImpl();


    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }


    public boolean isRoomAvailable(int roomId) {
        return roomRepository.findById(roomId)
                .map(Room::isAvailable)
                .orElse(false);
    }
}
