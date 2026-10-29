
package Components.restaurant.CLI;

import Components.restaurant.Models.Order;
import Components.restaurant.Service.OrderService;
import java.util.Scanner;

public class OrderCLI {

    private final OrderService service;
    private final Scanner scanner = new Scanner(System.in);

    public OrderCLI(OrderService service) {
        this.service = service;
    }

    public void start() {
        int choice = -1;

        do {
            System.out.println("\n=== ORDER MANAGEMENT ===");
            System.out.println("1. View All Orders");
            System.out.println("2. Place New Order");
            System.out.println("3. Find Order by ID");
            System.out.println("4. Update Order Status");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        if (service.getAllOrders().isEmpty()) {
                            System.out.println("No orders found.");
                        } else {
                            service.getAllOrders()
                                   .forEach(System.out::println);
                        }
                        break;

                    case 2:
                        System.out.print("Enter item name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter quantity: ");
                        int quantity = Integer.parseInt(
                            scanner.nextLine().trim()
                        );

                        System.out.print("Enter unit price: ");
                        double price = Double.parseDouble(
                            scanner.nextLine().trim()
                        );

                        Order order = service.placeOrder(
                            name, quantity, price
                        );

                        System.out.println(
                            "Order placed successfully: " + order
                        );
                        break;

                    case 3:
                        System.out.print("Enter order ID: ");
                        int searchId = Integer.parseInt(
                            scanner.nextLine().trim()
                        );

                        System.out.println(
                            service.getOrderById(searchId)
                        );
                        break;

                    case 4:
                        System.out.print("Enter order ID: ");
                        int id = Integer.parseInt(
                            scanner.nextLine().trim()
                        );

                        System.out.print(
                            "Enter status (PLACED/PREPARING/SERVED/CANCELLED): "
                        );

                        String status = scanner.nextLine().trim();

                        service.updateStatus(id, status);

                        System.out.println(
                            "Order status updated successfully."
                        );
                        break;

                    case 0:
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "Please enter valid numeric values."
                );

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);
    }
}
