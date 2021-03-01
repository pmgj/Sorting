package view.graphics;

import java.awt.Graphics;

public abstract class UIGraphic {

    private final String name;
    protected Graphics g;
    protected int WIDTH;
    protected int HEIGHT;
    protected int QTD;
    protected int outerSpace = 5;
    protected int innerSpace = 1;

    public UIGraphic(String name) {
        this.name = name;
    }

    public abstract void draw(int index, int value);

    public void setGraphics(Graphics g, int width, int height, int qtd) {
        this.g = g;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.QTD = qtd;
    }
    
    public static UIGraphic[] getGraphics() {
        return new UIGraphic[]{new Histogram(), new Line()};
    }

    @Override
    public String toString() {
        return name;
    }    
}
