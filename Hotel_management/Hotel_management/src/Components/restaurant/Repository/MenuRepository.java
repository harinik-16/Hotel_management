
package Components.restaurant.Repository;

import Components.restaurant.Models.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private final List<MenuItem> menuItems = new ArrayList<>();
    private int nextId = 1;

    public MenuItem save(String name, String category, double price) {
        MenuItem item = new MenuItem(nextId++, name, category, price);
        menuItems.add(item);
        return item;
    }

    public List<MenuItem> findAll() {
        return new ArrayList<>(menuItems);
    }

    public MenuItem findById(int id) {
        for (MenuItem item : menuItems) {
            if (item.getMenuId() == id) {
                return item;
            }
        }
        return null;
    }

    public boolean updatePrice(int id, double price) {
        MenuItem item = findById(id);

        if (item == null) {
            return false;
        }

        item.setPrice(price);
        return true;
    }

    public boolean delete(int id) {
        MenuItem item = findById(id);

        if (item == null) {
            return false;
        }

        menuItems.remove(item);
        return true;
    }

    public boolean updateAvailability(int id, boolean available) {
        MenuItem item = findById(id);

        if (item == null) {
            return false;
        }

        item.setAvailable(available);
        return true;
    }
}
