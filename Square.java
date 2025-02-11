package edu.weber.cs3230.GUI.GUIFoundations.homework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

public class Square implements Drawable {
    private int x, y, width, height;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);

        GeneralPath path = new GeneralPath();
        path.moveTo(x, y);
        path.lineTo(x + width, y);
        path.lineTo(x + width, y + height);
        path.lineTo(x, y + height);
        path.closePath();

        g2d.fill(path);
    }
}