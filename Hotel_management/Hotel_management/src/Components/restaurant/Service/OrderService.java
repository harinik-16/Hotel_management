
package Components.restaurant.Service;

import Components.restaurant.Models.Order;
import Components.restaurant.Repository.OrderRepository;
import java.util.List;

public class OrderService {

    private final OrderRepository repository;

    public OrderService() {
        repository = new OrderRepository();
    }

    public Order placeOrder(String itemName, int quantity, double unitPrice) {
        return repository.save(itemName, quantity, unitPrice);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(int id) {
        Order order = repository.findById(id);

        if (order == null) {
            throw new IllegalArgumentException("Order not found.");
        }

        return order;
    }

    public void updateStatus(int id, String status) {
        if (status == null ||
            !(status.equalsIgnoreCase("PLACED")
            || status.equalsIgnoreCase("PREPARING")
            || status.equalsIgnoreCase("SERVED")
            || status.equalsIgnoreCase("CANCELLED"))) {

            throw new IllegalArgumentException(
                "Status must be PLACED, PREPARING, SERVED, or CANCELLED."
            );
        }

        getOrderById(id).setStatus(status.toUpperCase());
    }
}
