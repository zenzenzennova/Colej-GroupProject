import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public final class PaymentDialog {

    private static final Random RANDOM = new Random();

    private PaymentDialog() {
    }

    private static ImageIcon generateQrCode(String content, int size) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, size, size);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
            return new ImageIcon(image);
        } catch (WriterException e) {
            return null;
        }
    }

    public static void show(Component parent, String ringkasan, double total) {
        String paymentId = "MAMBO" + String.format("%08d", RANDOM.nextInt(100000000));

        String[] options = {"QRIS", "Virtual Account"};
        int paymentMethodIndex = JOptionPane.showOptionDialog(
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

        if (paymentMethodIndex == JOptionPane.CLOSED_OPTION) {
            return;
        }

        if (paymentMethodIndex == 0) {
            showQrisDialog(parent, paymentId, total);
        } else {
            showVirtualAccountDialog(parent, paymentId, total);
        }
    }

    private static void showQrisDialog(Component parent, String paymentId, double total) {
        String qrContent = "MAMBOSTORE|" + paymentId + "|" + (long) total;
        ImageIcon qrIcon = generateQrCode(qrContent, 250);

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

        int result = JOptionPane.showConfirmDialog(
            parent,
            panel,
            "QRIS - Mambo Store",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            showSuccess(parent, paymentId, "QRIS");
        }
    }

    private static void showVirtualAccountDialog(Component parent, String paymentId, double total) {
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

        if (selectedBank == null) {
            return;
        }

        long randomSuffix = Math.abs(RANDOM.nextLong() % 1000000000000L);
        String vaNumber = "8800" + String.format("%012d", randomSuffix);

        JPanel panel = new JPanel();
        panel.setBackground(FuturisticUI.PAGE_LIGHT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel infoLabel = new JLabel(
            "<html><center>" +
            "<b>Virtual Account " + selectedBank + "</b><br><br>" +
            "Nomor VA:<br>" +
            "<span style='font-size:16px; letter-spacing:3px'><b>" + vaNumber + "</b></span><br><br>" +
            "Total Bayar: <b>Rp " + String.format("%,.0f", total) + "</b><br>" +
            "Payment ID: " + paymentId + "<br><br>" +
            "<i>Bayar sebelum 1 jam</i>" +
            "</center></html>"
        );
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(infoLabel);

        int result = JOptionPane.showConfirmDialog(
            parent,
            panel,
            "Virtual Account " + selectedBank,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            showSuccess(parent, paymentId, "Virtual Account " + selectedBank);
        }
    }

    private static void showSuccess(Component parent, String paymentId, String paymentMethod) {
        JOptionPane.showMessageDialog(
            parent,
            "Pembayaran Berhasil\n\n" +
            "------------------------------------\n" +
            "Payment ID : " + paymentId + "\n" +
            "Metode     : " + paymentMethod + "\n" +
            "Status     : BERHASIL\n" +
            "------------------------------------\n\n" +
            "Top up sedang diproses, maksimal 1 menit.",
            "Pembayaran Berhasil",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
