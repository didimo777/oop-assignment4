package assignment4.RoomManagementComponent.entities;

public class Suite extends Room {
    public Suite(int id, String number, boolean available) {
        super(id, number, available, RoomType.SUITE);
    }
}
