package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import view.graphics.UIGraphic;
import model.Command;

public class MyPanel extends JPanel {

    /* UI Components */
    private final MainWindow frame;
    private final List<Command> commands = new ArrayList();
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

    public void exch(List<Integer> a, int i, int j) {
        int swap = a.get(i);
        a.set(i, a.get(j));
        a.set(j, swap);
        this.commands.add(new Command(Command.WRITE, i, a.get(i)));
        this.commands.add(new Command(Command.WRITE, j, swap));
    }

    public void bubbleSort(List<Integer> array) {
        int len = array.size() - 1;
        do {
            int newn = 0;
            for (int i = 0; i < len; i++) {
                this.commands.add(new Command(Command.READ, i, array.get(i)));
                this.commands.add(new Command(Command.READ, i + 1, array.get(i + 1)));
                if (array.get(i) > array.get(i + 1)) {
                    exch(array, i, i + 1);
                    newn = i;
                }
            }
            len = newn;
        } while (len > 0);
    }

    public void insertionSort(List<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            for (int j = i; j > 0; j--) {
                this.commands.add(new Command(Command.READ, j, array.get(j)));
                this.commands.add(new Command(Command.READ, j - 1, array.get(j - 1)));
                if (array.get(j) < array.get(j - 1)) {
                    exch(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
    
    public void run() {
        List<Integer> temp = new ArrayList(this.numbers);
        String sortingAlgorithm = (String) this.frame.getCbAlgorithms().getSelectedItem();
        if(sortingAlgorithm.contains("Bubble")) bubbleSort(temp);
        else insertionSort(temp);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        UIGraphic grap = (UIGraphic) this.frame.getCbGraphics().getSelectedItem();
        grap.setGraphics(g, this.getWidth(), this.getHeight(), numbers.size());
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (!commands.isEmpty()) {
            Command first = commands.remove(0);
            int value = first.getValue();
            int index = first.getIndex();
            for (int idx = 0; idx < numbers.size(); idx++) {
                g.setColor(Color.white);
                if (first.getAction() == Command.READ && index == idx) {
                    g.setColor(Color.green);
                } else if (first.getAction() == Command.WRITE && index == idx) {
                    g.setColor(Color.red);
                }
                grap.draw(idx, numbers.get(idx));
            }
            if (first.getAction() == Command.WRITE) {
                numbers.set(first.getIndex(), value);
            }
            int delay = (Integer) this.frame.getsDelay().getValue();
            Timer timer = new Timer(delay, ae -> repaint());
            timer.setRepeats(false);
            timer.start();
        } else {
            g.setColor(Color.white);
            for (int idx = 0; idx < numbers.size(); idx++) {
                grap.draw(idx, numbers.get(idx));
            }
        }
    }
}
