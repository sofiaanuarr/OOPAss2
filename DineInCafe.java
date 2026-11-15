
public class DineInCafe extends Cafe {

    // 1. Private attribute for dine-in service charge
    private double serviceChargeRate = 0.05;

    // 2. Default constructor
    public DineInCafe() {
        super();
    }

    // 3. Parameterized constructor
    public DineInCafe(String name, String location,
                      int openHour, int closeHour,
                      int totalTables, int occupiedTables,
                      double dailySales, double serviceChargeRate) {

        super(name, location, openHour, closeHour,
              totalTables, occupiedTables, dailySales);

        setServiceChargeRate(serviceChargeRate);
    }

    // 4. Getter
    public double getServiceChargeRate() {
        return serviceChargeRate;
    }

    // 5. Setter with validation
    public void setServiceChargeRate(double serviceChargeRate) {
        if (Double.isFinite(serviceChargeRate)
                && serviceChargeRate >= 0
                && serviceChargeRate <= 1) {

            this.serviceChargeRate = serviceChargeRate;
        }
    }

    // 6. Override method to calculate dine-in service charge
    @Override
    public double calculateExtraCharge(double amount) {
        if (Double.isFinite(amount) && amount > 0) {
            return amount * serviceChargeRate;
        }

        return 0.0;
    }

    // 7. Override displayInfo method
    @Override
    public void displayInfo() {
        super.displayInfo();

        System.out.printf("Cafe Type: Dine-In%n");
        System.out.printf("Service Charge Rate: %.0f%%%n",
                serviceChargeRate * 100);
    }
}
