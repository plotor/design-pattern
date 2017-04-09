package com.javapatterns.observersax.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConcreteListener implements MouseListener {
    ConcreteListener() {
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getWhen());
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
