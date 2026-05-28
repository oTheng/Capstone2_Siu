public class MainSide implements OrderItem {

    private String dishName;
    private String size;
    private boolean eggs;
    private boolean bacon;
    private boolean ham;
    private boolean toast;
    private boolean hashBrowns;
    private String specialInstructions;
    private double price;

    public MainSide(
            String dishName,
            String size,
            boolean eggs,
            boolean bacon,
            boolean ham,
            boolean toast,
            boolean hashBrowns,
            String specialInstructions
    ) {
        this.dishName = dishName;
        this.size = size;
        this.eggs = eggs;
        this.bacon = bacon;
        this.ham = ham;
        this.toast = toast;
        this.hashBrowns = hashBrowns;
        this.specialInstructions = specialInstructions;
        this.price = calculatePrice();
    }

    private double calculatePrice() {
        double total = 0.0;

        if (dishName.equals("Breakfast Plate")) {
            total = 6.50;
        } else if (dishName.equals("Egg Sandwich")) {
            total = 5.00;
        } else if (dishName.equals("Bacon Plate")) {
            total = 5.75;
        } else if (dishName.equals("Ham Plate")) {
            total = 5.75;
        }

        if (size.equals("Large")) {
            total += 1.50;
        }

        if (eggs) {
            total += 1.50;
        }

        if (bacon) {
            total += 2.00;
        }

        if (ham) {
            total += 2.00;
        }

        if (toast) {
            total += 1.00;
        }

        if (hashBrowns) {
            total += 1.75;
        }

        return total;
    }

    @Override
    public String getItemName() {
        return dishName;
    }

    @Override
    public double getTotal() {
        return price;
    }

    @Override
    public String getDisplayText() {
        StringBuilder text = new StringBuilder();

        text.append("<u>Selected Main Side</u><br>");
        text.append("Dish: ").append(dishName).append("<br>");
        text.append("Size: ").append(size).append("<br>");

        text.append("Sides:<br>");

        boolean hasSide = false;

        if (eggs) {
            text.append("Eggs<br>");
            hasSide = true;
        }

        if (bacon) {
            text.append("Bacon<br>");
            hasSide = true;
        }

        if (ham) {
            text.append("Ham<br>");
            hasSide = true;
        }

        if (toast) {
            text.append("Toast<br>");
            hasSide = true;
        }

        if (hashBrowns) {
            text.append("Hash Browns<br>");
            hasSide = true;
        }

        if (!hasSide) {
            text.append("None<br>");
        }

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
        StringBuilder sides = new StringBuilder();

        if (eggs) {
            sides.append("Eggs, ");
        }

        if (bacon) {
            sides.append("Bacon, ");
        }

        if (ham) {
            sides.append("Ham, ");
        }

        if (toast) {
            sides.append("Toast, ");
        }

        if (hashBrowns) {
            sides.append("Hash Browns, ");
        }

        String sideText;

        if (sides.length() > 0) {
            sides.setLength(sides.length() - 2);
            sideText = sides.toString();
        } else {
            sideText = "None";
        }

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

        return dishName
                + "|"
                + size
                + "|"
                + sideText
                + "|"
                + cleanSpecialInstructions;
    }
}