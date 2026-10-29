package Components.restaurant.CLI;

import Components.restaurant.Models.Payment;
import Components.restaurant.payment.CardPayment;
import Components.restaurant.payment.CashPayment;
import Components.restaurant.payment.PaymentMethod;
import Components.restaurant.payment.UPIPayment;
import Components.restaurant.Service.PaymentService;
import java.util.Scanner;

public class PaymentCli {
    private final PaymentService service = new PaymentService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=== PAYMENT MANAGEMENT ===");
            System.out.println("1. Make Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. Find Payment by ID");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = readInt();

            if (choice == 1) {
                makePayment();
            } else if (choice == 2) {
                viewAllPayments();
            } else if (choice == 3) {
                findPayment();
            } else if (choice == 0) {
                System.out.println("Returning to main menu...");
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void makePayment() {
        System.out.print("Enter amount: Rs. ");
        double amount = readDouble();
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        System.out.println("\nSelect Payment Method");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        System.out.print("Enter choice: ");
        int methodChoice = readInt();

        PaymentMethod method;
        if (methodChoice == 1) {
            method = new CashPayment();
        } else if (methodChoice == 2) {
            method = new CardPayment();
        } else if (methodChoice == 3) {
            method = new UPIPayment();
        } else {
            System.out.println("Invalid payment method.");
            return;
        }

        Payment payment = service.processPayment(method, amount);
        System.out.println("Payment record saved.");
        System.out.println(payment);
    }

    private void viewAllPayments() {
        if (service.getAllPayments().isEmpty()) {
            System.out.println("No payment records found.");
            return;
        }
        for (Payment payment : service.getAllPayments()) {
            System.out.println(payment);
        }
    }

    private void findPayment() {
        System.out.print("Enter payment ID: ");
        int id = readInt();
        Payment payment = service.getPaymentById(id);
        if (payment == null) {
            System.out.println("Payment not found.");
        } else {
            System.out.println(payment);
        }
    }

    private int readInt() {
        while (true) {
            String value = scanner.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid whole number: ");
            }
        }
    }

    private double readDouble() {
        while (true) {
            String value = scanner.nextLine().trim();
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid amount: ");
            }
        }
    }
}
