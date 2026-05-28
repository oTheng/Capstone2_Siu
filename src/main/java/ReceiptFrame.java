import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReceiptFrame extends JFrame {

    ReceiptFrame(Coffee coffee) {
        this.setTitle("Ever Green Coffee - Receipt");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        JLabel titleLabel = new JLabel("Receipt");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String[] columns = {
                "Customer",
                "Coffee",
                "Size",
                "Milk",
                "Temperature",
                "Extras",
                "Add-ons",
                "Special Instructions",
                "Price"
        };

        String[] orderParts = coffee.toFileText().split("\\|");

        String coffeeType = orderParts[0];
        String size = orderParts[1];
        String milk = orderParts[2];
        String temperature = orderParts[3];
        String extras = orderParts[4];
        String addOns = orderParts[5];
        String specialInstructions = orderParts[6];

        Object[][] data = {
                {
                        coffee.getCustomerName(),
                        coffeeType,
                        size,
                        milk,
                        temperature,
                        extras,
                        addOns,
                        specialInstructions,
                        "$" + String.format("%.2f", coffee.getTotal())
                }
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable receiptTable = new JTable(model);
        receiptTable.setRowHeight(30);
        receiptTable.setFont(new Font("Serif", Font.PLAIN, 14));
        receiptTable.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(receiptTable);

        JLabel totalLabel = new JLabel("Total: $" + String.format("%.2f", coffee.getTotal()));
        totalLabel.setFont(new Font("Serif", Font.BOLD, 22));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(160, 45));
        closeButton.setFocusable(false);
        closeButton.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(totalLabel, BorderLayout.CENTER);
        bottomPanel.add(closeButton, BorderLayout.SOUTH);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}