package com.mars.util;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Audio implements Runnable {
    private static final Audio INSTANCE = new Audio();
    private static final List<String> PLAYED_AUDIO_NAMES = new ArrayList<>();
    private volatile boolean muted = false;
    private Clip clip;

    private Audio() {}

    public static Audio getInstance() {
        return INSTANCE;
    }

    private void findAndPlay(String fileName) {
        if (PLAYED_AUDIO_NAMES.contains(fileName)) {
            if ("start.wav".equals(fileName)
                    || "developers.wav".equals(fileName)
                    || "developers_song.wav".equals(fileName)) {
                if (clip.isActive()) {
                    clip.stop();
                    return;
                }
            } else {
                return;
            }
        }
        PLAYED_AUDIO_NAMES.add(fileName);
        AudioInputStream audioInputStream = null;

        try {
            InputStream inputStream = JSONHandler.getFileFromResourceAsStream("audio/" + fileName);
            audioInputStream = AudioSystem.getAudioInputStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startThread() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        startThread();
    }

    public void play(String fileName) {
        if (!muted) {
            run();
            findAndPlay(fileName);
        }
    }

    public void mute() {
        setMuted(true);
        stop();
    }

    public void toggleMute() {
        if (getMuted()) {
            unMute();
        } else {
            mute();
        }
    }

    public void unMute() {
        setMuted(false);
    }

    public void stop() {
        if (clip.isActive()) {
            clip.stop();
            clip.close();
        }
    }

    private boolean getMuted() {
        return this.muted;
    }

    private void setMuted(Boolean muted) {
        this.muted = muted;
        // maybe need to close thread?
    }
}