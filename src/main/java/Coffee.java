public class Coffee {

    private String customerName;
    private String coffeeType;
    private String size;
    private String milk;
    private String temperature;

    private int extraEspressoShots;
    private int vanillaPumps;
    private int caramelPumps;
    private int mochaPumps;
    private int hazelnutPumps;

    private boolean whippedCream;
    private boolean sugar;
    private boolean cinnamon;

    private String specialInstructions;
    private double price;

    public Coffee(
            String customerName,
            String coffeeType,
            String size,
            String milk,
            String temperature,
            int extraEspressoShots,
            int vanillaPumps,
            int caramelPumps,
            int mochaPumps,
            int hazelnutPumps,
            boolean whippedCream,
            boolean sugar,
            boolean cinnamon,
            String specialInstructions
    ) {
        this.customerName = customerName;
        this.coffeeType = coffeeType;
        this.size = size;
        this.milk = milk;
        this.temperature = temperature;
        this.extraEspressoShots = extraEspressoShots;
        this.vanillaPumps = vanillaPumps;
        this.caramelPumps = caramelPumps;
        this.mochaPumps = mochaPumps;
        this.hazelnutPumps = hazelnutPumps;
        this.whippedCream = whippedCream;
        this.sugar = sugar;
        this.cinnamon = cinnamon;
        this.specialInstructions = specialInstructions;

        this.price = calculatePrice();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public boolean isCinnamon() {
        return cinnamon;
    }

    public void setCinnamon(boolean cinnamon) {
        this.cinnamon = cinnamon;
    }

    public boolean isSugar() {
        return sugar;
    }

    public void setSugar(boolean sugar) {
        this.sugar = sugar;
    }

    public boolean isWhippedCream() {
        return whippedCream;
    }

    public void setWhippedCream(boolean whippedCream) {
        this.whippedCream = whippedCream;
    }

    public int getHazelnutPumps() {
        return hazelnutPumps;
    }

    public void setHazelnutPumps(int hazelnutPumps) {
        this.hazelnutPumps = hazelnutPumps;
    }

    public int getMochaPumps() {
        return mochaPumps;
    }

    public void setMochaPumps(int mochaPumps) {
        this.mochaPumps = mochaPumps;
    }

    public int getCaramelPumps() {
        return caramelPumps;
    }

    public void setCaramelPumps(int caramelPumps) {
        this.caramelPumps = caramelPumps;
    }

    public int getVanillaPumps() {
        return vanillaPumps;
    }

    public void setVanillaPumps(int vanillaPumps) {
        this.vanillaPumps = vanillaPumps;
    }

    public int getExtraEspressoShots() {
        return extraEspressoShots;
    }

    public void setExtraEspressoShots(int extraEspressoShots) {
        this.extraEspressoShots = extraEspressoShots;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    private double calculatePrice() {
        double total = 0.0;

        if (coffeeType.equals("Latte")) {
            total = 4.50;
        } else if (coffeeType.equals("Cappuccino")) {
            total = 4.25;
        } else if (coffeeType.equals("Americano")) {
            total = 3.50;
        } else if (coffeeType.equals("Mocha")) {
            total = 5.00;
        } else if (coffeeType.equals("Cold Brew")) {
            total = 4.75;
        } else if (coffeeType.equals("Espresso")) {
            total = 3.00;
        }

        if (size.equals("Medium")) {
            total += 0.75;
        } else if (size.equals("Large")) {
            total += 1.50;
        }

        if (milk.equals("Oat Milk") || milk.equals("Almond Milk") || milk.equals("Soy Milk")) {
            total += 0.75;
        }

        if (temperature.equals("Iced")) {
            total += 0.50;
        }

        total += extraEspressoShots * 1.00;
        total += vanillaPumps * 0.50;
        total += caramelPumps * 0.50;
        total += mochaPumps * 0.50;
        total += hazelnutPumps * 0.50;

        if (whippedCream) {
            total += 0.75;
        }

        return total;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDisplayText() {
        StringBuilder text = new StringBuilder();

        text.append("<u>Selected Coffee</u><br>");
        text.append("Coffee: ").append(coffeeType).append("<br>");
        text.append("Size: ").append(size).append("<br>");
        text.append("Milk: ").append(milk).append("<br>");
        text.append("Temperature: ").append(temperature).append("<br><br>");

        text.append("Extras:<br>");

        if (extraEspressoShots > 0) {
            text.append("Extra Espresso Shots x").append(extraEspressoShots).append("<br>");
        }

        if (vanillaPumps > 0) {
            text.append("Vanilla Pumps x").append(vanillaPumps).append("<br>");
        }

        if (caramelPumps > 0) {
            text.append("Caramel Pumps x").append(caramelPumps).append("<br>");
        }

        if (mochaPumps > 0) {
            text.append("Mocha Pumps x").append(mochaPumps).append("<br>");
        }

        if (hazelnutPumps > 0) {
            text.append("Hazelnut Pumps x").append(hazelnutPumps).append("<br>");
        }

        text.append("<br>Add-ons:<br>");

        boolean hasAddOn = false;

        if (whippedCream) {
            text.append("Whipped Cream<br>");
            hasAddOn = true;
        }

        if (sugar) {
            text.append("Sugar<br>");
            hasAddOn = true;
        }

        if (cinnamon) {
            text.append("Cinnamon<br>");
            hasAddOn = true;
        }

        if (!hasAddOn) {
            text.append("None<br>");
        }

        text.append("<br>Special Instructions:<br>");

        if (specialInstructions == null || specialInstructions.trim().isEmpty()) {
            text.append("None");
        } else {
            text.append(specialInstructions);
        }

        return text.toString();
    }

    public String toFileText() {
        StringBuilder text = new StringBuilder();

        text.append(coffeeType);
        text.append("|").append(size);

        if (milk.equals("No Milk")) {
            text.append("|None");
        } else {
            text.append("|").append(milk);
        }

        text.append("|").append(temperature);

        StringBuilder extras = new StringBuilder();

        if (extraEspressoShots > 0) {
            extras.append("Extra Espresso Shots x").append(extraEspressoShots).append(", ");
        }

        if (vanillaPumps > 0) {
            extras.append("Vanilla Pumps x").append(vanillaPumps).append(", ");
        }

        if (caramelPumps > 0) {
            extras.append("Caramel Pumps x").append(caramelPumps).append(", ");
        }

        if (mochaPumps > 0) {
            extras.append("Mocha Pumps x").append(mochaPumps).append(", ");
        }

        if (hazelnutPumps > 0) {
            extras.append("Hazelnut Pumps x").append(hazelnutPumps).append(", ");
        }

        if (extras.length() > 0) {
            extras.setLength(extras.length() - 2);
            text.append("|").append(extras);
        } else {
            text.append("|None");
        }

        StringBuilder addOns = new StringBuilder();

        if (whippedCream) {
            addOns.append("Whipped Cream, ");
        }

        if (sugar) {
            addOns.append("Sugar, ");
        }

        if (cinnamon) {
            addOns.append("Cinnamon, ");
        }

        if (addOns.length() > 0) {
            addOns.setLength(addOns.length() - 2);
            text.append("|").append(addOns);
        } else {
            text.append("|None");
        }

        if (specialInstructions == null || specialInstructions.trim().isEmpty()) {
            text.append("|None");
        } else {
            text.append("|").append(specialInstructions.trim());
        }

        return text.toString();
    }
    public String getPriceBreakdownText() {
        StringBuilder text = new StringBuilder();

        text.append("<u>Price Breakdown</u><br>");

        if (coffeeType.equals("Latte")) {
            text.append("Latte: $4.50<br>");
        } else if (coffeeType.equals("Cappuccino")) {
            text.append("Cappuccino: $4.25<br>");
        } else if (coffeeType.equals("Americano")) {
            text.append("Americano: $3.50<br>");
        } else if (coffeeType.equals("Mocha")) {
            text.append("Mocha: $5.00<br>");
        } else if (coffeeType.equals("Cold Brew")) {
            text.append("Cold Brew: $4.75<br>");
        } else if (coffeeType.equals("Espresso")) {
            text.append("Espresso: $3.00<br>");
        }

        if (size.equals("Medium")) {
            text.append("Medium Size: $0.75<br>");
        } else if (size.equals("Large")) {
            text.append("Large Size: $1.50<br>");
        }

        if (milk.equals("Oat Milk") || milk.equals("Almond Milk") || milk.equals("Soy Milk")) {
            text.append(milk).append(": $0.75<br>");
        }

        if (temperature.equals("Iced")) {
            text.append("Iced: $0.50<br>");
        }

        if (extraEspressoShots > 0) {
            text.append("Extra Espresso Shots x")
                    .append(extraEspressoShots)
                    .append(": $")
                    .append(String.format("%.2f", extraEspressoShots * 1.00))
                    .append("<br>");
        }

        if (vanillaPumps > 0) {
            text.append("Vanilla Pumps x")
                    .append(vanillaPumps)
                    .append(": $")
                    .append(String.format("%.2f", vanillaPumps * 0.50))
                    .append("<br>");
        }

        if (caramelPumps > 0) {
            text.append("Caramel Pumps x")
                    .append(caramelPumps)
                    .append(": $")
                    .append(String.format("%.2f", caramelPumps * 0.50))
                    .append("<br>");
        }

        if (mochaPumps > 0) {
            text.append("Mocha Pumps x")
                    .append(mochaPumps)
                    .append(": $")
                    .append(String.format("%.2f", mochaPumps * 0.50))
                    .append("<br>");
        }

        if (hazelnutPumps > 0) {
            text.append("Hazelnut Pumps x")
                    .append(hazelnutPumps)
                    .append(": $")
                    .append(String.format("%.2f", hazelnutPumps * 0.50))
                    .append("<br>");
        }

        if (whippedCream) {
            text.append("Whipped Cream: $0.75<br>");
        }

        if (sugar) {
            text.append("Sugar: $0.00<br>");
        }

        if (cinnamon) {
            text.append("Cinnamon: $0.00<br>");
        }

        text.append("<br><b>Total: $")
                .append(String.format("%.2f", getTotal()))
                .append("</b>");

        return text.toString();
    }
}