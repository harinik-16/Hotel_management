
package Components.restaurant.Service;

import Components.restaurant.Models.Bill;
import Components.restaurant.Models.OrderItem;
import Components.restaurant.Repository.BillRepository;

import java.util.List;

public class BillingService {

    private final BillRepository repository;
    private final OrderItemService orderItemService;

    private int nextBillId = 1;
    private static final double GST_RATE = 0.05;

    public BillingService(OrderItemService orderItemService) {
        this.repository = new BillRepository();
        this.orderItemService = orderItemService;
    }

    public Bill generateBill() {

        List<OrderItem> items =
                orderItemService.getAllOrderItems();

        if (items == null || items.isEmpty()) {
            return null;
        }

        double subtotal = 0.0;

        for (OrderItem item : items) {
            subtotal += item.getSubtotal();
        }

        double gstAmount = subtotal * GST_RATE;

        Bill bill = new Bill(
                nextBillId++,
                subtotal,
                gstAmount
        );

        repository.save(bill);

        return bill;
    }

    public List<Bill> getAllBills() {
        return repository.findAll();
    }

    public Bill getBillById(int id) {
        return repository.findById(id);
    }
}
