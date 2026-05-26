public class CoffeeConstructor {

    private String name;
    private String size;
    private double basePrice;
    private boolean iced;
    private boolean whippedCream;
    private int extraShots;

    public CoffeeConstructor(String name, String size, double basePrice, boolean iced, boolean whippedCream, int extraShots) {
        this.name = name;
        this.size = size;
        this.basePrice = basePrice;
        this.iced = iced;
        this.whippedCream = whippedCream;
        this.extraShots = extraShots;
    }

    public double calculatePrice() {
        double total = basePrice;

        if (size.equalsIgnoreCase("Medium")) {
            total += 0.75;
        } else if (size.equalsIgnoreCase("Large")) {
            total += 1.50;
        }

        if (iced) {
            total += 0.50;
        }

        if (whippedCream) {
            total += 0.75;
        }

        total += extraShots * 1.00;

        return total;
    }
}