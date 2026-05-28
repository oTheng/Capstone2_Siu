import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SignatureItemFrame extends JFrame {

    private OrderOptionsFrame orderOptionsFrame;

    private JButton breakfastComboButton;
    private JButton icedLatteComboButton;
    private JButton classicMorningButton;
    private JButton sweetTreatButton;
    private JButton cancelButton;

    SignatureItemFrame(OrderOptionsFrame orderOptionsFrame) {
        this.orderOptionsFrame = orderOptionsFrame;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Ever Green Coffee - Signature Items");
        this.setSize(750, 700);
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

        JLabel titleLabel = new JLabel("Choose a Signature Item");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 26));

        breakfastComboButton = createSignatureButton(
                "<html><center>Ever Green Breakfast Combo<br>$14.99</center></html>"
        );

        icedLatteComboButton = createSignatureButton(
                "<html><center>Iced Latte Morning Set<br>$11.99</center></html>"
        );

        classicMorningButton = createSignatureButton(
                "<html><center>Classic Morning Plate<br>$12.49</center></html>"
        );

        sweetTreatButton = createSignatureButton(
                "<html><center>Sweet Treat Coffee Combo<br>$9.99</center></html>"
        );

        JPanel signaturePanel = new JPanel(new GridLayout(2, 2, 25, 25));
        signaturePanel.setBackground(Color.WHITE);
        signaturePanel.setBorder(BorderFactory.createEmptyBorder(40, 70, 40, 70));

        signaturePanel.add(breakfastComboButton);
        signaturePanel.add(icedLatteComboButton);
        signaturePanel.add(classicMorningButton);
        signaturePanel.add(sweetTreatButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(160, 45));
        cancelButton.setFocusable(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(cancelButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(titleLabel, BorderLayout.NORTH);
        centerPanel.add(signaturePanel, BorderLayout.CENTER);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.add(centerPanel, BorderLayout.CENTER);

        breakfastComboButton.addActionListener(e -> addSignatureItem(
                "Ever Green Breakfast Combo",
                "Large Latte, Breakfast Plate, Eggs, Bacon, Hash Browns",
                14.99
        ));

        icedLatteComboButton.addActionListener(e -> addSignatureItem(
                "Iced Latte Morning Set",
                "Iced Latte, Vanilla, Bacon Plate, Toast",
                11.99
        ));

        classicMorningButton.addActionListener(e -> addSignatureItem(
                "Classic Morning Plate",
                "Hot Coffee, Eggs, Ham, Toast, Hash Browns",
                12.49
        ));

        sweetTreatButton.addActionListener(e -> addSignatureItem(
                "Sweet Treat Coffee Combo",
                "Mocha, Whipped Cream, Cinnamon, Sweet Breakfast Side",
                9.99
        ));

        cancelButton.addActionListener(e -> {
            dispose();
        });

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JButton createSignatureButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(250, 130));
        button.setFont(new Font("Serif", Font.BOLD, 18));
        button.setFocusable(false);
        button.setBackground(new Color(245, 245, 245));
        button.setForeground(Color.BLACK);
        return button;
    }

    private void addSignatureItem(String name, String description, double price) {
        SignatureItem signatureItem = new SignatureItem(
                name,
                description,
                price
        );

        orderOptionsFrame.addItemToOrder(signatureItem);

        dispose();
    }
}