package assignment4.factory;

import assignment4.entities.*;

public class RoomFactory {

    public static Room create(RoomType type, int id, String number, boolean available) {
        return switch (type) {
            case STANDARD -> new StandardRoom(id, number, available);
            case SUITE -> new Suite(id, number, available);
            case DORM_BED -> new DormBed(id, number, available);
        };
    }
}
