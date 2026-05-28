import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ItemCustomizationFrame extends JFrame {

    private OrderOptionsFrame orderOptionsFrame;

    private JComboBox<String> coffeeTypeComboBox;
    private JComboBox<String> sizeComboBox;
    private JComboBox<String> milkComboBox;
    private JComboBox<String> temperatureComboBox;

    private JSpinner espressoShotsSpinner;
    private JSpinner vanillaSpinner;
    private JSpinner caramelSpinner;
    private JSpinner mochaSpinner;
    private JSpinner hazelnutSpinner;

    private JCheckBox whippedCreamCheckBox;
    private JCheckBox sugarCheckBox;
    private JCheckBox cinnamonCheckBox;

    private JTextArea specialInstructionsArea;

    private JButton addToOrderButton;
    private JButton cancelButton;

    ItemCustomizationFrame(OrderOptionsFrame orderOptionsFrame) {
        this.orderOptionsFrame = orderOptionsFrame;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Ever Green Coffee - Customize Coffee");
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

        JLabel titleLabel = new JLabel("Customize Your Coffee");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        coffeeTypeComboBox = new JComboBox<>(new String[]{
                "Latte",
                "Cappuccino",
                "Americano",
                "Mocha",
                "Cold Brew",
                "Espresso"
        });

        sizeComboBox = new JComboBox<>(new String[]{
                "Small",
                "Medium",
                "Large"
        });

        milkComboBox = new JComboBox<>(new String[]{
                "Whole Milk",
                "Oat Milk",
                "Almond Milk",
                "Soy Milk",
                "No Milk"
        });

        temperatureComboBox = new JComboBox<>(new String[]{
                "Hot",
                "Iced"
        });

        formPanel.add(createSectionLabel("Coffee Type:"));
        formPanel.add(coffeeTypeComboBox);
        formPanel.add(Box.createVerticalStrut(12));

        formPanel.add(createSectionLabel("Coffee Size:"));
        formPanel.add(sizeComboBox);
        formPanel.add(Box.createVerticalStrut(12));

        formPanel.add(createSectionLabel("Milk Choice:"));
        formPanel.add(milkComboBox);
        formPanel.add(Box.createVerticalStrut(12));

        formPanel.add(createSectionLabel("Hot or Iced:"));
        formPanel.add(temperatureComboBox);
        formPanel.add(Box.createVerticalStrut(12));

        espressoShotsSpinner = createQuantitySpinner();
        vanillaSpinner = createQuantitySpinner();
        caramelSpinner = createQuantitySpinner();
        mochaSpinner = createQuantitySpinner();
        hazelnutSpinner = createQuantitySpinner();

        formPanel.add(createSectionLabel("Extras:"));
        formPanel.add(createSpinnerRow("Extra Espresso Shots", espressoShotsSpinner));
        formPanel.add(createSpinnerRow("Vanilla Pumps", vanillaSpinner));
        formPanel.add(createSpinnerRow("Caramel Pumps", caramelSpinner));
        formPanel.add(createSpinnerRow("Mocha Pumps", mochaSpinner));
        formPanel.add(createSpinnerRow("Hazelnut Pumps", hazelnutSpinner));
        formPanel.add(Box.createVerticalStrut(12));

        whippedCreamCheckBox = new JCheckBox("Whipped Cream");
        sugarCheckBox = new JCheckBox("Sugar");
        cinnamonCheckBox = new JCheckBox("Cinnamon");

        JPanel toppingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toppingPanel.setBackground(Color.WHITE);
        toppingPanel.add(whippedCreamCheckBox);
        toppingPanel.add(sugarCheckBox);
        toppingPanel.add(cinnamonCheckBox);

        formPanel.add(createSectionLabel("Toppings / Add-ons:"));
        formPanel.add(toppingPanel);
        formPanel.add(Box.createVerticalStrut(12));

        specialInstructionsArea = new JTextArea(4, 20);
        specialInstructionsArea.setLineWrap(true);
        specialInstructionsArea.setWrapStyleWord(true);

        JScrollPane specialScrollPane = new JScrollPane(specialInstructionsArea);

        formPanel.add(createSectionLabel("Would you like the coffee specialized?"));
        formPanel.add(specialScrollPane);

        addToOrderButton = new JButton("Add To Order");
        cancelButton = new JButton("Cancel");

        addToOrderButton.setPreferredSize(new Dimension(180, 45));
        cancelButton.setPreferredSize(new Dimension(180, 45));

        addToOrderButton.setFocusable(false);
        cancelButton.setFocusable(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addToOrderButton);
        buttonPanel.add(cancelButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(titleLabel, BorderLayout.NORTH);
        centerPanel.add(formPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(centerPanel, BorderLayout.CENTER);

        addToOrderButton.addActionListener(e -> addCustomizedCoffeeToOrder());

        cancelButton.addActionListener(e -> {
            dispose();
        });

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JLabel createSectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.BOLD, 18));
        label.setForeground(Color.BLACK);
        return label;
    }

    private JSpinner createQuantitySpinner() {
        return new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
    }

    private JPanel createSpinnerRow(String labelText, JSpinner spinner) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(180, 25));

        row.add(label);
        row.add(spinner);

        return row;
    }

    private void addCustomizedCoffeeToOrder() {
        String coffeeType = (String) coffeeTypeComboBox.getSelectedItem();
        String size = (String) sizeComboBox.getSelectedItem();
        String milk = (String) milkComboBox.getSelectedItem();
        String temperature = (String) temperatureComboBox.getSelectedItem();

        int extraEspressoShots = (int) espressoShotsSpinner.getValue();
        int vanillaPumps = (int) vanillaSpinner.getValue();
        int caramelPumps = (int) caramelSpinner.getValue();
        int mochaPumps = (int) mochaSpinner.getValue();
        int hazelnutPumps = (int) hazelnutSpinner.getValue();

        boolean whippedCream = whippedCreamCheckBox.isSelected();
        boolean sugar = sugarCheckBox.isSelected();
        boolean cinnamon = cinnamonCheckBox.isSelected();

        String specialInstructions = specialInstructionsArea.getText().trim();

        Coffee coffee = new Coffee(
                orderOptionsFrame.customerName,
                coffeeType,
                size,
                milk,
                temperature,
                extraEspressoShots,
                vanillaPumps,
                caramelPumps,
                mochaPumps,
                hazelnutPumps,
                whippedCream,
                sugar,
                cinnamon,
                specialInstructions
        );

        orderOptionsFrame.addItemToOrder(coffee);

        dispose();
    }
}