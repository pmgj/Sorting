package view.graphics;

import java.awt.Color;

public class Histogram extends UIGraphic {

    private int width;
    private int height;
    private int widthDiff;
    private int heightDiff;

    public Histogram() {
        super("Histogram");
    }

    @Override
    public void draw(int idx, int n) {
        Color[] rainbow = {new Color(139, 0, 255), new Color(46, 43, 95), new Color(0, 0, 255), new Color(0, 255, 0), new Color(255, 255, 0), new Color(255, 127, 0), new Color(255, 0, 0)};
        g.setColor(rainbow[n % 7]);
        int addHeight = (n > QTD - heightDiff) ? n - QTD + heightDiff : 0;
        g.fillRect(idx < this.widthDiff ? idx * (this.width + 1 + innerSpace) + outerSpace : idx * (this.width + innerSpace) + outerSpace + this.widthDiff, HEIGHT - n * height - outerSpace - addHeight, width + (idx < this.widthDiff ? 1 : 0), n * height + addHeight);
    }

    @Override
    public void update() {
        width = (WIDTH - 2 * outerSpace - (QTD - 1) * innerSpace) / QTD;
        height = (HEIGHT - 2 * outerSpace) / QTD;
        widthDiff = WIDTH - QTD * width - 2 * outerSpace - (QTD - 1) * innerSpace;
        heightDiff = HEIGHT - 2 * outerSpace - QTD * height;
    }
}
