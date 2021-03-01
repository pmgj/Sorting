package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import view.graphics.UIGraphic;

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
        Collections.shuffle(numbers);
    }

    public void shuffle(int quantity) {
        this.createRandomizedArray(quantity);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        UIGraphic grap = (UIGraphic) this.frame.getCbGraphics().getSelectedItem();
        grap.setGraphics(g, this.getWidth(), this.getHeight(), numbers.size());
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.white);
        for (int idx = 0; idx < numbers.size(); idx++) {
            grap.draw(idx, numbers.get(idx));
        }
    }
}
