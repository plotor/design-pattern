package com.javapatterns.state.audioplayer;

public class AudioPlayer {

    /**
     * @directed
     * @link aggregation
     */
    private AudioPlayerState audioPlayerState;

    public void stopButton() {
    }

    public void startButton() {
    }

    public void setState(AudioPlayerState state) {

    }

    public void startPlay() {
        System.out.println("Start playing");
    }

    public void stopPlay() {
        System.out.println("Stop playing");
    }
}
