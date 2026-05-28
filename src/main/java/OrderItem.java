public interface OrderItem {

    String getDisplayText();

    String toFileText();

    double getTotal();

    String getItemName();
}