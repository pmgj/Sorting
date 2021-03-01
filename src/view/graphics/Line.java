package view.graphics;

import java.awt.Color;

public class Line extends UIGraphic {

    private int width;
    private int height;
    private int widthDiff;
    private int heightDiff;

    public Line() {
        super("Line");
    }

    @Override
    public void draw(int idx, int n) {
        Color[] rainbow = {new Color(139, 0, 255), new Color(46, 43, 95), new Color(0, 0, 255), new Color(0, 255, 0), new Color(255, 255, 0), new Color(255, 127, 0), new Color(255, 0, 0)};
        g.setColor(rainbow[n % 7]);
        g.fillRect(idx < this.widthDiff ? idx * (this.width + 1 + innerSpace) + outerSpace : idx * (this.width + innerSpace) + outerSpace + this.widthDiff, HEIGHT - n * this.height - outerSpace - (n > QTD - heightDiff ? n - QTD + heightDiff : 0), this.width, this.height);
    }

    @Override
    public void update() {
        this.width = (WIDTH - 2 * outerSpace - (QTD - 1) * innerSpace) / QTD;
        this.height = (HEIGHT - 2 * outerSpace) / QTD;
        this.widthDiff = WIDTH - QTD * width - 2 * outerSpace - (QTD - 1) * innerSpace;
        this.heightDiff = HEIGHT - 2 * outerSpace - QTD * height;
    }
}
