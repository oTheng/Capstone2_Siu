import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainSideFrame extends JFrame {

    private OrderOptionsFrame orderOptionsFrame;

    private JComboBox<String> dishComboBox;
    private JComboBox<String> sizeComboBox;

    private JCheckBox eggsCheckBox;
    private JCheckBox baconCheckBox;
    private JCheckBox hamCheckBox;
    private JCheckBox toastCheckBox;
    private JCheckBox hashBrownsCheckBox;

    private JTextArea specialInstructionsArea;

    private JButton addToOrderButton;
    private JButton cancelButton;

    MainSideFrame(OrderOptionsFrame orderOptionsFrame) {
        this.orderOptionsFrame = orderOptionsFrame;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Ever Green Coffee - Main Side");
        this.setSize(650, 650);
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

        JLabel titleLabel = new JLabel("Add Main Side");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 26));

        dishComboBox = new JComboBox<>(new String[]{
                "Breakfast Plate",
                "Egg Sandwich",
                "Bacon Plate",
                "Ham Plate"
        });

        sizeComboBox = new JComboBox<>(new String[]{
                "Regular",
                "Large"
        });

        eggsCheckBox = new JCheckBox("Eggs");
        baconCheckBox = new JCheckBox("Bacon");
        hamCheckBox = new JCheckBox("Ham");
        toastCheckBox = new JCheckBox("Toast");
        hashBrownsCheckBox = new JCheckBox("Hash Browns");

        JPanel sidesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sidesPanel.setBackground(Color.WHITE);
        sidesPanel.add(eggsCheckBox);
        sidesPanel.add(baconCheckBox);
        sidesPanel.add(hamCheckBox);
        sidesPanel.add(toastCheckBox);
        sidesPanel.add(hashBrownsCheckBox);

        specialInstructionsArea = new JTextArea(4, 20);
        specialInstructionsArea.setLineWrap(true);
        specialInstructionsArea.setWrapStyleWord(true);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        formPanel.add(createLabel("Dish:"));
        formPanel.add(dishComboBox);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Size:"));
        formPanel.add(sizeComboBox);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Add Sides:"));
        formPanel.add(sidesPanel);
        formPanel.add(Box.createVerticalStrut(15));

        formPanel.add(createLabel("Special Instructions:"));
        formPanel.add(new JScrollPane(specialInstructionsArea));

        addToOrderButton = new JButton("Add Main Side To Order");
        cancelButton = new JButton("Cancel");

        addToOrderButton.setPreferredSize(new Dimension(210, 45));
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

        addToOrderButton.addActionListener(e -> addMainSideToOrder());

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

    private void addMainSideToOrder() {
        String dishName = (String) dishComboBox.getSelectedItem();
        String size = (String) sizeComboBox.getSelectedItem();

        boolean eggs = eggsCheckBox.isSelected();
        boolean bacon = baconCheckBox.isSelected();
        boolean ham = hamCheckBox.isSelected();
        boolean toast = toastCheckBox.isSelected();
        boolean hashBrowns = hashBrownsCheckBox.isSelected();

        String specialInstructions = specialInstructionsArea.getText().trim();

        MainSide mainSide = new MainSide(
                dishName,
                size,
                eggs,
                bacon,
                ham,
                toast,
                hashBrowns,
                specialInstructions
        );

        orderOptionsFrame.addItemToOrder(mainSide);

        dispose();
    }
}