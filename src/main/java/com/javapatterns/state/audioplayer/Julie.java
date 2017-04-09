package com.javapatterns.state.audioplayer;

public class Julie {
    /**
     * @link aggregation
     * @directed
     */
    private static AudioPlayer player;

    public void main(String[] args) {
        player = new AudioPlayer();

        player.startButton();
        player.stopButton();
    }
}
