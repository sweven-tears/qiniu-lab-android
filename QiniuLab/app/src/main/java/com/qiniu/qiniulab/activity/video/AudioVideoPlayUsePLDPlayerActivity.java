package com.qiniu.qiniulab.activity.video;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.pili.pldroid.player.widget.VideoView;
import com.qiniu.qiniulab.R;
import com.qiniu.qiniulab.activity.video.widget.MediaController;
import com.qiniu.qiniulab.utils.Tools;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class AudioVideoPlayUsePLDPlayerActivity extends ActionBarActivity {
    private VideoView videoPlayView;
    private MediaController videoPlayController;
    private TextView videoPlayLogTextView;

    public AudioVideoPlayUsePLDPlayerActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.simple_video_play_use_pldplayer_activity);
        this.initVideoPlay();
    }

    private void initVideoPlay() {
        this.videoPlayController = new MediaController(this);
        this.videoPlayView = (VideoView) this
                .findViewById(R.id.simple_video_play_pldplayer);
        this.videoPlayLogTextView = (TextView) this
                .findViewById(R.id.simple_video_play_log_textview);
        videoPlayView.setMediaController(videoPlayController);
        videoPlayController.setMediaPlayer(videoPlayView);
        videoPlayController.setAnchorView(videoPlayView);
        String videoName = this.getIntent().getStringExtra("VideoName");
        String videoUrl = this.getIntent().getStringExtra("VideoUrl");
        this.setTitle(videoName);
        videoPlayView.setVideoURI(Uri.parse(videoUrl));
        final long startTime = System.currentTimeMillis();
        videoPlayView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(IMediaPlayer mp) {
                long endTime = System.currentTimeMillis();
                long loadTime = endTime - startTime;
                videoPlayLogTextView.append("Load Time: "
                        + Tools.formatMilliSeconds(loadTime) + "\r\n");
                mp.start();
            }
        });

    }
}