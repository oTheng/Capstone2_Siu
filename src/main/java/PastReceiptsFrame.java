import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class PastReceiptsFrame extends JFrame {

    private static final String FILE_PATH = "src/main/resources/orders.csv";

    PastReceiptsFrame() {
        this.setTitle("Ever Green Coffee - Past Receipts");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        JLabel titleLabel = new JLabel("Past Receipts");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String[] columns = {
                "Customer",
                "Items",
                "Total",
                "Date/Time"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length >= 4) {
                    String customer = parts[0];
                    String dateTime = parts[parts.length - 1];
                    String total = parts[parts.length - 2];

                    StringBuilder items = new StringBuilder();

                    for (int i = 1; i < parts.length - 2; i++) {
                        items.append(parts[i]);

                        if (i < parts.length - 3) {
                            items.append(" | ");
                        }
                    }

                    model.addRow(new Object[]{
                            customer,
                            items.toString(),
                            "$" + total,
                            dateTime
                    });
                }
            }

            reader.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Could not load past receipts."
            );
        }

        JTable receiptsTable = new JTable(model);
        receiptsTable.setRowHeight(35);
        receiptsTable.setFont(new Font("Serif", Font.PLAIN, 14));
        receiptsTable.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));

        receiptsTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        receiptsTable.getColumnModel().getColumn(1).setPreferredWidth(600);
        receiptsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        receiptsTable.getColumnModel().getColumn(3).setPreferredWidth(150);

        JScrollPane scrollPane = new JScrollPane(receiptsTable);

        JButton closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(160, 45));
        closeButton.setFocusable(false);
        closeButton.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeButton);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}