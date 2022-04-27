package view.graphics;

import java.awt.Color;

public class Bars extends UIGraphic {

    private int width;
    private int height;
    private float colorDiff;
    private int widthDiff;

    public Bars() {
        super("Bars");
    }

    @Override
    public void draw(int idx, int n) {
        float[] color = this.hslToRgb(colorDiff * n, 1.0f, 0.5f);
        g.setColor(new Color(color[0], color[1], color[2]));
        g.fillRect(idx < this.widthDiff ? idx * (this.width + 1 + innerSpace) + outerSpace : idx * (this.width + innerSpace) + outerSpace + this.widthDiff, outerSpace, this.width + (idx < this.widthDiff ? 1 : 0), this.height);
    }

    @Override
    public void update() {
        this.width = (WIDTH - 2 * outerSpace - (QTD - 1) * innerSpace) / QTD;
        this.height = HEIGHT - 2 * outerSpace;
        this.colorDiff = 360.0f / QTD;
        this.widthDiff = WIDTH - QTD * this.width - 2 * outerSpace - (QTD - 1) * innerSpace;
    }
}
