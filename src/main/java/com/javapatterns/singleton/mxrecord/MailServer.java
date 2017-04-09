package com.javapatterns.singleton.mxrecord;

public class MailServer {
    private int priority;
    private String server;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

}
