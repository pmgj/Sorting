package view.graphics;

import java.awt.BasicStroke;
import java.awt.Color;

public class InnerCircle extends UIGraphic {

    private float colorDiff;
    private int lineWidth;

    public InnerCircle() {
        super("InnerCircle");
    }

    @Override
    public void draw(int idx, int n) {
        float[] color = this.hslToRgb(colorDiff * n, 1.0f, 0.5f);
        g.setColor(new Color(color[0], color[1], color[2]));
        int x = idx * WIDTH / (2 * QTD) + lineWidth / 2;
        int y = idx * HEIGHT / (2 * QTD) + lineWidth / 2;
        int w = WIDTH - 2 * x;
        int h = HEIGHT - 2 * y;
        g.setStroke(new BasicStroke(this.lineWidth));
        g.drawOval(x, y, w, h);
    }

    @Override
    public void update() {
        this.lineWidth = Math.max(WIDTH, HEIGHT) / (2 * QTD);
        this.colorDiff = 360.0f / QTD;
    }
}
