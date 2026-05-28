import java.util.ArrayList;

public class Order {

    private String customerName;
    private ArrayList<OrderItem> items;

    public Order(String customerName) {
        this.customerName = customerName;
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeLastItem() {
        if (!items.isEmpty()) {
            items.remove(items.size() - 1);
        }
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotal() {
        double total = 0.0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public String getDisplayText() {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);

            text.append("<b>Item ")
                    .append(i + 1)
                    .append("</b><br>");

            text.append(item.getDisplayText());
            text.append("<br>");
            text.append("<b>Price: $")
                    .append(String.format("%.2f", item.getTotal()))
                    .append("</b><br><br>");
        }

        text.append("<b>Order Total: $")
                .append(String.format("%.2f", getTotal()))
                .append("</b>");

        return text.toString();
    }

    public String toFileText() {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);

            text.append("Item ")
                    .append(i + 1)
                    .append(": ")
                    .append(item.toFileText())
                    .append(": ")
                    .append(String.format("%.2f", item.getTotal()));

            if (i < items.size() - 1) {
                text.append(" ; ");
            }
        }

        return text.toString();
    }
}