import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OrderOptionsFrame extends JFrame {

    JButton addItemButton;
    JButton addDrinkButton;
    JButton addMainSideButton;
    JButton checkoutButton;
    JButton cancelOrderButton;

    JLabel selectedItemLabel;

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
        selectedHolderPanel.setBorder(BorderFactory.createTitledBorder("Selected Item"));

        selectedItemLabel = new JLabel("No item selected");
        selectedItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectedItemLabel.setVerticalAlignment(SwingConstants.CENTER);
        selectedItemLabel.setFont(new Font("Serif", Font.BOLD, 20));

        selectedHolderPanel.add(selectedItemLabel, BorderLayout.CENTER);

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
            new OrderMenuFrame(this);
        });

        addDrinkButton.addActionListener(e -> {
            new OrderMenuFrame(this);
        });

        addMainSideButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Main Side menu coming soon!");
        });

        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Checking out with: " + selectedItemLabel.getText());
        });

        cancelOrderButton.addActionListener(e -> {
            dispose();
        });

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setSelectedItem(String itemName) {
        selectedItemLabel.setText(itemName);
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