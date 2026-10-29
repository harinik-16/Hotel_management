
package Components.restaurant.Service;

import Components.restaurant.Models.MenuItem;
import Components.restaurant.Repository.MenuRepository;
import java.util.List;

public class MenuService {

    private final MenuRepository repository = new MenuRepository();

    public MenuItem addMenuItem(String name, String category, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty.");
        }

        if (!Double.isFinite(price) || price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        return repository.save(name.trim(), category.trim(), price);
    }

    public List<MenuItem> getAllMenuItems() {
        return repository.findAll();
    }

    public MenuItem getMenuItemById(int id) {
        return repository.findById(id);
    }

    public boolean updatePrice(int id, double price) {
        if (!Double.isFinite(price) || price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        return repository.updatePrice(id, price);
    }

    public boolean deleteMenuItem(int id) {
        return repository.delete(id);
    }

    public boolean changeAvailability(int id, boolean available) {
        return repository.updateAvailability(id, available);
    }
}
