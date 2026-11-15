
public class TakeawayCafe extends Cafe {

    // 1. Private attribute for takeaway packaging fee
    private double packagingFee = 1.00;

    // 2. Default constructor
    public TakeawayCafe() {
        super();
    }

    // 3. Parameterized constructor
    public TakeawayCafe(String name, String location,
                        int openHour, int closeHour,
                        int totalTables, int occupiedTables,
                        double dailySales, double packagingFee) {

        super(name, location, openHour, closeHour,
              totalTables, occupiedTables, dailySales);

        setPackagingFee(packagingFee);
    }

    // 4. Getter
    public double getPackagingFee() {
        return packagingFee;
    }

    // 5. Setter with validation
    public void setPackagingFee(double packagingFee) {
        if (Double.isFinite(packagingFee) && packagingFee >= 0) {
            this.packagingFee = packagingFee;
        }
    }

    // 6. Override method to calculate takeaway packaging fee
    @Override
    public double calculateExtraCharge(double amount) {
        if (Double.isFinite(amount) && amount > 0) {
            return packagingFee;
        }

        return 0.0;
    }

    // 7. Override displayInfo method
    @Override
    public void displayInfo() {
        super.displayInfo();

        System.out.println("Cafe Type: Takeaway");
        System.out.printf("Packaging Fee: RM %.2f%n", packagingFee);
    }
}
