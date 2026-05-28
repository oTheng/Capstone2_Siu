public class Drink implements OrderItem {

    private String drinkName;
    private String size;
    private boolean iced;
    private String specialInstructions;
    private double price;

    public Drink(String drinkName, String size, boolean iced, String specialInstructions) {
        this.drinkName = drinkName;
        this.size = size;
        this.iced = iced;
        this.specialInstructions = specialInstructions;
        this.price = calculatePrice();
    }

    private double calculatePrice() {
        double total = 0.0;

        if (drinkName.equals("Water")) {
            total = 1.00;
        } else if (drinkName.equals("Soda")) {
            total = 2.00;
        } else if (drinkName.equals("Lemonade")) {
            total = 2.75;
        } else if (drinkName.equals("Iced Tea")) {
            total = 2.50;
        } else if (drinkName.equals("Orange Juice")) {
            total = 3.00;
        }

        if (size.equals("Medium")) {
            total += 0.50;
        } else if (size.equals("Large")) {
            total += 1.00;
        }

        return total;
    }

    @Override
    public String getItemName() {
        return drinkName;
    }

    @Override
    public double getTotal() {
        return price;
    }

    @Override
    public String getDisplayText() {
        StringBuilder text = new StringBuilder();

        text.append("<u>Selected Drink</u><br>");
        text.append("Drink: ").append(drinkName).append("<br>");
        text.append("Size: ").append(size).append("<br>");
        text.append("Iced: ").append(iced ? "Yes" : "No").append("<br>");

        text.append("Special Instructions:<br>");

        if (specialInstructions == null || specialInstructions.trim().isEmpty()) {
            text.append("None");
        } else {
            text.append(specialInstructions.trim());
        }

        return text.toString();
    }

    @Override
    public String toFileText() {
        String cleanSpecialInstructions;

        if (specialInstructions == null || specialInstructions.trim().isEmpty()) {
            cleanSpecialInstructions = "None";
        } else {
            cleanSpecialInstructions = specialInstructions
                    .trim()
                    .replace("|", "/")
                    .replace("\n", " ")
                    .replace("\r", " ");
        }

        return drinkName
                + "|"
                + size
                + "|"
                + (iced ? "Iced" : "Not Iced")
                + "|"
                + cleanSpecialInstructions;
    }
}