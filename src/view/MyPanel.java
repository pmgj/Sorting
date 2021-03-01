package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

    /* UI Components */
    private final MainWindow frame;
    protected List<Integer> numbers;

    public MyPanel(MainWindow mw) {
        this.frame = mw;
    }
    
    private void createRandomizedArray(int qtd) {
        this.numbers = new ArrayList(qtd);
        for (int i = 1; i <= qtd; i++) {
            this.numbers.add(i);
        }
        Collections.shuffle(this.numbers);
    }

    public void shuffle() {
        int quantity = (Integer) this.frame.getsQuantity().getValue();
        this.createRandomizedArray(quantity);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        int outerSpace = 5;
        int innerSpace = 1;
        int QTD = numbers.size();
        int width = (this.getWidth() - 2 * outerSpace - (QTD - 1) * innerSpace) / QTD;
        int height = (this.getHeight() - 2 * outerSpace) / QTD;
        g.setColor(Color.white);
        for (int idx = 0; idx < QTD; idx++) {
            int n = numbers.get(idx);
            g.fillRect(idx * (width + innerSpace) + outerSpace, this.getHeight() - n * height - outerSpace, width, n * height);
        }
    }
}
