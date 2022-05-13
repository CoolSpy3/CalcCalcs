package com.coolspy3.calccalcs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JComponent;

public abstract class ImageBuffer extends JComponent {

    private Image image;

    public ImageBuffer() {
        image = createImage(getWidth(), getHeight());
        Graphics g = image.getGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.dispose();
    }

    public abstract void render(Graphics2D g);

    public boolean updateImage() {
        Image nImage = createImage(getWidth(), getHeight());
        try {
            Graphics2D iGraphics = (Graphics2D) nImage.getGraphics();
            render(iGraphics);
            iGraphics.dispose();
        } catch(Exception e) {
            e.printStackTrace(System.err);
            return true;
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

}
