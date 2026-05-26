import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaSwingFrame extends JFrame implements ActionListener {

    JButton button1;
    JButton button2;
    JLabel outputLabel;

    JavaSwingFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ever Green Coffee");
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

        JLabel letterLabel = new JLabel("Welcome to Ever Green Coffee Shop!");
        letterLabel.setForeground(Color.BLACK);
        letterLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        letterLabel.setHorizontalAlignment(SwingConstants.CENTER);

        button1 = new JButton("Order");
        button1.setPreferredSize(new Dimension(180, 50));
        button1.setFocusable(false);
        button1.addActionListener(this);

        button2 = new JButton("Exit");
        button2.setPreferredSize(new Dimension(180, 50));
        button2.setFocusable(false);
        button2.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        JPanel titleAndButtonsPanel = new JPanel(new BorderLayout());
        titleAndButtonsPanel.setBackground(Color.WHITE);
        titleAndButtonsPanel.add(letterLabel, BorderLayout.NORTH);
        titleAndButtonsPanel.add(buttonPanel, BorderLayout.CENTER);

        outputLabel = new JLabel("");
        outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outputLabel.setVerticalAlignment(SwingConstants.CENTER);
        outputLabel.setFont(new Font("Serif", Font.BOLD, 32));
        outputLabel.setForeground(Color.WHITE);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        centerPanel.add(titleAndButtonsPanel, BorderLayout.NORTH);
        centerPanel.add(outputLabel, BorderLayout.CENTER);
        this.add(centerPanel, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            this.setVisible(false);

            new OrderOptionsFrame();
        }

        if (e.getSource() == button2) {
            JOptionPane.showMessageDialog(this, "Terminating Ever Green Coffee.");
            try {
                Thread.sleep(1000);
                System.exit(0);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}