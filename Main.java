
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Create Dine-In Cafe object
        Cafe cafe1 = new DineInCafe(
            "Daily Brew",
            "Shah Alam",
            8, 22,
            10, 3,
            150.00,
            0.05
        );

        // Create Takeaway Cafe object
        Cafe cafe2 = new TakeawayCafe(
            "Coffee Corner",
            "Kuala Lumpur",
            9, 23,
            15, 5,
            250.00,
            1.00
        );

        // Store derived class objects in a base class array
        // This demonstrates runtime polymorphism
        Cafe[] cafes = {cafe1, cafe2};

        int choice = -1;
        int orderCounter = 1;

        System.out.println("==================================");
        System.out.println("       CAFE MANAGEMENT SYSTEM");
        System.out.println("==================================");

        // Display information for all cafes
        for (int i = 0; i < cafes.length; i++) {
            cafes[i].displayInfo();
        }

        // Main menu
        while (choice != 0) {

            System.out.println("\n===== CAFE MANAGEMENT MENU =====");
            System.out.println("1. Check Opening Status");
            System.out.println("2. Seat Customers");
            System.out.println("3. Free Tables");
            System.out.println("4. Create Order and Payment");
            System.out.println("5. Display Cafe Information");
            System.out.println("0. Exit");

            choice = readInt(input, "Enter your choice: ");

            switch (choice) {

                // Check opening status
                case 1: {

                    int hour = readInt(input, "Enter hour (0-23): ");

                    if (hour < 0 || hour > 23) {
                        System.out.println("Invalid hour.");
                        break;
                    }

                    for (int i = 0; i < cafes.length; i++) {

                        if (cafes[i].isOpen(hour)) {
                            System.out.println(
                                cafes[i].getName() + " is open."
                            );
                        } else {
                            System.out.println(
                                cafes[i].getName() + " is closed."
                            );
                        }
                    }

                    break;
                }

                // Seat customers
                case 2: {

                    Cafe selectedCafe = selectCafe(input, cafes);

                    if (selectedCafe == null) {
                        break;
                    }

                    int needed = readInt(
                        input, "How many tables are needed? "
                    );

                    if (selectedCafe.seatCustomers(needed) != -1) {

                        System.out.println(
                            "Tables allocated successfully at "
                            + selectedCafe.getName()
                        );

                        System.out.println(
                            "Available tables: "
                            + selectedCafe.getAvailableTables()
                        );

                    } else {
                        System.out.println(
                            "Unable to allocate tables."
                        );
                    }

                    break;
                }

                // Free tables
                case 3: {

                    Cafe selectedCafe = selectCafe(input, cafes);

                    if (selectedCafe == null) {
                        break;
                    }

                    int freed = readInt(
                        input, "How many tables should be freed? "
                    );

                    if (selectedCafe.freeTables(freed) != -1) {

                        System.out.println(
                            "Tables freed successfully at "
                            + selectedCafe.getName()
                        );

                        System.out.println(
                            "Available tables: "
                            + selectedCafe.getAvailableTables()
                        );

                    } else {
                        System.out.println(
                            "Unable to free tables. Check the number entered."
                        );
                    }

                    break;
                }

                // Create order and process payment
                case 4: {

                    Cafe selectedCafe = selectCafe(input, cafes);

                    if (selectedCafe == null) {
                        break;
                    }

                    System.out.println("\n===== CUSTOMER DETAILS =====");

                    String customerID = "C" + orderCounter;

                    String customerName = readString(
                        input, "Enter customer name: "
                    );

                    String contactNumber = readString(
                        input, "Enter contact number: "
                    );

                    Customer customer = new Customer(
                        customerID,
                        customerName,
                        contactNumber
                    );

                    System.out.println("\n===== ORDER DETAILS =====");

                    double orderAmount = readDouble(
                        input, "Enter order amount (RM): "
                    );

                    if (orderAmount <= 0) {
                        System.out.println(
                            "Order amount must be greater than zero."
                        );
                        break;
                    }

                    // Create order object
                    String orderID = String.format(
                        "O%03d", orderCounter
                    );

                    Order order = new Order(
                        orderID,
                        customer,
                        selectedCafe,
                        orderAmount
                    );

                    // Display order details
                    order.displayOrderInfo();

                    // Calculate total amount
                    double amountDue = order.calculateTotalAmount();

                    System.out.printf(
                        "\nTotal amount due: RM%.2f%n",
                        amountDue
                    );

                    System.out.println("\n===== PAYMENT =====");

                    String paymentMethod = readString(
                        input,
                        "Enter payment method (Cash/Card/E-Wallet): "
                    );

                    double amountPaid = readDouble(
                        input, "Enter amount paid (RM): "
                    );

                    // Create payment object
                    String paymentID = String.format(
                        "P%03d", orderCounter
                    );

                    Payment payment = new Payment(
                        paymentID,
                        order,
                        paymentMethod,
                        amountPaid
                    );

                    // Process payment
                    if (payment.processPayment()) {

                        System.out.println(
                            "\nPayment processed successfully!"
                        );

                        // Record sales after successful payment
                        selectedCafe.recordSale(amountDue);

                        payment.displayPaymentInfo();

                        System.out.println(
                            "\nOrder completed successfully."
                        );

                        orderCounter++;

                    } else {

                        payment.displayPaymentInfo();

                        System.out.println(
                            "Order was not completed."
                        );
                    }

                    break;
                }

                // Display all cafe information
                case 5: {

                    for (int i = 0; i < cafes.length; i++) {
                        cafes[i].displayInfo();
                    }

                    break;
                }

                // Exit system
                case 0:

                    System.out.println(
                        "Thank you for using Cafe Management System!"
                    );

                    break;

                default:

                    System.out.println(
                        "Invalid choice. Please select again."
                    );
            }
        }

        input.close();
    }

    // Read a whole number
    private static int readInt(
        Scanner input, String prompt
    ) {

        while (true) {

            System.out.print(prompt);
            String line = input.nextLine().trim();

            try {
                return Integer.parseInt(line);

            } catch (NumberFormatException e) {

                System.out.println(
                    "Invalid input. Please enter a whole number."
                );
            }
        }
    }

    // Read a decimal number
    private static double readDouble(
        Scanner input, String prompt
    ) {

        while (true) {

            System.out.print(prompt);
            String line = input.nextLine().trim();

            try {

                double value = Double.parseDouble(line);

                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }

                System.out.println(
                    "Please enter a valid non-negative amount."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                    "Invalid input. Example: 25.50"
                );
            }
        }
    }

    // Read text input
    private static String readString(
        Scanner input, String prompt
    ) {

        while (true) {

            System.out.print(prompt);
            String value = input.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println(
                "Input cannot be empty. Please try again."
            );
        }
    }

    // Allow user to select a cafe
    private static Cafe selectCafe(
        Scanner input, Cafe[] cafes
    ) {

        System.out.println("\nChoose a cafe:");

        for (int i = 0; i < cafes.length; i++) {

            System.out.println(
                (i + 1) + ". " + cafes[i].getName()
            );
        }

        System.out.println("0. Cancel");

        int selection = readInt(
            input, "Enter cafe number: "
        );

        if (selection == 0) {

            System.out.println("Operation cancelled.");
            return null;
        }

        if (selection < 1 || selection > cafes.length) {

            System.out.println(
                "Invalid cafe selection."
            );

            return null;
        }

        return cafes[selection - 1];
    }
}
