
package Components.restaurant.Service;

import Components.restaurant.Models.MenuItem;
import Components.restaurant.Models.OrderItem;
import Components.restaurant.Repository.OrderItemRepository;

import java.util.List;

public class OrderItemService {

    private final OrderItemRepository repository;
    private final MenuService menuService;

    public OrderItemService(MenuService menuService) {
        if (menuService == null) {
            throw new IllegalArgumentException(
                "MenuService cannot be null."
            );
        }

        this.menuService = menuService;
        this.repository = new OrderItemRepository();
    }

    public OrderItem addOrderItem(int menuItemId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero."
            );
        }

        MenuItem menuItem = menuService.getMenuItemById(menuItemId);

        if (menuItem == null) {
            throw new IllegalArgumentException(
                "Menu item not found. Enter a valid Menu ID."
            );
        }

        if (!menuItem.isAvailable()) {
            throw new IllegalArgumentException(
                "This menu item is unavailable."
            );
        }

        return repository.save(menuItem, quantity);
    }

    public List<OrderItem> getAllOrderItems() {
        return repository.findAll();
    }

    public OrderItem getOrderItemById(int id) {
        return repository.findById(id);
    }

    public boolean updateQuantity(int id, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero."
            );
        }

        return repository.updateQuantity(id, quantity);
    }

    public boolean deleteOrderItem(int id) {
        return repository.delete(id);
    }
}
