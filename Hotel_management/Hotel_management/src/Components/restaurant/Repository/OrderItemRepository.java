
package Components.restaurant.Repository;

import Components.restaurant.Models.MenuItem;
import Components.restaurant.Models.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {

    private final List<OrderItem> orderItems = new ArrayList<>();
    private int nextId = 1;

    public OrderItem save(MenuItem menuItem, int quantity) {
        OrderItem item = new OrderItem(nextId++, menuItem, quantity);
        orderItems.add(item);
        return item;
    }

    public List<OrderItem> findAll() {
        return new ArrayList<>(orderItems);
    }

    public OrderItem findById(int id) {
        for (OrderItem item : orderItems) {
            if (item.getOrderItemId() == id) {
                return item;
            }
        }
        return null;
    }

    public boolean updateQuantity(int id, int quantity) {
        OrderItem item = findById(id);

        if (item == null) {
            return false;
        }

        item.setQuantity(quantity);
        return true;
    }

    public boolean delete(int id) {
        OrderItem item = findById(id);

        if (item == null) {
            return false;
        }

        orderItems.remove(item);
        return true;
    }
}
