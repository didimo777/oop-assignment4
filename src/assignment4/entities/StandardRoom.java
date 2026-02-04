package assignment4.entities;

public class StandardRoom extends Room {
    public StandardRoom(int id, String number, boolean available) {
        super(id, number, available, RoomType.STANDARD);
    }
}
