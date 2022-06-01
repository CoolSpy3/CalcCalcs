package com.coolspy3.calccalcs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public abstract class ImageBuffer extends JComponent
{

    public static final ImageBuffer NO_IMAGE = new ImageBuffer()
    {
        @Override
        public void render(Graphics2D g)
        {}
    };

    private Image image;

    public ImageBuffer()
    {
        setSize(1, 1);
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.dispose();
    }

    public abstract void render(Graphics2D g);

    public boolean updateImage()
    {
        boolean errorOccurred = false;
        Image nImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        try
        {
            Graphics2D iGraphics = (Graphics2D) nImage.getGraphics();
            iGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            render(iGraphics);
            iGraphics.dispose();
        }
        catch (Exception e)
        {
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
    protected void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
    }

}
