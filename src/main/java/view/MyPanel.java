package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Command;
import model.DynamicInvocationHandler;
import model.algorithms.SortAlgorithm;
import view.graphics.UIGraphic;

public class MyPanel extends JPanel {

    /* UI Components */
    private final MainWindow frame;
    /* Data from model */
    private List<Command> commands = new ArrayList<>();
    protected List<Integer> numbers;

    public MyPanel(MainWindow mw) {
        this.frame = mw;
    }

    private void createRandomizedArray(int qtd) {
        this.numbers = new ArrayList<>(qtd);
        for (int i = 1; i <= qtd; i++) {
            this.numbers.add(i);
        }
        Collections.shuffle(numbers);
    }

    @SuppressWarnings("unchecked")
    public void run(SortAlgorithm sortingAlgorithm) {
        List<Integer> des = new ArrayList<>(this.numbers);
        DynamicInvocationHandler dih = new DynamicInvocationHandler(des);
        List<Integer> proxyInstance = (List<Integer>) Proxy.newProxyInstance(List.class.getClassLoader(), new Class[]{List.class}, dih);
        sortingAlgorithm.sort(proxyInstance);
        this.commands = dih.getCommands();
        repaint();
    }

    public void shuffle(int quantity) {
        this.createRandomizedArray(quantity);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        UIGraphic grap = (UIGraphic) this.frame.getCbGraphics().getSelectedItem();
        grap.setGraphics((Graphics2D) g, this.getWidth(), this.getHeight(), this.numbers.size());
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
            this.frame.getbShuffle().setEnabled(true);
            this.frame.getbRun().setEnabled(true);
        }
    }
}
