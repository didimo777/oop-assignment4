package assignment4.entities;

public class DormBed extends Room {
    public DormBed(int id, String number, boolean available) {
        super(id, number, available, RoomType.DORM_BED);
    }
}
