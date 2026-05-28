import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SmallReceiptFrame extends JFrame {

    SmallReceiptFrame(Coffee coffee) {
        this.setTitle("Ever Green Coffee - Receipt");
        this.setSize(360, 520);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        receiptArea.setText(buildReceiptText(coffee, timestamp));

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

    private String buildReceiptText(Coffee coffee, String timestamp) {
        String[] parts = coffee.toFileText().split("\\|");

        String coffeeType = parts[0];
        String size = parts[1];
        String milk = parts[2];
        String temperature = parts[3];
        String extras = parts[4];
        String addOns = parts[5];
        String specialInstructions = parts[6];

        return
                "================================\n" +
                        "       EVER GREEN COFFEE        \n" +
                        "================================\n" +
                        "Customer: " + coffee.getCustomerName() + "\n" +
                        "Date/Time: " + timestamp + "\n" +
                        "--------------------------------\n" +
                        "Drink: " + coffeeType + "\n" +
                        "Size: " + size + "\n" +
                        "Milk: " + milk + "\n" +
                        "Temperature: " + temperature + "\n" +
                        "Extras: " + extras + "\n" +
                        "Add-ons: " + addOns + "\n" +
                        "Special: " + specialInstructions + "\n" +
                        "--------------------------------\n" +
                        "TOTAL: $" + String.format("%.2f", coffee.getTotal()) + "\n" +
                        "================================\n" +
                        "     Thank you for visiting!    \n" +
                        "================================\n";
    }
}