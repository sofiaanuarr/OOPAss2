
public class Order {

    // 1. Private attributes (Encapsulation)
    private String orderID;
    private Customer customer;
    private Cafe cafe;
    private double orderAmount;

    // 2. Default constructor
    public Order() {
        orderID = "O000";
        customer = new Customer();
        cafe = new Cafe();
        orderAmount = 0.0;
    }

    // 3. Parameterized constructor
    public Order(String orderID, Customer customer,
                 Cafe cafe, double orderAmount) {

        setOrderID(orderID);
        setCustomer(customer);
        setCafe(cafe);
        setOrderAmount(orderAmount);
    }

    // 4. Getters
    public String getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    // 5. Setters with validation
    public void setOrderID(String orderID) {
        if (orderID != null && !orderID.trim().isEmpty()) {
            this.orderID = orderID.trim();
        }
    }

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.customer = customer;
        }
    }

    public void setCafe(Cafe cafe) {
        if (cafe != null) {
            this.cafe = cafe;
        }
    }

    public void setOrderAmount(double orderAmount) {
        if (Double.isFinite(orderAmount) && orderAmount >= 0) {
            this.orderAmount = orderAmount;
        }
    }

    // 6. Calculate total amount including cafe extra charge
    public double calculateTotalAmount() {
        double extraCharge = cafe.calculateExtraCharge(orderAmount);

        return orderAmount + extraCharge;
    }

    // 7. Display order information
    public void displayOrderInfo() {
        System.out.println("\n===== Order Information =====");
        System.out.println("Order ID: " + getOrderID());

        System.out.println("Customer: "
                + customer.getCustomerName());

        System.out.println("Cafe: "
                + cafe.getName());

        System.out.printf("Order Amount: RM %.2f%n",
                getOrderAmount());

        System.out.printf("Extra Charge: RM %.2f%n",
                cafe.calculateExtraCharge(orderAmount));

        System.out.printf("Total Amount: RM %.2f%n",
                calculateTotalAmount());
    }
}
