package com.javapatterns.observer.monkeyking;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

class OCBox extends Canvas implements Observer {
    static final Color[] colors = {
            Color.black, Color.blue, Color.cyan,
            Color.darkGray, Color.gray, Color.green,
            Color.lightGray, Color.magenta,
            Color.orange, Color.pink, Color.red,
            Color.white, Color.yellow
    };
    Observable notifier;
    int x, y; // Locations in grid
    Color cColor = newColor();

    OCBox(int x, int y, Observable notifier) {
        this.x = x;
        this.y = y;
        notifier.addObserver(this);
        this.notifier = notifier;
        addMouseListener(new ML());
    }

    static final Color newColor() {
        return colors[
                (int) (Math.random() * colors.length)
                ];
    }

    public void paint(Graphics g) {
        g.setColor(cColor);
        Dimension s = getSize();
        g.fillRect(0, 0, s.width, s.height);
    }

    public void update(Observable o, Object arg) {
        OCBox clicked = (OCBox) arg;
        if (nextTo(clicked)) {
            cColor = clicked.cColor;
            repaint();
        }
    }

    private final boolean nextTo(OCBox b) {
        return Math.abs(x - b.x) <= 1 &&
                Math.abs(y - b.y) <= 1;
    }

    class ML extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            notifier.notifyObservers(OCBox.this);
        }
    }
} ///:~ 
