package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class MainActivity extends AppCompatActivity {

    String videoLink = "https://firebasestorage.googleapis.com/v0/b/mcclab-4c07a.appspot.com/o/media%2Fvideos%2Fphp%2FPHP%20Web%20Development%20(2021).mp4?alt=media&token=637b6826-e68e-4083-84a1-5460e3048249";

    ActivityMainBinding activityMainBinding;
    PlayerView pv;
    SimpleExoPlayer player;
    boolean playWhenReady = true;
    long currentPosition = 0;
    int currentWindow = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

       pv = activityMainBinding.playerView;
    }

    private void initPlayer(){
        player = new SimpleExoPlayer.Builder(this).build();
        pv.setPlayer(player);

        MediaItem item = MediaItem.fromUri(videoLink);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, currentPosition);
        player.prepare();
        player.addMediaItem(item);
    }

    private void releasePlayer(){
        if(player!=null){
            playWhenReady = player.getPlayWhenReady();
            currentWindow = player.getCurrentWindowIndex();
            currentPosition = player.getCurrentPosition();
            player = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player!=null){
            initPlayer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }
}