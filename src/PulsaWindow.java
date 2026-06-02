/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Zennova
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class PulsaWindow extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PulsaWindow.class.getName());
    private javax.swing.JToggleButton pulsaAxisButton;
    private javax.swing.JToggleButton pulsaSmartfrenButton;
    private javax.swing.JToggleButton emoneyOvoButton;
    private javax.swing.JToggleButton emoneyShopeePayButton;

    /**
     * Creates new form PulsaWindow
     */
    public PulsaWindow() {
        initComponents();
        initAdditionalProductButtons();
        
        // ===== 1. TOGGLE: hanya bisa pilih 1 =====
        javax.swing.ButtonGroup pulsaGroup = new javax.swing.ButtonGroup();
        pulsaGroup.add(pulsaTelkomselButton);
        pulsaGroup.add(pulsaAxisButton);
        pulsaGroup.add(pulsaSmartfrenButton);
        pulsaGroup.add(emoneyGopayButton);
        pulsaGroup.add(pulsaTriButton);
        pulsaGroup.add(emoneyDanaButton);
        pulsaGroup.add(emoneyOvoButton);
        pulsaGroup.add(emoneyShopeePayButton);
        pulsaGroup.add(pulsaXLButton);

        // ===== 2. BACK BUTTON =====
        backButton.addActionListener(e -> {
            new MainWindow().setVisible(true);
            this.dispose();
        });

        // ===== 3. KONFIRMASI BUTTON =====
        confirmButton.addActionListener(e -> {

            // Cek pilihan
            String selectedProduct = "";
            boolean isPulsa = false;

            if (pulsaTelkomselButton.isSelected()) {
                selectedProduct = "Pulsa Telkomsel"; isPulsa = true;
            } else if (pulsaAxisButton.isSelected()) {
                selectedProduct = "Pulsa Axis"; isPulsa = true;
            } else if (pulsaSmartfrenButton.isSelected()) {
                selectedProduct = "Pulsa Smartfren"; isPulsa = true;
            } else if (emoneyGopayButton.isSelected()) {
                selectedProduct = "E-money GoPay"; isPulsa = false;
            } else if (pulsaTriButton.isSelected()) {
                selectedProduct = "Pulsa 3 (Tri)"; isPulsa = true;
            } else if (emoneyDanaButton.isSelected()) {
                selectedProduct = "E-money DANA"; isPulsa = false;
            } else if (emoneyOvoButton.isSelected()) {
                selectedProduct = "E-money OVO"; isPulsa = false;
            } else if (emoneyShopeePayButton.isSelected()) {
                selectedProduct = "E-money ShopeePay"; isPulsa = false;
            } else if (pulsaXLButton.isSelected()) {
                selectedProduct = "Pulsa XL"; isPulsa = true;
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Pilih produk terlebih dahulu!",
                    "Peringatan",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Cek nominal
            String nominalText = jTextField1.getText().trim();
            if (nominalText.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Masukkan nominal top up!",
                    "Peringatan",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }

            double nominal;
            try {
                nominal = Double.parseDouble(nominalText);
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Nominal harus berupa angka!",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ===== VALIDASI MINIMAL & HITUNG ADMIN =====
            double admin = 0;

            if (isPulsa) {
                // Minimal 5000
                if (nominal < 5000) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Minimal top up pulsa adalah Rp 5.000!",
                        "Peringatan",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Admin 500, +500 per kelipatan 50.000
                long kelipatan = (long) Math.floor(nominal / 50000);
                admin = 500 + (kelipatan * 500);

            } else {
                // Minimal 10000
                if (nominal < 10000) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Minimal top up e-money adalah Rp 10.000!",
                        "Peringatan",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Admin 1500, +1500 per kelipatan 100.000
                long kelipatan = (long) Math.floor(nominal / 100000);
                admin = 1500 + (kelipatan * 1500);
            }

            double total = nominal + admin;

            // ===== POPUP MINTA NOMOR TELEPON =====
            String nomorTelepon = javax.swing.JOptionPane.showInputDialog(
                this,
                "Produk     : " + selectedProduct + "\n" +
                "Nominal    : Rp " + String.format("%,.0f", nominal) + "\n" +
                "Admin      : Rp " + String.format("%,.0f", admin) + "\n" +
                "Total Bayar: Rp " + String.format("%,.0f", total) + "\n\n" +
                "Masukkan nomor telepon tujuan:",
                "Konfirmasi Top Up",
                javax.swing.JOptionPane.PLAIN_MESSAGE
            );

            // ===== KALAU NOMOR DIISI & KLIK OK =====
            if (nomorTelepon != null && !nomorTelepon.trim().isEmpty()) {
                String ringkasan = "Produk : " + selectedProduct + "\n" +
                                   "Nomor  : " + nomorTelepon.trim() + "\n" +
                                   "Nominal: Rp " + String.format("%,.0f", nominal) + "\n" +
                                   "Admin  : Rp " + String.format("%,.0f", admin) + "\n";
                PaymentDialog.show(this, ringkasan, total);
            }
        });

        applyFuturisticLayout();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackgroundPulsa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pulsaTelkomselButton = new javax.swing.JToggleButton();
        emoneyGopayButton = new javax.swing.JToggleButton();
        pulsaTriButton = new javax.swing.JToggleButton();
        emoneyDanaButton = new javax.swing.JToggleButton();
        pulsaXLButton = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        confirmButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BackgroundPulsa.setBackground(new java.awt.Color(244, 239, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Top Up Pulsa & E-money, Mambo Store");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Untuk saat ini, berikut adalah pilihan Top Up yang tersedia:");

        pulsaTelkomselButton.setBackground(new java.awt.Color(124, 57, 226));
        pulsaTelkomselButton.setFont(new java.awt.Font("SimSun-ExtG", 0, 12)); // NOI18N
        pulsaTelkomselButton.setForeground(new java.awt.Color(255, 255, 255));
        pulsaTelkomselButton.setText("Pulsa (Telkomsel)");
        pulsaTelkomselButton.addActionListener(this::pulsaTelkomselButtonActionPerformed);

        emoneyGopayButton.setBackground(new java.awt.Color(104, 75, 203));
        emoneyGopayButton.setFont(new java.awt.Font("SimSun-ExtG", 0, 12)); // NOI18N
        emoneyGopayButton.setText("E-money (Gopay)");

        pulsaTriButton.setBackground(new java.awt.Color(145, 84, 243));
        pulsaTriButton.setFont(new java.awt.Font("SimSun-ExtG", 0, 12)); // NOI18N
        pulsaTriButton.setText("Pulsa (3)");

        emoneyDanaButton.setBackground(new java.awt.Color(95, 68, 190));
        emoneyDanaButton.setFont(new java.awt.Font("SimSun-ExtG", 0, 12)); // NOI18N
        emoneyDanaButton.setText("E-money (Dana)");

        pulsaXLButton.setBackground(new java.awt.Color(84, 62, 158));
        pulsaXLButton.setFont(new java.awt.Font("SimSun-ExtG", 0, 12)); // NOI18N
        pulsaXLButton.setForeground(new java.awt.Color(255, 255, 255));
        pulsaXLButton.setText("Pulsa (XL)");
        pulsaXLButton.setPreferredSize(new java.awt.Dimension(120, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Silahkan masukkan nominal top up yang anda inginkan (Rupiah):");

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        confirmButton.setBackground(new java.awt.Color(116, 57, 232));
        confirmButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        confirmButton.setText("Konfirmasi");

        backButton.setBackground(new java.awt.Color(82, 61, 151));
        backButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        backButton.setText("Kembali");
        backButton.addActionListener(this::backButtonActionPerformed);

        javax.swing.GroupLayout BackgroundPulsaLayout = new javax.swing.GroupLayout(BackgroundPulsa);
        BackgroundPulsa.setLayout(BackgroundPulsaLayout);
        BackgroundPulsaLayout.setHorizontalGroup(
            BackgroundPulsaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundPulsaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BackgroundPulsaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundPulsaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(BackgroundPulsaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BackgroundPulsaLayout.createSequentialGroup()
                                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(BackgroundPulsaLayout.createSequentialGroup()
                        .addGroup(BackgroundPulsaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addGroup(BackgroundPulsaLayout.createSequentialGroup()
                                .addComponent(pulsaTelkomselButton)
                                .addGap(18, 18, 18)
                                .addComponent(emoneyGopayButton)
                                .addGap(18, 18, 18)
                                .addComponent(pulsaTriButton)
                                .addGap(18, 18, 18)
                                .addComponent(emoneyDanaButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pulsaXLButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        BackgroundPulsaLayout.setVerticalGroup(
            BackgroundPulsaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundPulsaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(BackgroundPulsaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pulsaTelkomselButton)
                    .addComponent(emoneyGopayButton)
                    .addComponent(pulsaTriButton)
                    .addComponent(emoneyDanaButton)
                    .addComponent(pulsaXLButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BackgroundPulsaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPulsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackgroundPulsa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pulsaTelkomselButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulsaTelkomselButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pulsaTelkomselButtonActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backButtonActionPerformed

    private void initAdditionalProductButtons() {
        pulsaAxisButton = new javax.swing.JToggleButton("Pulsa (Axis)");
        pulsaSmartfrenButton = new javax.swing.JToggleButton("Pulsa (Smartfren)");
        emoneyOvoButton = new javax.swing.JToggleButton("E-money (OVO)");
        emoneyShopeePayButton = new javax.swing.JToggleButton("E-money (ShopeePay)");
    }

    private void applyFuturisticLayout() {
        FuturisticUI.BackgroundPanel root = FuturisticUI.background("/assets/bannerPulsa.png");
        root.setLayout(new BorderLayout(0, 18));
        root.setBorder(new EmptyBorder(18, 22, 22, 22));

        root.add(FuturisticUI.banner("/assets/bannerPulsa.png", 230), BorderLayout.NORTH);

        FuturisticUI.GlassPanel panel = FuturisticUI.glass(new BorderLayout(18, 18));

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        FuturisticUI.title(jLabel1, "Top Up Pulsa & E-Money", 30);
        jLabel1.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        FuturisticUI.body(jLabel2, "Pilih produk tujuan, lalu masukkan nominal top up dalam Rupiah.", 15);
        jLabel2.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        header.add(jLabel1);
        header.add(Box.createVerticalStrut(6));
        header.add(jLabel2);

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

        FuturisticUI.body(jLabel3, "Nominal top up (Rupiah)", 15);
        jLabel3.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        FuturisticUI.styleTextField(jTextField1);
        jTextField1.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 54));
        jTextField1.setPreferredSize(new java.awt.Dimension(10, 54));

        JPanel actions = new JPanel(new GridLayout(1, 2, 14, 0));
        actions.setOpaque(false);
        FuturisticUI.styleCommandButton(confirmButton, FuturisticUI.VIOLET, new Color(177, 139, 255));
        FuturisticUI.styleCommandButton(backButton, new Color(82, 61, 151), new Color(151, 108, 232));
        actions.add(confirmButton);
        actions.add(backButton);

        formStack.add(jLabel3);
        formStack.add(Box.createVerticalStrut(10));
        formStack.add(jTextField1);
        formStack.add(Box.createVerticalStrut(14));
        formStack.add(actions);

        panel.add(header, BorderLayout.NORTH);
        panel.add(productScroll, BorderLayout.CENTER);
        panel.add(formStack, BorderLayout.SOUTH);

        root.add(panel, BorderLayout.CENTER);
        setContentPane(root);
        FuturisticUI.prepareFrame(this, "Mambo Store - Pulsa & E-Money", 1040, 780);
        revalidate();
        repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PulsaWindow().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackgroundPulsa;
    private javax.swing.JButton backButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JToggleButton emoneyDanaButton;
    private javax.swing.JToggleButton emoneyGopayButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton pulsaTelkomselButton;
    private javax.swing.JToggleButton pulsaTriButton;
    private javax.swing.JToggleButton pulsaXLButton;
    // End of variables declaration//GEN-END:variables
}
