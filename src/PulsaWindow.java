/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Zennova
 */
public class PulsaWindow extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PulsaWindow.class.getName());

    /**
     * Creates new form PulsaWindow
     */
    public PulsaWindow() {
        initComponents();
        
        // ===== 1. TOGGLE: hanya bisa pilih 1 =====
        javax.swing.ButtonGroup pulsaGroup = new javax.swing.ButtonGroup();
        pulsaGroup.add(pulsaTelkomselButton);
        pulsaGroup.add(emoneyGopayButton);
        pulsaGroup.add(pulsaTriButton);
        pulsaGroup.add(emoneyDanaButton);
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
            } else if (emoneyGopayButton.isSelected()) {
                selectedProduct = "E-money GoPay"; isPulsa = false;
            } else if (pulsaTriButton.isSelected()) {
                selectedProduct = "Pulsa 3 (Tri)"; isPulsa = true;
            } else if (emoneyDanaButton.isSelected()) {
                selectedProduct = "E-money DANA"; isPulsa = false;
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

        BackgroundPulsa.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Top Up Pulsa & E-money, Mambo Store");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Untuk saat ini, berikut adalah pilihan Top Up yang tersedia:");

        pulsaTelkomselButton.setBackground(new java.awt.Color(255, 51, 51));
        pulsaTelkomselButton.setFont(new java.awt.Font("SimSun-ExtG", 0, 12)); // NOI18N
        pulsaTelkomselButton.setForeground(new java.awt.Color(255, 255, 255));
        pulsaTelkomselButton.setText("Pulsa (Telkomsel)");
        pulsaTelkomselButton.addActionListener(this::pulsaTelkomselButtonActionPerformed);

        emoneyGopayButton.setBackground(new java.awt.Color(102, 255, 255));
        emoneyGopayButton.setFont(new java.awt.Font("SimSun-ExtG", 0, 12)); // NOI18N
        emoneyGopayButton.setText("E-money (Gopay)");

        pulsaTriButton.setBackground(new java.awt.Color(255, 102, 204));
        pulsaTriButton.setFont(new java.awt.Font("SimSun-ExtG", 0, 12)); // NOI18N
        pulsaTriButton.setText("Pulsa (3)");

        emoneyDanaButton.setBackground(new java.awt.Color(0, 204, 255));
        emoneyDanaButton.setFont(new java.awt.Font("SimSun-ExtG", 0, 12)); // NOI18N
        emoneyDanaButton.setText("E-money (Dana)");

        pulsaXLButton.setBackground(new java.awt.Color(0, 0, 204));
        pulsaXLButton.setFont(new java.awt.Font("SimSun-ExtG", 0, 12)); // NOI18N
        pulsaXLButton.setForeground(new java.awt.Color(255, 255, 255));
        pulsaXLButton.setText("Pulsa (XL)");
        pulsaXLButton.setPreferredSize(new java.awt.Dimension(120, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Silahkan masukkan nominal top up yang anda inginkan (Rupiah):");

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        confirmButton.setBackground(new java.awt.Color(0, 255, 51));
        confirmButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        confirmButton.setText("Konfirmasi");

        backButton.setBackground(new java.awt.Color(255, 51, 102));
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
