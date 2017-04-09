package com.javapatterns.observertimer.cursor;

import java.applet.Applet;
import javax.swing.*;

public class RotatingCursor extends Applet {
    private Action timerAction;
    private Timer timer;

    public void init() {
        timerAction = new TimerAction(this);
        timer = new Timer(300, timerAction);
        timer.start();
    }

}
