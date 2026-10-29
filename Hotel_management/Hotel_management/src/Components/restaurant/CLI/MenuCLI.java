
package Components.restaurant.CLI;

import Components.restaurant.Models.MenuItem;
import Components.restaurant.Service.MenuService;
import java.util.List;
import java.util.Scanner;

public class MenuCLI {

    private final MenuService service;
    private final Scanner scanner = new Scanner(System.in);

    public MenuCLI(MenuService service) {
        this.service = service;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== MENU MANAGEMENT =====");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View All Menu Items");
            System.out.println("3. Search Menu Item by ID");
            System.out.println("4. Update Item Price");
            System.out.println("5. Delete Menu Item");
            System.out.println("6. Change Availability");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
                continue;
            }

            try {
                if (choice == 1) {
                    addItem();
                } else if (choice == 2) {
                    viewItems();
                } else if (choice == 3) {
                    searchItem();
                } else if (choice == 4) {
                    updatePrice();
                } else if (choice == 5) {
                    deleteItem();
                } else if (choice == 6) {
                    changeAvailability();
                } else if (choice == 0) {
                    return;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        double price = readPrice("Enter price: ");

        MenuItem item = service.addMenuItem(name, category, price);
        System.out.println("Menu item added successfully!");
        System.out.println(item);
    }

    private void viewItems() {
        List<MenuItem> items = service.getAllMenuItems();

        if (items.isEmpty()) {
            System.out.println("No menu items found.");
            return;
        }

        for (MenuItem item : items) {
            System.out.println(item);
        }
    }

    private void searchItem() {
        int id = readInt("Enter Menu ID: ");
        MenuItem item = service.getMenuItemById(id);

        if (item == null) {
            System.out.println("Menu item not found.");
        } else {
            System.out.println(item);
        }
    }

    private void updatePrice() {
        int id = readInt("Enter Menu ID: ");
        double price = readPrice("Enter new price: ");

        if (service.updatePrice(id, price)) {
            System.out.println("Price updated successfully.");
        } else {
            System.out.println("Menu item not found.");
        }
    }

    private void deleteItem() {
        int id = readInt("Enter Menu ID to delete: ");

        if (service.deleteMenuItem(id)) {
            System.out.println("Menu item deleted successfully.");
        } else {
            System.out.println("Menu item not found.");
        }
    }

    private void changeAvailability() {
        int id = readInt("Enter Menu ID: ");

        System.out.println("1. Available");
        System.out.println("2. Unavailable");
        int status = readInt("Choose status: ");

        if (status != 1 && status != 2) {
            System.out.println("Invalid status.");
            return;
        }

        if (service.changeAvailability(id, status == 1)) {
            System.out.println("Availability updated successfully.");
        } else {
            System.out.println("Menu item not found.");
        }
    }

    private int readInt(String message) {
        System.out.print(message);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid whole number.");
        }
    }

    private double readPrice(String message) {
        System.out.print(message);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid price.");
        }
    }
}
