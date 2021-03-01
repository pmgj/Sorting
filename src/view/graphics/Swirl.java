package view.graphics;

import java.awt.Color;

public class Swirl extends UIGraphic {

    private int cx;
    private int cy;
    private double outer_rad;
    private int sm_rad;
    private double angle_incr;

    public Swirl() {
        super("Swirl");
    }

    @Override
    public void draw(int idx, int n) {
        Color[] rainbow = {new Color(139, 0, 255), new Color(46, 43, 95), new Color(0, 0, 255), new Color(0, 255, 0), new Color(255, 255, 0), new Color(255, 127, 0), new Color(255, 0, 0)};
        g.setColor(rainbow[n % 7]);
        double ratio = (1.0 * n) / this.QTD;
        double angle = idx * this.angle_incr;
        double spiral_rad = ratio * this.outer_rad;
        int x = (int) (this.cx + Math.cos(angle) * spiral_rad);
        int y = (int) (this.cy + Math.sin(angle) * spiral_rad);
        g.fillOval(x, y, this.sm_rad, this.sm_rad);
    }

    @Override
    public void update() {
        this.cx = this.WIDTH / 2;
        this.cy = this.HEIGHT / 2;
        this.outer_rad = this.WIDTH * .15;
        this.sm_rad = 10;
        this.angle_incr = 6 * Math.PI / 180;
    }
}
