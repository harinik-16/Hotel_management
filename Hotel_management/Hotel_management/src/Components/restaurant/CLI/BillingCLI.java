
package Components.restaurant.CLI;

import Components.restaurant.Models.Bill;
import Components.restaurant.Service.BillingService;
import java.util.Scanner;

public class BillingCLI {
    private final BillingService billingService;
    private final Scanner scanner;

    public BillingCLI(BillingService billingService, Scanner scanner) {
        this.billingService = billingService;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== BILLING MANAGEMENT =====");
            System.out.println("1. Generate Bill");
            System.out.println("2. View All Bills");
            System.out.println("3. Search Bill by ID");
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

            switch (choice) {
                case 1:
                    Bill bill = billingService.generateBill();

                    if (bill == null) {
                        System.out.println(
                            "No order items found. Add items first."
                        );
                    } else {
                        System.out.println(bill);
                    }
                    break;

                case 2:
                    for (Bill savedBill : billingService.getAllBills()) {
                        System.out.println(savedBill);
                    }
                    if (billingService.getAllBills().isEmpty()) {
                        System.out.println("No bills available.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Bill ID: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        Bill found = billingService.getBillById(id);

                        if (found != null) {
                            System.out.println(found);
                        } else {
                            System.out.println("Bill not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Bill ID.");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
