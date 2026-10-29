package Components.restaurant.Models;

public class Table {

    private final int id;
    private final int capacity;
    private String status;

    public Table(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.status = "AVAILABLE";
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Table ID: " + id
                + ", Capacity: " + capacity
                + ", Status: " + status;
    }
}
