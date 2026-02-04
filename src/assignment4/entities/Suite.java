package assignment4.entities;

public class Suite extends Room {
    public Suite(int id, String number, boolean available) {
        super(id, number, available, RoomType.SUITE);
    }
}
