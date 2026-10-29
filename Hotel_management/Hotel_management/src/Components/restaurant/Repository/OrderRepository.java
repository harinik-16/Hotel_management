
package Components.restaurant.Repository;

import Components.restaurant.Models.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private final List<Order> orders = new ArrayList<>();
    private int nextId = 1;

    public Order save(String itemName, int quantity, double price) {
        Order order = new Order(nextId++, itemName, quantity, price);
        orders.add(order);
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    public Order findById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }
}
