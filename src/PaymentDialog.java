/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Zennova
 */
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.Random;

public class PaymentDialog {

    // ===== GENERATE QR CODE =====
    private static ImageIcon generateQR(String content, int size) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, size, size);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
            return new ImageIcon(image);
        } catch (WriterException e) {
            return null;
        }
    }

    // ===== SHOW PAYMENT DIALOG =====
    public static void show(Component parent, String ringkasan, double total) {

        // Generate random payment ID
        String paymentId = "MAMBO" + String.format("%08d", new Random().nextInt(99999999));

        // ===== STEP 1: Pilih metode =====
        String[] options = {"QRIS", "Virtual Account"};
        int pilihMetode = JOptionPane.showOptionDialog(
            parent,
            "Ringkasan Pesanan:\n" + ringkasan +
            "\nTotal Bayar: Rp " + String.format("%,.0f", total) +
            "\n\nPilih metode pembayaran:",
            "Metode Pembayaran",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
        );

        if (pilihMetode == JOptionPane.CLOSED_OPTION) return;

        // ===== STEP 2A: QRIS =====
        if (pilihMetode == 0) {
            String qrContent = "MAMBOSTORE|" + paymentId + "|" + (long) total;
            ImageIcon qrIcon = generateQR(qrContent, 250);

            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBackground(FuturisticUI.PAGE_LIGHT);

            JLabel infoLabel = new JLabel(
                "<html><center>" +
                "<b>Scan QR Code berikut untuk membayar</b><br>" +
                "Payment ID: " + paymentId + "<br>" +
                "Total: <b>Rp " + String.format("%,.0f", total) + "</b><br><br>" +
                "QR berlaku selama <b>10 menit</b>" +
                "</center></html>"
            );
            infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel qrLabel = new JLabel(qrIcon);
            qrLabel.setHorizontalAlignment(SwingConstants.CENTER);

            panel.add(infoLabel, BorderLayout.NORTH);
            panel.add(qrLabel, BorderLayout.CENTER);

            int bayar = JOptionPane.showConfirmDialog(
                parent, panel,
                "QRIS - Mambo Store",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

            if (bayar == JOptionPane.OK_OPTION) showSuccess(parent, paymentId, "QRIS");

        // ===== STEP 2B: VIRTUAL ACCOUNT =====
        } else {
            String[] banks = {"BCA", "Mandiri", "BNI", "BRI"};
            String selectedBank = (String) JOptionPane.showInputDialog(
                parent,
                "Pilih bank Virtual Account:",
                "Virtual Account",
                JOptionPane.PLAIN_MESSAGE,
                null,
                banks,
                banks[0]
            );

            if (selectedBank == null) return;

            // Generate random VA number
            String vaNumber = "8800" + String.format("%012d", new Random().nextLong() % 1000000000000L).replace("-", "");
            vaNumber = vaNumber.substring(0, 16);

            JPanel panel = new JPanel();
            panel.setBackground(FuturisticUI.PAGE_LIGHT);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel info = new JLabel(
                "<html><center>" +
                "<b>Virtual Account " + selectedBank + "</b><br><br>" +
                "Nomor VA:<br>" +
                "<span style='font-size:16px; letter-spacing:3px'><b>" + vaNumber + "</b></span><br><br>" +
                "Total Bayar: <b>Rp " + String.format("%,.0f", total) + "</b><br>" +
                "Payment ID: " + paymentId + "<br><br>" +
                "<i>Bayar sebelum 1 jam</i>" +
                "</center></html>"
            );
            info.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(info);

            int bayar = JOptionPane.showConfirmDialog(
                parent, panel,
                "Virtual Account " + selectedBank,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

            if (bayar == JOptionPane.OK_OPTION) showSuccess(parent, paymentId, "Virtual Account " + selectedBank);
        }
    }

    // ===== SUCCESS DIALOG =====
    private static void showSuccess(Component parent, String paymentId, String metode) {
        JOptionPane.showMessageDialog(
            parent,
            "Pembayaran Berhasil! 🎉\n\n" +
            "━━━━━━━━━━━━━━━━━━━━\n" +
            "Payment ID : " + paymentId + "\n" +
            "Metode     : " + metode + "\n" +
            "Status     : BERHASIL ✓\n" +
            "━━━━━━━━━━━━━━━━━━━━\n\n" +
            "Top up sedang diproses, maksimal 1 menit!",
            "Pembayaran Berhasil",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
