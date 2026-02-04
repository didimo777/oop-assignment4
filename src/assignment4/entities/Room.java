package assignment4.entities;

public class Room {
    private int id;
    private String number;
    private boolean available;

    public Room(int id, String number, boolean available) {
        this.id = id;
        this.number = number;
        this.available = available;
    }

    public int getId() { return id; }
    public String getNumber() { return number; }
    public boolean isAvailable() { return available; }
}
