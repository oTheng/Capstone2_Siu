import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OrderMenuFrame extends JFrame {

    JButton latteButton;
    JButton cappuccinoButton;
    JButton americanoButton;
    JButton mochaButton;

    OrderOptionsFrame orderOptionsFrame;

    OrderMenuFrame(OrderOptionsFrame orderOptionsFrame) {
        this.orderOptionsFrame = orderOptionsFrame;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Ever Green Coffee - Order Menu");
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

        JLabel letterLabel = new JLabel("Choose One Coffee");
        letterLabel.setForeground(Color.BLACK);
        letterLabel.setFont(new Font("Serif", Font.BOLD, 24));
        letterLabel.setHorizontalAlignment(SwingConstants.CENTER);

        latteButton = createMenuButton("Latte - $4.50");
        cappuccinoButton = createMenuButton("Cappuccino - $4.25");
        americanoButton = createMenuButton("Americano - $3.50");
        mochaButton = createMenuButton("Mocha - $5.00");

        JPanel menuPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        menuPanel.add(latteButton);
        menuPanel.add(cappuccinoButton);
        menuPanel.add(americanoButton);
        menuPanel.add(mochaButton);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(letterLabel, BorderLayout.NORTH);
        centerPanel.add(menuPanel, BorderLayout.CENTER);

        this.add(centerPanel, BorderLayout.CENTER);

        latteButton.addActionListener(e -> selectItem("Latte - $4.50"));
        cappuccinoButton.addActionListener(e -> selectItem("Cappuccino - $4.25"));
        americanoButton.addActionListener(e -> selectItem("Americano - $3.50"));
        mochaButton.addActionListener(e -> selectItem("Mocha - $5.00"));

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void selectItem(String itemName) {
        orderOptionsFrame.setSelectedItem(itemName);
        dispose();
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(220, 100));
        button.setFont(new Font("Serif", Font.BOLD, 18));
        button.setFocusable(false);
        button.setBackground(new Color(245, 245, 245));
        button.setForeground(Color.BLACK);
        return button;
    }
}