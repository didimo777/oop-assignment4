package assignment4.RoomManagementComponent;

import assignment4.RoomManagementComponent.entities.Room;

@FunctionalInterface
public interface RoomFilter {
    boolean test(Room room);
}
