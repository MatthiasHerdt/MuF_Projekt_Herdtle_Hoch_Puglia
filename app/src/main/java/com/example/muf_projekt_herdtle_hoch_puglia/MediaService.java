package com.example.muf_projekt_herdtle_hoch_puglia;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import java.io.IOException;

public class MediaService extends Service {

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return new MediaBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnErrorListener((mediaPlayer, what, extra) -> {
            switch (what) {
                case MediaPlayer.MEDIA_ERROR_IO:
                default:
                    return false;
            }
        });
        mediaPlayer.setOnPreparedListener(mediaPlayer -> {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();

        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public class MediaBinder extends Binder {
        public boolean play(@RawRes int mediaFile){
            try{
                mediaPlayer.reset();
                mediaPlayer.setDataSource(getResources().openRawResourceFd(mediaFile));
                mediaPlayer.prepareAsync();
                return true;
            } catch (IOException ex) {
                return false;
            }
        }
    }
}
