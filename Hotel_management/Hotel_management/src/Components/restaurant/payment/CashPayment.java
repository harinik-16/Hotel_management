package Components.restaurant.payment;

public class CashPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.printf(
                "Cash payment of Rs. %.2f successful.%n", amount);
    }

    @Override
    public String getMethodName() {
        return "Cash";
    }
}