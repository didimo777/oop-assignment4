package assignment4.entities;

public class Room {
    private final int id;
    private final String number;
    private final boolean available;
    private final RoomType type;

    public Room(int id, String number, boolean available, RoomType type) {
        this.id = id;
        this.number = number;
        this.available = available;
        this.type = type;
    }

    public int getId() { return id; }
    public String getNumber() { return number; }
    public boolean isAvailable() { return available; }
    public RoomType getType() { return type; }

    public double getBasePrice() { return getBasePrice();
    }
}
