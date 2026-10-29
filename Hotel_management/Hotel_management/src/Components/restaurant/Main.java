
package Components.restaurant;

import Components.restaurant.CLI.BillingCLI;
import Components.restaurant.CLI.MenuCLI;
import Components.restaurant.CLI.OrderCLI;
import Components.restaurant.CLI.OrderItemCLI;
import Components.restaurant.CLI.PaymentCli;
import Components.restaurant.CLI.TableCLI;
import Components.restaurant.Service.BillingService;
import Components.restaurant.Service.MenuService;
import Components.restaurant.Service.OrderItemService;
import Components.restaurant.Service.OrderService;
import Components.restaurant.Service.TableService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Shared services
        MenuService menuService = new MenuService();

        OrderItemService orderItemService =
                new OrderItemService(menuService);

        BillingService billingService =
                new BillingService(orderItemService);

        while (true) {

            System.out.println("\n===== HOTEL MANAGEMENT SYSTEM =====");
            System.out.println("1. Table Management");
            System.out.println("2. Order Management");
            System.out.println("3. Payment Management");
            System.out.println("4. Menu Management");
            System.out.println("5. OrderItem Management");
            System.out.println("6. Billing Management");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            if (choice == 1) {

                new TableCLI(new TableService()).start();

            } else if (choice == 2) {

                new OrderCLI(new OrderService()).start();

            } else if (choice == 3) {

                new PaymentCli().start();

            } else if (choice == 4) {

                MenuCLI menuCLI = new MenuCLI(menuService);
                menuCLI.start();

            } else if (choice == 5) {

                OrderItemCLI orderItemCLI =
                        new OrderItemCLI(
                                menuService,
                                orderItemService,
                                scanner
                        );

                orderItemCLI.start();

            } else if (choice == 6) {

                BillingCLI billingCLI =
                        new BillingCLI(billingService, scanner);

                billingCLI.start();

            } else if (choice == 0) {

                System.out.println(
                        "Thank you for using Hotel Management System!"
                );

                scanner.close();
                return;

            } else {

                System.out.println(
                        "Invalid choice! Please choose 0, 1, 2, 3, 4, 5, or 6."
                );
            }
        }
    }
}
