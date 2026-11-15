
public class Customer {

    // 1. Private attributes (Encapsulation)
    private String customerID;
    private String customerName;
    private String contactNumber;

    // 2. Default constructor
    public Customer() {
        customerID = "C000";
        customerName = "Unknown Customer";
        contactNumber = "Not Provided";
    }

    // 3. Parameterized constructor
    public Customer(String customerID, String customerName,
                    String contactNumber) {

        setCustomerID(customerID);
        setCustomerName(customerName);
        setContactNumber(contactNumber);
    }

    // 4. Getters
    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    // 5. Setters with validation
    public void setCustomerID(String customerID) {
        if (customerID != null && !customerID.trim().isEmpty()) {
            this.customerID = customerID.trim();
        }
    }

    public void setCustomerName(String customerName) {
        if (customerName != null && !customerName.trim().isEmpty()) {
            this.customerName = customerName.trim();
        }
    }

    public void setContactNumber(String contactNumber) {
        if (contactNumber != null && !contactNumber.trim().isEmpty()) {
            this.contactNumber = contactNumber.trim();
        }
    }

    // 6. Display customer information
    public void displayCustomerInfo() {
        System.out.println("\n===== Customer Information =====");
        System.out.println("Customer ID: " + getCustomerID());
        System.out.println("Customer Name: " + getCustomerName());
        System.out.println("Contact Number: " + getContactNumber());
    }
}
