package com.example.elvis;

import android.media.MediaPlayer;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class MyPlayer {

    ArrayList<MediaPlayer> melodyList = new ArrayList<>();
    private int currentMelody = 0;

    public void addMelody(MediaPlayer melody) {
        melodyList.add(melody);

    }

    public void nextMelody() {
        if (melodyList.get(currentMelody).isPlaying()) {
            melodyList.get(currentMelody).pause();
            melodyList.get(currentMelody).seekTo(0);
            currentMelody++;
            if (currentMelody >= melodyList.size()) {
                currentMelody = 0;
            }
            melodyList.get(currentMelody).start();
        } else {

            melodyList.get(currentMelody).start();
        }
    }


    public void pauseMelody() {

        melodyList.get(currentMelody).pause();

    }


    public void startMelody() {
        if (!melodyList.get(currentMelody).isPlaying()) {

            melodyList.get(currentMelody).start();

        }

    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
