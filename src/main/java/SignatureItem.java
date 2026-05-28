public class SignatureItem implements OrderItem {

    private String name;
    private String description;
    private double price;

    public SignatureItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getItemName() {
        return name;
    }

    @Override
    public double getTotal() {
        return price;
    }

    @Override
    public String getDisplayText() {
        return "<u>Signature Item</u><br>"
                + "Item: " + name + "<br>"
                + "Includes: " + description;
    }

    @Override
    public String toFileText() {
        return name
                + "|"
                + description.replace("|", "/")
                + "|Premade Signature Item";
    }
}