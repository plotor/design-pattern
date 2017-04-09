package com.javapatterns.observertimer.swingadapter;

import java.awt.*;

abstract class SwingTimerTask extends java.util.TimerTask {
    public abstract void doRun();

    public void run() {
        if (!EventQueue.isDispatchThread()) {
            EventQueue.invokeLater(this);
        } else {
            doRun();
        }
    }
}
    