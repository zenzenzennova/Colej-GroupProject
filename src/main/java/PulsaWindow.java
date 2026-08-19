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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class PulsaWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JLabel headerTitleLabel = new JLabel();
    private final JLabel headerSubtitleLabel = new JLabel();
    private final JLabel nominalLabel = new JLabel();
    private final JTextField nominalField = new JTextField();

    private final JToggleButton pulsaTelkomselButton = new JToggleButton("Pulsa (Telkomsel)");
    private final JToggleButton pulsaAxisButton = new JToggleButton("Pulsa (Axis)");
    private final JToggleButton pulsaTriButton = new JToggleButton("Pulsa (3)");
    private final JToggleButton pulsaXLButton = new JToggleButton("Pulsa (XL)");
    private final JToggleButton pulsaSmartfrenButton = new JToggleButton("Pulsa (Smartfren)");
    private final JToggleButton emoneyGopayButton = new JToggleButton("E-money (Gopay)");
    private final JToggleButton emoneyDanaButton = new JToggleButton("E-money (Dana)");
    private final JToggleButton emoneyOvoButton = new JToggleButton("E-money (OVO)");
    private final JToggleButton emoneyShopeePayButton = new JToggleButton("E-money (ShopeePay)");

    private final JButton confirmButton = new JButton("Konfirmasi");
    private final JButton backButton = new JButton("Kembali");

    public PulsaWindow() {
        initUi();
    }

    private void initUi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ButtonGroup productGroup = new ButtonGroup();
        productGroup.add(pulsaTelkomselButton);
        productGroup.add(pulsaAxisButton);
        productGroup.add(pulsaSmartfrenButton);
        productGroup.add(emoneyGopayButton);
        productGroup.add(pulsaTriButton);
        productGroup.add(emoneyDanaButton);
        productGroup.add(emoneyOvoButton);
        productGroup.add(emoneyShopeePayButton);
        productGroup.add(pulsaXLButton);

        backButton.addActionListener(e -> {
            new MainWindow().setVisible(true);
            dispose();
        });

        confirmButton.addActionListener(e -> processTopUp());

        FuturisticUI.BackgroundPanel root = FuturisticUI.background();
        root.setLayout(new BorderLayout(0, 18));
        root.setBorder(new EmptyBorder(18, 22, 22, 22));

        root.add(FuturisticUI.banner("/assets/bannerPulsa.png", 360), BorderLayout.NORTH);

        FuturisticUI.GlassPanel panel = FuturisticUI.glass(new BorderLayout(18, 18));

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        FuturisticUI.title(headerTitleLabel, "Top Up Pulsa & E-Money", 30);
        headerTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        FuturisticUI.body(headerSubtitleLabel, "Pilih produk tujuan, lalu masukkan nominal top up dalam Rupiah.", 15);
        headerSubtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(headerTitleLabel);
        header.add(Box.createVerticalStrut(6));
        header.add(headerSubtitleLabel);

        JPanel productGrid = new JPanel(new GridLayout(0, 3, 14, 14));
        productGrid.setOpaque(false);

        FuturisticUI.styleToggleButton(pulsaTelkomselButton, new Color(124, 57, 226), new Color(196, 170, 255));
        FuturisticUI.styleToggleButton(pulsaAxisButton, new Color(119, 65, 218), new Color(197, 177, 255));
        FuturisticUI.styleToggleButton(pulsaSmartfrenButton, new Color(108, 72, 194), new Color(190, 162, 250));
        FuturisticUI.styleToggleButton(emoneyGopayButton, new Color(104, 75, 203), new Color(180, 149, 255));
        FuturisticUI.styleToggleButton(pulsaTriButton, new Color(145, 84, 243), new Color(212, 195, 255));
        FuturisticUI.styleToggleButton(emoneyDanaButton, new Color(95, 68, 190), new Color(188, 161, 255));
        FuturisticUI.styleToggleButton(emoneyOvoButton, new Color(126, 72, 221), new Color(203, 184, 255));
        FuturisticUI.styleToggleButton(emoneyShopeePayButton, new Color(136, 82, 232), new Color(217, 202, 255));
        FuturisticUI.styleToggleButton(pulsaXLButton, new Color(84, 62, 158), new Color(177, 145, 244));

        productGrid.add(pulsaTelkomselButton);
        productGrid.add(pulsaAxisButton);
        productGrid.add(pulsaTriButton);
        productGrid.add(pulsaXLButton);
        productGrid.add(pulsaSmartfrenButton);
        productGrid.add(emoneyGopayButton);
        productGrid.add(emoneyDanaButton);
        productGrid.add(emoneyOvoButton);
        productGrid.add(emoneyShopeePayButton);

        JScrollPane productScroll = new JScrollPane(productGrid);
        productScroll.setOpaque(false);
        productScroll.getViewport().setOpaque(false);
        productScroll.setBorder(null);
        productScroll.setPreferredSize(new Dimension(10, 178));
        productScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        productScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel formStack = new JPanel();
        formStack.setOpaque(false);
        formStack.setLayout(new BoxLayout(formStack, BoxLayout.Y_AXIS));

        FuturisticUI.body(nominalLabel, "Nominal top up (Rupiah)", 15);
        nominalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        FuturisticUI.styleTextField(nominalField);
        nominalField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 54));
        nominalField.setPreferredSize(new Dimension(10, 54));

        JPanel actions = new JPanel(new GridLayout(1, 2, 14, 0));
        actions.setOpaque(false);
        FuturisticUI.styleCommandButton(confirmButton, FuturisticUI.VIOLET, new Color(177, 139, 255));
        FuturisticUI.styleCommandButton(backButton, new Color(82, 61, 151), new Color(151, 108, 232));
        actions.add(confirmButton);
        actions.add(backButton);

        formStack.add(nominalLabel);
        formStack.add(Box.createVerticalStrut(10));
        formStack.add(nominalField);
        formStack.add(Box.createVerticalStrut(14));
        formStack.add(actions);

        panel.add(header, BorderLayout.NORTH);
        panel.add(productScroll, BorderLayout.CENTER);
        panel.add(formStack, BorderLayout.SOUTH);

        root.add(panel, BorderLayout.CENTER);
        setContentPane(root);

        FuturisticUI.prepareFrame(this, "Mambo Store - Pulsa & E-Money", 1040, 860);
        revalidate();
        repaint();
    }

    private void processTopUp() {
        String selectedProduct;
        boolean isPulsa;

        if (pulsaTelkomselButton.isSelected()) {
            selectedProduct = "Pulsa Telkomsel";
            isPulsa = true;
        } else if (pulsaAxisButton.isSelected()) {
            selectedProduct = "Pulsa Axis";
            isPulsa = true;
        } else if (pulsaSmartfrenButton.isSelected()) {
            selectedProduct = "Pulsa Smartfren";
            isPulsa = true;
        } else if (emoneyGopayButton.isSelected()) {
            selectedProduct = "E-money GoPay";
            isPulsa = false;
        } else if (pulsaTriButton.isSelected()) {
            selectedProduct = "Pulsa 3 (Tri)";
            isPulsa = true;
        } else if (emoneyDanaButton.isSelected()) {
            selectedProduct = "E-money DANA";
            isPulsa = false;
        } else if (emoneyOvoButton.isSelected()) {
            selectedProduct = "E-money OVO";
            isPulsa = false;
        } else if (emoneyShopeePayButton.isSelected()) {
            selectedProduct = "E-money ShopeePay";
            isPulsa = false;
        } else if (pulsaXLButton.isSelected()) {
            selectedProduct = "Pulsa XL";
            isPulsa = true;
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Pilih produk terlebih dahulu!",
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

        double admin;
        if (isPulsa) {
            if (nominal < 5000) {
                JOptionPane.showMessageDialog(
                    this,
                    "Minimal top up pulsa adalah Rp 5.000!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            long kelipatan = (long) Math.floor(nominal / 50000);
            admin = 500 + (kelipatan * 500);
        } else {
            if (nominal < 10000) {
                JOptionPane.showMessageDialog(
                    this,
                    "Minimal top up e-money adalah Rp 10.000!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            long kelipatan = (long) Math.floor(nominal / 100000);
            admin = 1500 + (kelipatan * 1500);
        }

        double total = nominal + admin;

        String nomorTelepon = JOptionPane.showInputDialog(
            this,
            "Produk     : " + selectedProduct + "\n" +
            "Nominal    : Rp " + String.format("%,.0f", nominal) + "\n" +
            "Admin      : Rp " + String.format("%,.0f", admin) + "\n" +
            "Total Bayar: Rp " + String.format("%,.0f", total) + "\n\n" +
            "Masukkan nomor telepon tujuan:",
            "Konfirmasi Top Up",
            JOptionPane.PLAIN_MESSAGE
        );

        if (nomorTelepon != null && !nomorTelepon.trim().isEmpty()) {
            String ringkasan = "Produk : " + selectedProduct + "\n" +
                               "Nomor  : " + nomorTelepon.trim() + "\n" +
                               "Nominal: Rp " + String.format("%,.0f", nominal) + "\n" +
                               "Admin  : Rp " + String.format("%,.0f", admin) + "\n";
            PaymentDialog.show(this, ringkasan, total);
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

        SwingUtilities.invokeLater(() -> new PulsaWindow().setVisible(true));
    }
}
