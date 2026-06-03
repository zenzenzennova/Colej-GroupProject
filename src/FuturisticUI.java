/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Zennova
 */
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

final class FuturisticUI {

    static final Color PAGE_DARK = new Color(48, 27, 98);
    static final Color PAGE_LIGHT = new Color(250, 248, 255);
    static final Color GLASS_DARK = new Color(244, 239, 255, 218);
    static final Color GLASS_LIGHT = new Color(255, 255, 255, 186);
    static final Color CYAN = new Color(205, 190, 255);
    static final Color VIOLET = new Color(116, 57, 232);
    static final Color PINK = new Color(151, 83, 244);
    static final Color GREEN = new Color(178, 139, 255);
    static final Color WHITE = new Color(255, 255, 255);
    static final Color MUTED = new Color(81, 61, 131);

    private static final String FONT = "Segoe UI";

    private FuturisticUI() {
    }

    static void prepareFrame(JFrame frame, String title, int width, int height) {
        frame.setTitle(title);
        frame.setMinimumSize(new Dimension(860, 620));
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
    }

    static BackgroundPanel background() {
        return new BackgroundPanel(PAGE_LIGHT, PAGE_LIGHT, null);
    }

    static BackgroundPanel background(Color start, Color end, Color accent) {
        return new BackgroundPanel(start, end, accent);
    }

    static BannerPanel banner(String resource, int height) {
        BannerPanel panel = new BannerPanel(resource);
        panel.setPreferredSize(new Dimension(10, height));
        panel.setMinimumSize(new Dimension(10, Math.max(180, height - 80)));
        return panel;
    }

    static GlassPanel glass(LayoutManager layout) {
        return new GlassPanel(
            layout,
            new Color(94, 50, 176, 42),
            GLASS_LIGHT,
            GLASS_DARK,
            new Color(116, 57, 232, 118)
        );
    }

    static GlassPanel glass(LayoutManager layout, Color shadow, Color start, Color end, Color border) {
        return new GlassPanel(layout, shadow, start, end, border);
    }

    static JLabel title(JLabel label, String text, int size) {
        label.setText(text);
        label.setFont(new Font(FONT, Font.BOLD, size));
        label.setForeground(PAGE_DARK);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        return label;
    }

    static JLabel body(JLabel label, String text, int size) {
        label.setText(text);
        label.setFont(new Font(FONT, Font.PLAIN, size));
        label.setForeground(MUTED);
        return label;
    }

    static void styleCommandButton(JButton button, Color start, Color end) {
        styleButtonBase(button, start, end, 17);
        button.setPreferredSize(new Dimension(190, 58));
    }

    static void styleToggleButton(JToggleButton button, Color start, Color end) {
        styleButtonBase(button, start, end, 16);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setPreferredSize(new Dimension(214, 58));
    }

    static void styleTextField(JTextField field) {
        styleTextField(field, PAGE_DARK, VIOLET, CYAN);
    }

    static void styleTextField(JTextField field, Color text, Color accent, Color border) {
        field.setFont(new Font(FONT, Font.BOLD, 18));
        field.setForeground(text);
        field.setCaretColor(accent);
        field.setSelectedTextColor(WHITE);
        field.setSelectionColor(accent);
        field.setOpaque(false);
        field.setBorder(new RoundedBorder(border, new Insets(12, 16, 12, 16), 16));
    }

