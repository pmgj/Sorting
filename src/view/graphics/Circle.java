package view.graphics;

import java.awt.Color;

public class Circle extends UIGraphic {

    private int baseAngle;
    private float colorDiff;
    private int angleDiff;

    public Circle() {
        super("Circle");
    }

    @Override
    public void draw(int idx, int n) {
        float[] color = this.hslToRgb(colorDiff * n, 1.0f, 0.5f);
        g.setColor(new Color(color[0], color[1], color[2]));
        g.fillArc(0, 0, WIDTH, HEIGHT, idx < this.angleDiff ? idx * (this.baseAngle + 1) : idx * this.baseAngle + this.angleDiff, this.baseAngle + (idx < this.angleDiff ? 1 : 0));
    }

    @Override
    public void update() {
        this.colorDiff = 360.0f / QTD;
        this.baseAngle = 360 / QTD;
        this.angleDiff = 360 % QTD;
    }
}
