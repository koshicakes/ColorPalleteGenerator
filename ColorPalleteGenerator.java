import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ColorPaletteGenerator {

    private static final int COLOR_COUNT = 5;
    private static final Random random = new Random();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Color Palette Generator");
            JPanel palette = new JPanel(new GridLayout(1, COLOR_COUNT));
            JLabel[] swatches = new JLabel[COLOR_COUNT];

            for (int i = 0; i < COLOR_COUNT; i++) {
                swatches[i] = new JLabel("", SwingConstants.CENTER);
                swatches[i].setOpaque(true);
                swatches[i].setPreferredSize(new Dimension(120, 200));
                palette.add(swatches[i]);
            }

            JButton button = new JButton("Generate New Palette");
            button.addActionListener(e -> generate(swatches));
            generate(swatches);

            frame.add(palette, BorderLayout.CENTER);
            frame.add(button, BorderLayout.SOUTH);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    private static void generate(JLabel[] swatches) {
        for (JLabel swatch : swatches) {
            Color c = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            swatch.setBackground(c);
            swatch.setText(String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue()));

            // Use dark text on light colors and white text on dark colors
            double brightness = (c.getRed() * 299 + c.getGreen() * 587 + c.getBlue() * 114) / 1000.0;
            swatch.setForeground(brightness > 128 ? Color.BLACK : Color.WHITE);
        }
    }
}