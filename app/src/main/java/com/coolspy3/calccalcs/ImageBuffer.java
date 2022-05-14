package com.coolspy3.calccalcs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public abstract class ImageBuffer extends JComponent {

    private Image image;

    public ImageBuffer() {
        setSize(1, 1);
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.dispose();
    }

    public abstract void render(Graphics2D g);

    public boolean updateImage() {
        boolean errorOccurred = false;
        Image nImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        try {
            Graphics2D iGraphics = (Graphics2D) nImage.getGraphics();
            render(iGraphics);
            iGraphics.dispose();
        } catch(Exception e) {
            e.printStackTrace(System.err);
            Graphics g = image.getGraphics();
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
            g.dispose();
            errorOccurred = true;
        }
        image = nImage;
        return errorOccurred;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

}
