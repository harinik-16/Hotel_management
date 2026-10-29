
package Components.restaurant.Service;

import Components.restaurant.Models.Table;
import Components.restaurant.Repository.TableRepository;
import java.util.List;

public class TableService {

    private final TableRepository repository;

    public TableService() {
        repository = new TableRepository();
    }

    public List<Table> getAllTables() {
        return repository.findAll();
    }

    public Table getTableById(int id) {
        Table table = repository.findById(id);

        if (table == null) {
            throw new IllegalArgumentException("Table not found.");
        }

        return table;
    }

    // Book a table
    public boolean bookTable(int id) {
        Table table = getTableById(id);

        if (!table.isAvailable()) {
            return false;
        }

        table.setAvailable(false);
        return true;
    }

    // Cancel booking and make the table available
    public boolean cancelBooking(int id) {
        Table table = getTableById(id);

        if (table.isAvailable()) {
            return false;
        }

        table.setAvailable(true);
        return true;
    }
}
