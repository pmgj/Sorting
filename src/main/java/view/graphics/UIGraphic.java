package view.graphics;

import java.awt.Graphics2D;

public abstract class UIGraphic {

    private final String name;
    protected Graphics2D g;
    protected int WIDTH;
    protected int HEIGHT;
    protected int QTD;
    protected int outerSpace = 5;
    protected int innerSpace = 1;

    public UIGraphic(String name) {
        this.name = name;
    }

    public abstract void draw(int index, int value);

    public abstract void update();

    public void setGraphics(Graphics2D g, int width, int height, int qtd) {
        this.g = g;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.QTD = qtd;
        this.update();
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    private float f(float n, float h, float l, float a) {
        float k = (n + h / 30) % 12;
        return l - a * Math.max(Math.min(k - 3, Math.min(9 - k, 1)), -1);
    }

    protected float[] hslToRgb(float h, float s, float l) {
        float a = s * Math.min(l, 1 - l);
        return new float[]{f(0, h, l, a), f(8, h, l, a), f(4, h, l, a)};
    }    
}
