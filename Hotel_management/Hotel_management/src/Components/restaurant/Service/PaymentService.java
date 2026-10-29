package Components.restaurant.Service;

import Components.restaurant.Models.Payment;
import Components.restaurant.payment.PaymentMethod;
import Components.restaurant.Repository.PaymentRepository;

import java.util.List;

public class PaymentService {

    private final PaymentRepository repository;
    private int nextPaymentId = 1;

    public PaymentService() {
        repository = new PaymentRepository();
    }

    public Payment processPayment(
            PaymentMethod method, double amount) {

        // Runtime polymorphism
        method.pay(amount);

        Payment payment = new Payment(
                nextPaymentId++,
                amount,
                method.getMethodName(),
                "SUCCESS");

        repository.save(payment);

        return payment;
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public Payment getPaymentById(int id) {
        Payment payment = repository.findById(id);

        return payment;
    }
}