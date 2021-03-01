package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {

    /* UI Components */
    private MyPanel pCanvas;
    private JComboBox cbGraphics;
    private JSpinner sQuantity;

    private void createWindow() {
        this.setPreferredSize(new Dimension(618, 726));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titulo = new JLabel("Ordenação", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 24));
        topPanel.add(titulo);
        topPanel.setMaximumSize(new Dimension(640, 50));
        this.add(topPanel);

        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cbGraphics = new JComboBox(new String[]{"Histogram", "Line"});
        cbGraphics.setToolTipText("How to show the sorting algorithms");
        cbGraphics.addActionListener(ae -> pCanvas.repaint());
        middlePanel.add(cbGraphics);
        sQuantity = new JSpinner(new SpinnerNumberModel(25, 1, 2000, 1));
        sQuantity.setToolTipText("Quantity of numbers to sort");
        middlePanel.add(sQuantity);
        JButton bShuffle = new JButton("Shuffle");
        bShuffle.addActionListener(ae -> pCanvas.shuffle());
        middlePanel.add(bShuffle);
        middlePanel.setMaximumSize(new Dimension(640, 50));
        this.add(middlePanel);

        pCanvas = new MyPanel(this);
        pCanvas.shuffle();
        this.add(pCanvas);

        this.pack();
    }

    public JComboBox getCbGraphics() {
        return cbGraphics;
    }

    public JSpinner getsQuantity() {
        return sQuantity;
    }

    public MainWindow() {
        super("Sorting Algorithms");
    }

    public static void main(String[] args) {
        MainWindow w = new MainWindow();
        w.createWindow();
    }
}