    private static void styleButtonBase(AbstractButton button, Color start, Color end, int radius) {
        button.setUI(new NeonButtonUI(start, end, radius));
        button.setFont(new Font(FONT, Font.BOLD, 15));
        button.setForeground(WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setRolloverEnabled(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(12, 18, 12, 18));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private static Image loadImage(String resource) {
        URL url = FuturisticUI.class.getResource(resource);
        if (url == null) {
            return null;
        }
        return new javax.swing.ImageIcon(url).getImage();
    }

    private static void drawCover(Graphics2D g2, Image image, int width, int height, double focalX, double focalY) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        if (imageWidth <= 0 || imageHeight <= 0 || width <= 0 || height <= 0) {
            return;
        }

        double scale = Math.max((double) width / imageWidth, (double) height / imageHeight);
        int drawWidth = (int) Math.ceil(imageWidth * scale);
        int drawHeight = (int) Math.ceil(imageHeight * scale);
        int x = (int) Math.round((width - drawWidth) * focalX);
        int y = (int) Math.round((height - drawHeight) * focalY);
        g2.drawImage(image, x, y, drawWidth, drawHeight, null);
    }

    static final class BackgroundPanel extends JPanel {

        private final Color start;
        private final Color end;
        private final Color accent;

        BackgroundPanel(Color start, Color end, Color accent) {
            this.start = start;
            this.end = end;
            this.accent = accent;
            setBackground(start);
            setOpaque(true);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setPaint(new GradientPaint(0, 0, start, getWidth(), getHeight(), end));
            g2.fillRect(0, 0, getWidth(), getHeight());
            if (accent != null) {
                g2.setComposite(AlphaComposite.SrcOver.derive(0.14f));
                g2.setColor(accent);
                for (int x = -getHeight(); x < getWidth(); x += 112) {
                    g2.drawLine(x, getHeight(), x + getHeight(), 0);
                }
            }
            g2.dispose();
        }
    }

    static final class BannerPanel extends JPanel {

        private final Image image;

        BannerPanel(String resource) {
            this.image = loadImage(resource);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            Shape shadow = new RoundRectangle2D.Float(4, 8, Math.max(0, width - 8), Math.max(0, height - 8), 28, 28);
            g2.setColor(new Color(0, 0, 0, 88));
            g2.fill(shadow);

            Shape clip = new RoundRectangle2D.Float(0, 0, Math.max(0, width - 1), Math.max(0, height - 12), 28, 28);
            g2.setClip(clip);
            g2.setPaint(new GradientPaint(0, 0, new Color(49, 25, 113), width, height, new Color(9, 198, 219)));
            g2.fillRect(0, 0, width, height);
            if (image != null) {
                drawCover(g2, image, width, Math.max(1, height - 12), 0.5, 0.44);
            }
            g2.setPaint(new GradientPaint(0, 0, new Color(4, 8, 28, 20), width, height, new Color(4, 8, 28, 96)));
            g2.fillRect(0, 0, width, height);
            g2.setClip(null);

            g2.setStroke(new BasicStroke(1.6f));
            g2.setColor(new Color(255, 255, 255, 110));
            g2.draw(clip);
            g2.dispose();
            super.paintComponent(graphics);
        }
    }

    static final class GlassPanel extends JPanel {

        private final Color shadow;
        private final Color start;
        private final Color end;
        private final Color border;

        GlassPanel(LayoutManager layout, Color shadow, Color start, Color end, Color border) {
            super(layout);
            this.shadow = shadow;
            this.start = start;
            this.end = end;
            this.border = border;
            setOpaque(false);
            setBorder(new EmptyBorder(22, 24, 24, 24));
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();

            g2.setColor(shadow);
            g2.fillRoundRect(5, 8, Math.max(0, width - 10), Math.max(0, height - 10), 24, 24);

            g2.setPaint(new GradientPaint(0, 0, start, width, height, end));
            g2.fillRoundRect(0, 0, Math.max(0, width - 1), Math.max(0, height - 1), 24, 24);

            g2.setStroke(new BasicStroke(1.4f));
            g2.setColor(border);
            g2.drawRoundRect(0, 0, Math.max(0, width - 1), Math.max(0, height - 1), 24, 24);

            g2.dispose();
            super.paintComponent(graphics);
        }
    }

    private static final class NeonButtonUI extends BasicButtonUI {

        private final Color start;
        private final Color end;
        private final int radius;

        NeonButtonUI(Color start, Color end, int radius) {
            this.start = start;
            this.end = end;
            this.radius = radius;
        }

        @Override
        public void paint(Graphics graphics, JComponent component) {
            AbstractButton button = (AbstractButton) component;
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = component.getWidth();
            int height = component.getHeight();
            boolean selected = button.getModel().isSelected();
            boolean rollover = button.getModel().isRollover();
            boolean pressed = button.getModel().isPressed();

            Color left = selected ? brighten(start, 1.25f) : start;
            Color right = selected ? brighten(end, 1.25f) : end;
            if (rollover) {
                left = brighten(left, 1.16f);
                right = brighten(right, 1.16f);
            }
            if (pressed) {
                left = darken(left, 0.78f);
                right = darken(right, 0.78f);
            }

            g2.setColor(new Color(0, 0, 0, selected ? 82 : 54));
            g2.fillRoundRect(3, 5, Math.max(0, width - 6), Math.max(0, height - 7), radius + 8, radius + 8);

            g2.setPaint(new GradientPaint(0, 0, left, width, height, right));
            g2.fillRoundRect(0, 0, Math.max(0, width - 1), Math.max(0, height - 2), radius + 8, radius + 8);

            g2.setPaint(new GradientPaint(0, 0, new Color(255, 255, 255, selected ? 210 : 112), width, height, new Color(255, 255, 255, 38)));
            g2.setStroke(new BasicStroke(selected ? 2.1f : 1.2f));
            g2.drawRoundRect(1, 1, Math.max(0, width - 3), Math.max(0, height - 4), radius + 7, radius + 7);

            g2.dispose();
            super.paint(graphics, component);
        }

        @Override
        protected void paintFocus(Graphics graphics, AbstractButton button, java.awt.Rectangle viewRect, java.awt.Rectangle textRect, java.awt.Rectangle iconRect) {
        }

        private static Color brighten(Color color, float factor) {
            return new Color(
                Math.min(255, Math.round(color.getRed() * factor)),
                Math.min(255, Math.round(color.getGreen() * factor)),
                Math.min(255, Math.round(color.getBlue() * factor)),
                color.getAlpha()
            );
        }

        private static Color darken(Color color, float factor) {
            return new Color(
                Math.max(0, Math.round(color.getRed() * factor)),
                Math.max(0, Math.round(color.getGreen() * factor)),
                Math.max(0, Math.round(color.getBlue() * factor)),
                color.getAlpha()
            );
        }
    }

    private static final class RoundedBorder extends AbstractBorder {

        private final Color color;
        private final Insets insets;
        private final int radius;

        RoundedBorder(Color color, Insets insets, int radius) {
            this.color = color;
            this.insets = insets;
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component component, Graphics graphics, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(1.4f));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius + 10, radius + 10);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component component) {
            return new Insets(insets.top, insets.left, insets.bottom, insets.right);
        }

        @Override
        public Insets getBorderInsets(Component component, Insets targetInsets) {
            targetInsets.top = insets.top;
            targetInsets.left = insets.left;
            targetInsets.bottom = insets.bottom;
            targetInsets.right = insets.right;
            return targetInsets;
        }
    }
}
