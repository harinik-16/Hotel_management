
package Components.restaurant.Models;

public class OrderItem {
    private int orderItemId;
    private int menuItemId;
    private String itemName;
    private int quantity;
    private double unitPrice;

    public OrderItem(int orderItemId, MenuItem menuItem, int quantity) {
        if (menuItem == null) {
            throw new IllegalArgumentException("Menu item cannot be null.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero."
            );
        }

        this.orderItemId = orderItemId;
        this.menuItemId = menuItem.getMenuId();
        this.itemName = menuItem.getItemName();
        this.quantity = quantity;
        this.unitPrice = menuItem.getPrice();
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSubtotal() {
        return quantity * unitPrice;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero."
            );
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem ID: " + orderItemId
            + " | Menu ID: " + menuItemId
            + " | Item: " + itemName
            + " | Quantity: " + quantity
            + " | Unit Price: Rs."
            + String.format("%.2f", unitPrice)
            + " | Subtotal: Rs."
            + String.format("%.2f", getSubtotal());
    }
}
