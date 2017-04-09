package com.javapatterns.proxy.imageloader;

import java.awt.*;
import javax.swing.*;

public class ImageIconProxy implements Icon {
    boolean isIconCreated = false;
    private ImageIcon realIcon = null;
    private String imageName;
    private int width;
    private int height;

    public ImageIconProxy(String imageName, int width, int height) {
        this.imageName = imageName;
        this.width = width;
        this.height = height;
    }

    // The proxy's paint() method is overloaded to draw a border
    // and a message "Loading author's photo.." while the image
    // loads. After the image has loaded, it is drawn. Notice
    // that the proxy does not load the image until it is
    // actually needed.
    public void paintIcon(final Component c, Graphics g, int x, int y) {
        if (isIconCreated) {
            realIcon.paintIcon(c, g, x, y);
            g.drawString("Java and Patterns by Jeff Yan, Ph.D", x + 20, y + 370);
        } else {
            g.drawRect(x, y, width - 1, height - 1);
            g.drawString("Loading author's photo...", x + 20, y + 20);

            // The image is being loaded on another thread.
            synchronized (this) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            // Slow down the image-loading process.
                            Thread.currentThread().sleep(2000);

                            // ImageIcon constructor creates the image.
                            realIcon = new ImageIcon(imageName);
                            isIconCreated = true;
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        // Repaint the icon's component after the
                        // icon has been created.
                        c.repaint();
                    }
                });
            }
        }
    }

    public int getIconWidth() {
        return realIcon.getIconWidth();
    }

    public int getIconHeight() {
        return realIcon.getIconHeight();
    }
}
