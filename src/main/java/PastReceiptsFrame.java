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
                "Coffee",
                "Size",
                "Milk",
                "Temperature",
                "Extras",
                "Add-ons",
                "Special Instructions",
                "Price",
                "Date/Time"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length >= 10) {
                    model.addRow(new Object[]{
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4],
                            parts[5],
                            parts[6],
                            parts[7],
                            "$" + parts[8],
                            parts[9]
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
        receiptsTable.setRowHeight(30);
        receiptsTable.setFont(new Font("Serif", Font.PLAIN, 14));
        receiptsTable.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));

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