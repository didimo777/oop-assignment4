package assignment4.RoomManagementComponent.entities;

public class DormBed extends Room {
    public DormBed(int id, String number, boolean available) {
        super(id, number, available, RoomType.DORM_BED);
    }
}
