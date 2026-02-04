package assignment4.entities;

public class Guest {
    private int id;
    private String name;
    private String email;

    public Guest(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Guest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
