package edu.volstate.soundgestures;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class MediaPlayerFactory {
    Activity activity;

    public MediaPlayerFactory(Activity activity) {
        this.activity = activity;
    }

    public void playMedia(int media) {
        Log.i("playMedia", String.valueOf(media));
        MediaPlayer mediaPlayer = MediaPlayer.create(activity, media);
        mediaPlayer.start();
    }
}
