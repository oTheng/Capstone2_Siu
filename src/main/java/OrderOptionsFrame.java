import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OrderOptionsFrame extends JFrame {
    Coffee selectedCoffee;
    JButton addItemButton;
    JButton addDrinkButton;
    JButton addMainSideButton;
    JButton checkoutButton;
    JButton cancelOrderButton;

    JLabel customerNameLabel;
    JLabel selectedItemLabel;

    String customerName;
    double selectedItemPrice = 0.0;

    OrderOptionsFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Ever Green Coffee - Order Options");
        this.setSize(800, 800);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        ImageIcon image = new ImageIcon(getClass().getResource("/EGC.png"));

        Image resizedImage = image.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        this.setIconImage(resizedImage);

        Image scaledLogo = image.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));

        Border border = BorderFactory.createLineBorder(
                Color.getHSBColor(0.34f, 0.20f, 0.21f),
                4
        );

        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(border);

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        topBar.add(logoLabel, BorderLayout.CENTER);

        this.add(topBar, BorderLayout.NORTH);

        customerName = JOptionPane.showInputDialog(
                this,
                "Enter customer name:",
                "Customer Name",
                JOptionPane.QUESTION_MESSAGE
        );

        if (customerName == null || customerName.trim().isEmpty()) {
            customerName = "Guest";
        }

        JLabel titleLabel = new JLabel("What would you like to do?");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        addItemButton = createOptionButton("1) Add Item");
        addDrinkButton = createOptionButton("2) Add Drink");
        addMainSideButton = createOptionButton("3) Add Main Side");
        checkoutButton = createOptionButton("4) Checkout");
        cancelOrderButton = createOptionButton("0) Cancel Order");

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBackground(Color.WHITE);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 40));

        addItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addDrinkButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMainSideButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        optionsPanel.add(addItemButton);
        optionsPanel.add(Box.createVerticalStrut(15));
        optionsPanel.add(addDrinkButton);
        optionsPanel.add(Box.createVerticalStrut(15));
        optionsPanel.add(addMainSideButton);
        optionsPanel.add(Box.createVerticalStrut(15));
        optionsPanel.add(checkoutButton);
        optionsPanel.add(Box.createVerticalStrut(15));
        optionsPanel.add(cancelOrderButton);

        JPanel selectedHolderPanel = new JPanel(new BorderLayout());
        selectedHolderPanel.setBackground(Color.WHITE);
        selectedHolderPanel.setBorder(BorderFactory.createTitledBorder("Selected Order"));

        customerNameLabel = new JLabel("Customer: " + customerName);
        customerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        customerNameLabel.setFont(new Font("Serif", Font.BOLD, 18));

        selectedItemLabel = new JLabel("No item selected");
        selectedItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedItemLabel.setVerticalAlignment(SwingConstants.TOP);
        selectedItemLabel.setFont(new Font("Serif", Font.PLAIN, 16));

        JScrollPane selectedScrollPane = new JScrollPane(selectedItemLabel);
        selectedScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        selectedScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        selectedHolderPanel.add(customerNameLabel, BorderLayout.NORTH);
        selectedHolderPanel.add(selectedScrollPane, BorderLayout.CENTER);

        JPanel mainContentPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        mainContentPanel.setBackground(Color.WHITE);
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        mainContentPanel.add(optionsPanel);
        mainContentPanel.add(selectedHolderPanel);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(titleLabel, BorderLayout.NORTH);
        centerPanel.add(mainContentPanel, BorderLayout.CENTER);

        this.add(centerPanel, BorderLayout.CENTER);

        addItemButton.addActionListener(e -> {
            new ItemCustomizationFrame(this);
        });

        addDrinkButton.addActionListener(e -> {
            new OrderMenuFrame(this);
        });

        addMainSideButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Main Side menu coming soon!");
        });

        checkoutButton.addActionListener(e -> {
            saveOrderToCSV();
        });

        cancelOrderButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Order cancelled.");
            dispose();
        });

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setSelectedItem(String itemName, double price) {
        selectedItemPrice = price;

        selectedItemLabel.setText(
                "<html><div style='text-align:center; width:250px;'>"
                        + itemName
                        + "<br><br><b>Price: $" + String.format("%.2f", price) + "</b>"
                        + "</div></html>"
        );
    }
    public void setSelectedCoffee(Coffee coffee) {
        selectedCoffee = coffee;
        selectedItemPrice = coffee.getPrice();

        selectedItemLabel.setText(
                "<html><div style='text-align:center; width:250px;'>"
                        + coffee.getDisplayText()
                        + "<br><br>"
                        + coffee.getPriceBreakdownText()
                        + "</div></html>"
        );
    }
    private void saveOrderToCSV() {
        if (selectedCoffee == null) {
            JOptionPane.showMessageDialog(this, "Please select an item before checkout.");
            return;
        }
        OrderCSVWriter.writeCoffeeOrder(selectedCoffee);

        JOptionPane.showMessageDialog(
                this,
                "Order saved successfully!\nTotal: $" + String.format("%.2f", selectedCoffee.getTotal())
        );
        dispose();
    }
    private JButton createOptionButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 55));
        button.setMaximumSize(new Dimension(250, 55));
        button.setFont(new Font("Serif", Font.BOLD, 18));
        button.setFocusable(false);
        button.setBackground(new Color(245, 245, 245));
        button.setForeground(Color.BLACK);
        return button;
    }
}