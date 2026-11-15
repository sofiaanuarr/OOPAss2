
public class Cafe {

    // 1. Private attributes (Encapsulation)
    private String name = "Unknown Cafe";
    private String location = "Unknown";
    private int openHour = 8;
    private int closeHour = 22;
    private int totalTables = 10;
    private int occupiedTables = 0;
    private double dailySales = 0.0;

    // 2. Default constructor
    public Cafe() {
    }

    // 3. Parameterized constructor
    public Cafe(String name, String location, int openHour,
                int closeHour, int totalTables,
                int occupiedTables, double dailySales) {

        setName(name);
        setLocation(location);
        setOpenHour(openHour);
        setCloseHour(closeHour);
        setTotalTables(totalTables);
        setOccupiedTables(occupiedTables);
        setDailySales(dailySales);
    }

    // 4. Getters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getOpenHour() {
        return openHour;
    }

    public int getCloseHour() {
        return closeHour;
    }

    public int getTotalTables() {
        return totalTables;
    }

    public int getOccupiedTables() {
        return occupiedTables;
    }

    public double getDailySales() {
        return dailySales;
    }

    // 5. Setters with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void setLocation(String location) {
        if (location != null && !location.trim().isEmpty()) {
            this.location = location.trim();
        }
    }

    public void setOpenHour(int openHour) {
        if (openHour >= 0 && openHour <= 23) {
            this.openHour = openHour;
        }
    }

    public void setCloseHour(int closeHour) {
        if (closeHour >= 0 && closeHour <= 23) {
            this.closeHour = closeHour;
        }
    }

    public void setTotalTables(int totalTables) {
        if (totalTables > 0 && totalTables >= occupiedTables) {
            this.totalTables = totalTables;
        }
    }

    public void setOccupiedTables(int occupiedTables) {
        if (occupiedTables >= 0 && occupiedTables <= totalTables) {
            this.occupiedTables = occupiedTables;
        }
    }

    public void setDailySales(double dailySales) {
        if (Double.isFinite(dailySales) && dailySales >= 0) {
            this.dailySales = dailySales;
        }
    }

    // 6. Check cafe opening status
    public boolean isOpen(int hour) {
        if (hour < 0 || hour > 23) {
            return false;
        }

        if (openHour < closeHour) {
            return hour >= openHour && hour < closeHour;
        }

        if (openHour > closeHour) {
            return hour >= openHour || hour < closeHour;
        }

        return false;
    }

    // 7. Seat customers
    public int seatCustomers(int tables) {
        if (tables > 0 && tables <= getAvailableTables()) {
            occupiedTables += tables;
            return occupiedTables;
        }

        return -1;
    }

    // 8. Free tables
    public int freeTables(int tables) {
        if (tables > 0 && tables <= occupiedTables) {
            occupiedTables -= tables;
            return occupiedTables;
        }

        return -1;
    }

    // 9. Calculate available tables
    public int getAvailableTables() {
        return totalTables - occupiedTables;
    }

    // 10. Record daily sales
    public void recordSale(double amount) {
        if (Double.isFinite(amount) && amount > 0) {
            dailySales += amount;
        }
    }

    // 11. Get occupancy status
    public String getOccupancyStatus() {
        if (occupiedTables == 0) {
            return "Empty";
        } else if (occupiedTables == totalTables) {
            return "Full";
        } else {
            return "Partially Occupied";
        }
    }

    // 12. Polymorphism method
    // Derived classes will override this method
    public double calculateExtraCharge(double amount) {
        return 0.0;
    }

    // 13. Display cafe information
    public void displayInfo() {
        System.out.println("\n===== Cafe Information =====");
        System.out.println("Name: " + getName());
        System.out.println("Location: " + getLocation());
        System.out.println("Opening Hour: " + getOpenHour() + ":00");
        System.out.println("Closing Hour: " + getCloseHour() + ":00");
        System.out.println("Total Tables: " + getTotalTables());
        System.out.println("Occupied Tables: " + getOccupiedTables());
        System.out.println("Available Tables: " + getAvailableTables());
        System.out.printf("Daily Sales: RM %.2f%n", getDailySales());
        System.out.println("Occupancy Status: " + getOccupancyStatus());
    }
}
