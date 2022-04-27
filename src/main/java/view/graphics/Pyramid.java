package view.graphics;

import java.awt.Color;

public class Pyramid extends UIGraphic {

    private int width;
    private int height;
    private int widthDiff;
    private int heightDiff;

    public Pyramid() {
        super("Pyramid");
    }

    @Override
    public void draw(int idx, int n) {
        Color[] rainbow = {new Color(139, 0, 255), new Color(46, 43, 95), new Color(0, 0, 255), new Color(0, 255, 0), new Color(255, 255, 0), new Color(255, 127, 0), new Color(255, 0, 0)};
        g.setColor(rainbow[n % 7]);
        int addWidth = n > QTD - this.widthDiff ? (this.widthDiff - QTD + n) / 2 : 0;
        g.fillRect(WIDTH / 2 - n * this.width / 2 - addWidth, idx < this.heightDiff ? idx * (this.height + 1 + innerSpace) + outerSpace : idx * (this.height + innerSpace) + outerSpace + this.heightDiff, n * this.width + addWidth, this.height);
    }

    @Override
    public void update() {
        this.width = (WIDTH - 2 * outerSpace) / QTD;
        this.height = (HEIGHT - 2 * outerSpace - (QTD - 1) * innerSpace) / QTD;
        this.heightDiff = HEIGHT - QTD * height - 2 * outerSpace - (QTD - 1) * innerSpace;
        this.widthDiff = WIDTH - QTD * this.width - 2 * outerSpace;
    }
}
