
package Components.restaurant.Models;

public class Order {
    private int id;
    private String itemName;
    private int quantity;
    private double price;
    private String status;

    public Order(int id, String itemName, int quantity, double price) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.status = "PLACED";
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "Order ID: " + id
                + ", Item: " + itemName
                + ", Quantity: " + quantity
                + ", Price: " + price
                + ", Total: " + getTotal()
                + ", Status: " + status;
    }
}
