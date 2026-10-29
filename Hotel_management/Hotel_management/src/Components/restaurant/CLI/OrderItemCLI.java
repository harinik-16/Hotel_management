
package Components.restaurant.CLI;

import Components.restaurant.Models.MenuItem;
import Components.restaurant.Models.OrderItem;
import Components.restaurant.Service.MenuService;
import Components.restaurant.Service.OrderItemService;

import java.util.List;
import java.util.Scanner;

public class OrderItemCLI {

    private final MenuService menuService;
    private final OrderItemService orderItemService;
    private final Scanner scanner;

    public OrderItemCLI(
            MenuService menuService,
            OrderItemService orderItemService,
            Scanner scanner) {

        this.menuService = menuService;
        this.orderItemService = orderItemService;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== ORDER ITEM MANAGEMENT =====");
            System.out.println("1. Add Order Item");
            System.out.println("2. View All Order Items");
            System.out.println("3. Search Order Item by ID");
            System.out.println("4. Update Quantity");
            System.out.println("5. Delete Order Item");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            try {
                if (choice == 1) {
                    addItem();
                } else if (choice == 2) {
                    viewAll();
                } else if (choice == 3) {
                    searchItem();
                } else if (choice == 4) {
                    updateQuantity();
                } else if (choice == 5) {
                    deleteItem();
                } else if (choice == 0) {
                    return;
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addItem() {
        List<MenuItem> menuItems = menuService.getAllMenuItems();

        System.out.println("\n--- Available Menu Items ---");

        boolean found = false;

        for (MenuItem menuItem : menuItems) {
            if (menuItem.isAvailable()) {
                System.out.println(menuItem);
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                "No available items. Add items in Menu Management first."
            );
            return;
        }

        int menuId = readInt("Enter Menu ID: ");
        int quantity = readInt("Enter quantity: ");

        OrderItem item =
            orderItemService.addOrderItem(menuId, quantity);

        System.out.println("Order item added successfully!");
        System.out.println(item);
    }

    private void viewAll() {
        List<OrderItem> items = orderItemService.getAllOrderItems();

        if (items.isEmpty()) {
            System.out.println("No order items found.");
            return;
        }

        double total = 0;

        for (OrderItem item : items) {
            System.out.println(item);
            total += item.getSubtotal();
        }

        System.out.printf(
            "Combined item total: Rs. %.2f%n", total
        );
    }

    private void searchItem() {
        int id = readInt("Enter Order Item ID: ");

        OrderItem item = orderItemService.getOrderItemById(id);

        if (item == null) {
            System.out.println("Order item not found.");
        } else {
            System.out.println(item);
        }
    }

    private void updateQuantity() {
        int id = readInt("Enter Order Item ID: ");
        int quantity = readInt("Enter new quantity: ");

        if (orderItemService.updateQuantity(id, quantity)) {
            System.out.println("Quantity updated successfully!");
            System.out.println(
                orderItemService.getOrderItemById(id)
            );
        } else {
            System.out.println("Order item not found.");
        }
    }

    private void deleteItem() {
        int id = readInt("Enter Order Item ID to delete: ");

        if (orderItemService.deleteOrderItem(id)) {
            System.out.println("Order item deleted successfully!");
        } else {
            System.out.println("Order item not found.");
        }
    }

    private int readInt(String message) {
        System.out.print(message);

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Please enter a valid whole number."
            );
        }
    }
}
