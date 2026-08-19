import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JLabel titleLabel = new JLabel();
    private final JLabel descriptionLabel = new JLabel();
    private final JLabel selectionLabel = new JLabel();
    private final JButton gameButton = new JButton("Top up Game");
    private final JButton pulsaButton = new JButton("Pulsa & E-Money");
    private final JButton comingSoonButton = new JButton("Coming Soon");

    public MainWindow() {
        initUi();
    }

    private void initUi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameButton.addActionListener(e -> {
            new TopUpWindow().setVisible(true);
            dispose();
        });

        pulsaButton.addActionListener(e -> {
            new PulsaWindow().setVisible(true);
            dispose();
        });

        comingSoonButton.addActionListener(e -> JOptionPane.showMessageDialog(
            this,
            "Pilihan ini belum tersedia. Pantau terus Mambo Store!",
            "Coming Soon",
            JOptionPane.INFORMATION_MESSAGE
        ));

        FuturisticUI.BackgroundPanel root = FuturisticUI.background();
        root.setLayout(new BorderLayout(0, 18));
        root.setBorder(new EmptyBorder(20, 24, 24, 24));

        FuturisticUI.BannerPanel hero = FuturisticUI.banner("/assets/bannerMain.png", 390);
        root.add(hero, BorderLayout.NORTH);

        FuturisticUI.GlassPanel menuPanel = FuturisticUI.glass(new BorderLayout(18, 18));

        JPanel textStack = new JPanel();
        textStack.setOpaque(false);
        textStack.setLayout(new BoxLayout(textStack, BoxLayout.Y_AXIS));

        FuturisticUI.title(titleLabel, "Mambo Store", 31);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        FuturisticUI.body(
            descriptionLabel,
            "<html><div style='width:780px'>Tempat top up game, pulsa, dan e-money yang cepat, aman, dan siap dipakai kapan saja.</div></html>",
            15
        );
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        FuturisticUI.body(selectionLabel, "Pilih layanan", 16);
        selectionLabel.setForeground(FuturisticUI.VIOLET);
        selectionLabel.setFont(selectionLabel.getFont().deriveFont(Font.BOLD, 16f));
        selectionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        textStack.add(titleLabel);
        textStack.add(Box.createVerticalStrut(6));
        textStack.add(descriptionLabel);
        textStack.add(Box.createVerticalStrut(16));
        textStack.add(selectionLabel);

        JPanel buttonGrid = new JPanel(new GridLayout(1, 3, 18, 0));
        buttonGrid.setOpaque(false);

        FuturisticUI.styleCommandButton(gameButton, FuturisticUI.VIOLET, new Color(172, 132, 255));
        FuturisticUI.styleCommandButton(pulsaButton, new Color(132, 79, 242), new Color(207, 188, 255));
        FuturisticUI.styleCommandButton(comingSoonButton, new Color(92, 62, 168), new Color(167, 132, 238));

        buttonGrid.add(gameButton);
        buttonGrid.add(pulsaButton);
        buttonGrid.add(comingSoonButton);

        menuPanel.add(textStack, BorderLayout.NORTH);
        menuPanel.add(buttonGrid, BorderLayout.CENTER);

        root.add(menuPanel, BorderLayout.CENTER);
        setContentPane(root);

        FuturisticUI.prepareFrame(this, "Mambo Store", 1060, 780);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ignored) {
        }

        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
