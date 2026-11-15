
public class Payment {

    // Private attributes (Encapsulation)
    private String paymentID;
    private Order order;
    private String paymentMethod;
    private double amountPaid;
    private boolean paymentSuccessful;

    // Default constructor
    public Payment() {
        this.paymentID = "P000";
        this.order = new Order();
        this.paymentMethod = "Cash";
        this.amountPaid = 0.0;
        this.paymentSuccessful = false;
    }

    // Parameterized constructor
    public Payment(String paymentID, Order order,
                   String paymentMethod, double amountPaid) {

        setPaymentID(paymentID);
        setOrder(order);
        setPaymentMethod(paymentMethod);
        setAmountPaid(amountPaid);
        this.paymentSuccessful = false;
    }

    // Getter methods
    public String getPaymentID() {
        return paymentID;
    }

    public Order getOrder() {
        return order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public boolean isPaymentSuccessful() {
        return paymentSuccessful;
    }

    // Setter methods
    public void setPaymentID(String paymentID) {
        if (paymentID != null && !paymentID.trim().isEmpty()) {
            this.paymentID = paymentID;
        }
    }

    public void setOrder(Order order) {
        if (order != null) {
            this.order = order;
        }
    }

    public void setPaymentMethod(String paymentMethod) {
        if (paymentMethod != null && !paymentMethod.trim().isEmpty()) {
            this.paymentMethod = paymentMethod;
        }
    }

    public void setAmountPaid(double amountPaid) {
        if (Double.isFinite(amountPaid) && amountPaid >= 0) {
            this.amountPaid = amountPaid;
        }
    }

    // Calculate total amount that needs to be paid
    public double getAmountDue() {
        return order.calculateTotalAmount();
    }

    // Process payment
    public boolean processPayment() {

        if (amountPaid >= getAmountDue()) {
            paymentSuccessful = true;
            return true;
        } else {
            paymentSuccessful = false;
            return false;
        }
    }

    // Calculate change
    public double getChange() {
        if (paymentSuccessful) {
            return amountPaid - getAmountDue();
        }

        return 0.0;
    }

    // Display payment information
    public void displayPaymentInfo() {

        System.out.println("\n===== PAYMENT INFORMATION =====");
        System.out.println("Payment ID: " + paymentID);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.printf("Amount Due: RM%.2f%n", getAmountDue());
        System.out.printf("Amount Paid: RM%.2f%n", amountPaid);

        if (paymentSuccessful) {
            System.out.println("Payment Status: Successful");
            System.out.printf("Change: RM%.2f%n", getChange());
        } else {
            System.out.println("Payment Status: Unsuccessful");
            System.out.println("Insufficient payment.");
        }
    }
}
