
package Components.restaurant.Models;

public class MenuItem {

    private int menuId;
    private String itemName;
    private String category;
    private double price;
    private boolean available;

    public MenuItem(int menuId, String itemName,
                    String category, double price) {
        this.menuId = menuId;
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Menu ID: " + menuId
                + " | Item: " + itemName
                + " | Category: " + category
                + " | Price: Rs." + String.format("%.2f", price)
                + " | Status: " + (available ? "Available" : "Unavailable");
    }
}
