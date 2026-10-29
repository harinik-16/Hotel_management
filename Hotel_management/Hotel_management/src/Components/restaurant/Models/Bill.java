
package Components.restaurant.Models;

public class Bill {
    private int billId;
    private double subtotal;
    private double gstAmount;
    private double totalAmount;

    public Bill(int billId, double subtotal, double gstAmount) {
        this.billId = billId;
        this.subtotal = subtotal;
        this.gstAmount = gstAmount;
        this.totalAmount = subtotal + gstAmount;
    }

    public int getBillId() {
        return billId;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getGstAmount() {
        return gstAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "\n========== HOTEL BILL ==========" +
               "\nBill ID      : " + billId +
               String.format("\nSubtotal     : Rs. %.2f", subtotal) +
               String.format("\nGST (5%%)     : Rs. %.2f", gstAmount) +
               String.format("\nTotal Amount : Rs. %.2f", totalAmount) +
               "\n================================";
    }
}
