package com.sk.quantumsudio.projectq.headline.LiveNews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.sk.quantumsudio.projectq.headline.R;

public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String TAG = "PlayerActivity";
    public static final String DEVELOPER_KEY = "AIzaSyDYLeGXhKhrhCT4NkcDEdUvtdTeuyUaY5w";   //Developer Key Here

    YouTubePlayer player;
    String videoId;
    @Override
    protected void onCreate(Bundle bundle) {
        Log.d(TAG, "onCreate: Inside onCreate of PlayerActivity");
        super.onCreate(bundle);
        setContentView(R.layout.activity_player);

        YouTubePlayerView playerView = findViewById(R.id.youTubePlayerView);   //Initialization of youtubePlayerview Widget
        playerView.initialize(DEVELOPER_KEY,this);

        Bundle b = getIntent().getExtras(); //receives videoId from bundle of fragment
        videoId = b.getString("liveNewsId");

    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
        Log.d(TAG, "onInitializationSuccess: YoutubePlayer successfully initialized");
        this.player = player;
        if(!b){
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);  //loads the video
            player.setFullscreen(true);
            player.loadVideo(videoId);
        }
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.d(TAG, "onInitializationFailure: youtubePlayer initilization failed");
        Toast.makeText(this, "Video playback error! Try again later.", Toast.LENGTH_LONG).show();
    }
}
