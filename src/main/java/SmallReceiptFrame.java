import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SmallReceiptFrame extends JFrame {

    SmallReceiptFrame(Order order) {
        this.setTitle("Ever Green Coffee - Receipt");
        this.setSize(380, 560);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        receiptArea.setText(buildReceiptText(order, timestamp));

        JScrollPane scrollPane = new JScrollPane(receiptArea);

        JButton closeButton = new JButton("Close Receipt");
        closeButton.setFocusable(false);
        closeButton.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = screenSize.width - this.getWidth() - 40;
        int y = 100;

        this.setLocation(x, y);
        this.setVisible(true);
    }

    private String buildReceiptText(Order order, String timestamp) {
        StringBuilder receipt = new StringBuilder();

        receipt.append("================================\n");
        receipt.append("       EVER GREEN COFFEE        \n");
        receipt.append("================================\n");
        receipt.append("Customer: ").append(order.getCustomerName()).append("\n");
        receipt.append("Date/Time: ").append(timestamp).append("\n");
        receipt.append("--------------------------------\n");

        for (int i = 0; i < order.getItems().size(); i++) {
            OrderItem item = order.getItems().get(i);

            receipt.append("Item ").append(i + 1).append("\n");

            receipt.append(
                    item.getDisplayText()
                            .replace("<u>", "")
                            .replace("</u>", "")
                            .replace("<b>", "")
                            .replace("</b>", "")
                            .replace("<br>", "\n")
            );

            receipt.append("\n");
            receipt.append("Price: $")
                    .append(String.format("%.2f", item.getTotal()))
                    .append("\n");

            receipt.append("--------------------------------\n");
        }
        receipt.append("TOTAL: $")
                .append(String.format("%.2f", order.getTotal()))
                .append("\n");

        receipt.append("================================\n");
        receipt.append("     Thank you for visiting!    \n");
        receipt.append("================================\n");

        return receipt.toString();
    }
}