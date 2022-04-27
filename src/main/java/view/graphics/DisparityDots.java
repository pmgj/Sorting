package view.graphics;

import java.awt.Color;

public class DisparityDots extends UIGraphic {

    private int cx;
    private int cy;
    private int radius;
    private float colorDiff;

    public DisparityDots() {
        super("DisparityDots");
    }

    @Override
    public void draw(int idx, int n) {
        float[] color = this.hslToRgb(colorDiff * n, 1.0f, 0.5f);
        g.setColor(new Color(color[0], color[1], color[2]));
        int x = this.cx + (int) ((this.WIDTH - 2.0 * this.radius - 2.0 * this.outerSpace) / 2.0 * (Math.cos(idx * 2 * Math.PI / this.QTD)) * (1.0 - (Math.abs(n - idx - 1.0) / this.QTD)));
        int y = this.cy + (int) ((this.HEIGHT - 2.0 * this.radius  - 2.0 * this.outerSpace) / 2.0 * (Math.sin(idx * 2 * Math.PI / this.QTD)) * (1.0 - (Math.abs(n - idx - 1.0) / this.QTD)));
        g.fillOval(x, y, this.radius, this.radius);
    }

    @Override
    public void update() {
        this.cx = this.WIDTH / 2;
        this.cy = this.HEIGHT / 2;
        this.radius = Math.min(this.cx, this.cy) / this.QTD;
        this.colorDiff = 360.0f / this.QTD;
    }
}
