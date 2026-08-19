import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class TopUpWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color GAME_BG_START = new Color(251, 246, 240);
    private static final Color GAME_BG_END = new Color(231, 241, 247);
    private static final Color GAME_INK = new Color(26, 47, 66);
    private static final Color GAME_MUTED = new Color(81, 95, 111);
    private static final Color GAME_CORAL = new Color(235, 80, 91);
    private static final Color GAME_SALMON = new Color(247, 143, 128);
    private static final Color GAME_BLUE = new Color(31, 90, 123);
    private static final Color GAME_CYAN = new Color(81, 169, 196);

    private final JLabel headerTitleLabel = new JLabel();
    private final JLabel headerSubtitleLabel = new JLabel();
    private final JLabel nominalLabel = new JLabel();
    private final JTextField nominalField = new JTextField();

    private final JToggleButton valorantButton = new JToggleButton("Valorant");
    private final JToggleButton hsrButton = new JToggleButton("Honkai: Star Rail");
    private final JToggleButton mlbbButton = new JToggleButton("Mobile Legends: Bang Bang");
    private final JToggleButton wuwaButton = new JToggleButton("Wuthering Waves");

    private final JButton confirmButton = new JButton("Konfirmasi");
    private final JButton backButton = new JButton("Kembali");

    public TopUpWindow() {
        initUi();
    }

    private void initUi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ButtonGroup gameGroup = new ButtonGroup();
        gameGroup.add(valorantButton);
        gameGroup.add(hsrButton);
        gameGroup.add(mlbbButton);
        gameGroup.add(wuwaButton);

        backButton.addActionListener(e -> {
            new MainWindow().setVisible(true);
            dispose();
        });

        confirmButton.addActionListener(e -> processTopUp());

        FuturisticUI.BackgroundPanel root = FuturisticUI.background(GAME_BG_START, GAME_BG_END, GAME_CORAL);
        root.setLayout(new BorderLayout(0, 18));
        root.setBorder(new EmptyBorder(18, 22, 22, 22));

        root.add(FuturisticUI.banner("/assets/bgGame.jpg", 340), BorderLayout.NORTH);

        FuturisticUI.GlassPanel panel = FuturisticUI.glass(
            new BorderLayout(18, 18),
            new Color(31, 90, 123, 38),
            new Color(255, 252, 247, 226),
            new Color(226, 240, 247, 214),
            new Color(235, 80, 91, 116)
        );

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        FuturisticUI.title(headerTitleLabel, "Top Up Game", 30);
        headerTitleLabel.setForeground(GAME_INK);
        headerTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        FuturisticUI.body(headerSubtitleLabel, "Pilih game tujuan, lalu masukkan nominal top up dalam Rupiah.", 15);
        headerSubtitleLabel.setForeground(GAME_MUTED);
        headerSubtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(headerTitleLabel);
        header.add(Box.createVerticalStrut(6));
        header.add(headerSubtitleLabel);

        JPanel gameGrid = new JPanel(new GridLayout(2, 2, 14, 14));
        gameGrid.setOpaque(false);

        FuturisticUI.styleToggleButton(valorantButton, GAME_CORAL, GAME_SALMON);
        FuturisticUI.styleToggleButton(hsrButton, GAME_BLUE, GAME_CYAN);
        FuturisticUI.styleToggleButton(mlbbButton, new Color(192, 83, 104), new Color(240, 153, 132));
        FuturisticUI.styleToggleButton(wuwaButton, new Color(42, 61, 82), new Color(102, 130, 148));

        gameGrid.add(valorantButton);
        gameGrid.add(hsrButton);
        gameGrid.add(mlbbButton);
        gameGrid.add(wuwaButton);

        JPanel formStack = new JPanel();
        formStack.setOpaque(false);
        formStack.setLayout(new BoxLayout(formStack, BoxLayout.Y_AXIS));

        FuturisticUI.body(nominalLabel, "Nominal top up (Rupiah)", 15);
        nominalLabel.setForeground(GAME_MUTED);
        nominalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        FuturisticUI.styleTextField(nominalField, GAME_INK, GAME_CORAL, new Color(81, 169, 196));
        nominalField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 54));
        nominalField.setPreferredSize(new Dimension(10, 54));

        JPanel actions = new JPanel(new GridLayout(1, 2, 14, 0));
        actions.setOpaque(false);
        FuturisticUI.styleCommandButton(confirmButton, GAME_CORAL, GAME_SALMON);
        FuturisticUI.styleCommandButton(backButton, GAME_BLUE, GAME_CYAN);
        actions.add(confirmButton);
        actions.add(backButton);

        formStack.add(nominalLabel);
        formStack.add(Box.createVerticalStrut(10));
        formStack.add(nominalField);
        formStack.add(Box.createVerticalStrut(14));
        formStack.add(actions);

        panel.add(header, BorderLayout.NORTH);
        panel.add(gameGrid, BorderLayout.CENTER);
        panel.add(formStack, BorderLayout.SOUTH);

        root.add(panel, BorderLayout.CENTER);
        setContentPane(root);

        FuturisticUI.prepareFrame(this, "Mambo Store - Top Up Game", 1040, 840);
        revalidate();
        repaint();
    }

    private void processTopUp() {
        String selectedGame;
        String currency;
        double rate;

        if (valorantButton.isSelected()) {
            selectedGame = "Valorant";
            currency = "Valorant Points (VP)";
            rate = 1000.0 / 108625.0;
        } else if (hsrButton.isSelected()) {
            selectedGame = "Honkai: Star Rail";
            currency = "Oneiric Shard";
            rate = 60.0 / 16000.0;
        } else if (mlbbButton.isSelected()) {
            selectedGame = "Mobile Legends: Bang Bang";
            currency = "Diamond";
            rate = 110.0 / 30235.0;
        } else if (wuwaButton.isSelected()) {
            selectedGame = "Wuthering Waves";
            currency = "Lunites";
            rate = 60.0 / 14922.0;
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Pilih game terlebih dahulu!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String nominalText = nominalField.getText().trim();
        if (nominalText.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Masukkan nominal top up!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        double nominal;
        try {
            nominal = Double.parseDouble(nominalText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Nominal harus berupa angka!",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        long estimasi = Math.round(nominal * rate);

        String gameId = JOptionPane.showInputDialog(
            this,
            "Game       : " + selectedGame + "\n" +
            "Nominal    : Rp " + String.format("%,.0f", nominal) + "\n" +
            "Estimasi   : " + estimasi + " " + currency + "\n\n" +
            "Masukkan ID " + selectedGame + " kamu:",
            "Konfirmasi Top Up",
            JOptionPane.PLAIN_MESSAGE
        );

        if (gameId != null && !gameId.trim().isEmpty()) {
            String ringkasan = "Game   : " + selectedGame + "\n" +
                               "ID     : " + gameId.trim() + "\n" +
                               "Nominal: Rp " + String.format("%,.0f", nominal) + "\n" +
                               "Estimasi: " + estimasi + " " + currency + "\n";
            PaymentDialog.show(this, ringkasan, nominal);
        }
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

        SwingUtilities.invokeLater(() -> new TopUpWindow().setVisible(true));
    }
}
