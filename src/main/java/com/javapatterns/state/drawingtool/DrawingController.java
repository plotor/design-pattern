package com.javapatterns.state.drawingtool;

public class DrawingController {
    /**
     * @link aggregation
     * @clientRole Current Tool
     */
    private Tool tool;

    public void mousePressed() {
        tool.handleMousePress();
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public void processKeyboard() {
    }

    public void initialize() {
    }
}
