import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DrinkMenuFrame extends JFrame {

    private OrderOptionsFrame orderOptionsFrame;

    private JComboBox<String> drinkComboBox;
    private JComboBox<String> sizeComboBox;
    private JCheckBox icedCheckBox;
    private JTextArea specialInstructionsArea;

    private JButton addToOrderButton;
    private JButton cancelButton;

    DrinkMenuFrame(OrderOptionsFrame orderOptionsFrame) {
        this.orderOptionsFrame = orderOptionsFrame;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Ever Green Coffee - Drinks");
        this.setSize(600, 600);
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

        JLabel titleLabel = new JLabel("Add Drink");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 26));

        drinkComboBox = new JComboBox<>(new String[]{
                "Water",
                "Soda",
                "Lemonade",
                "Iced Tea",
                "Orange Juice"
        });

        sizeComboBox = new JComboBox<>(new String[]{
                "Small",
                "Medium",
                "Large"
        });

        icedCheckBox = new JCheckBox("Iced");

        specialInstructionsArea = new JTextArea(4, 20);
        specialInstructionsArea.setLineWrap(true);
        specialInstructionsArea.setWrapStyleWord(true);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        formPanel.add(createLabel("Drink:"));
        formPanel.add(drinkComboBox);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Size:"));
        formPanel.add(sizeComboBox);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Options:"));
        formPanel.add(icedCheckBox);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Special Instructions:"));
        formPanel.add(new JScrollPane(specialInstructionsArea));

        addToOrderButton = new JButton("Add Drink To Order");
        cancelButton = new JButton("Cancel");

        addToOrderButton.setPreferredSize(new Dimension(180, 45));
        cancelButton.setPreferredSize(new Dimension(120, 45));

        addToOrderButton.setFocusable(false);
        cancelButton.setFocusable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addToOrderButton);
        buttonPanel.add(cancelButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(titleLabel, BorderLayout.NORTH);
        centerPanel.add(formPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(centerPanel, BorderLayout.CENTER);

        addToOrderButton.addActionListener(e -> addDrinkToOrder());

        cancelButton.addActionListener(e -> {
            dispose();
        });

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.BOLD, 18));
        return label;
    }

    private void addDrinkToOrder() {
        String drinkName = (String) drinkComboBox.getSelectedItem();
        String size = (String) sizeComboBox.getSelectedItem();
        boolean iced = icedCheckBox.isSelected();
        String specialInstructions = specialInstructionsArea.getText().trim();

        Drink drink = new Drink(
                drinkName,
                size,
                iced,
                specialInstructions
        );

        orderOptionsFrame.addItemToOrder(drink);

        dispose();
    }
}