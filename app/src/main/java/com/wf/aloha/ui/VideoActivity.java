package com.wf.aloha.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.wf.aloha.R;
import com.wf.aloha.utils.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoActivity extends AppCompatActivity {

    private static final int STORAGE_REQUEST = 100;
    @BindView(R.id.bt_music_start)
    Button btMusicStart;
    @BindView(R.id.bt_music_pause)
    Button btMusicPause;
    @BindView(R.id.bt_music_reset)
    Button btMusicReset;
    @BindView(R.id.bt_video_start)
    Button btVideoStart;
    @BindView(R.id.bt_video_pause)
    Button btVideoPause;
    @BindView(R.id.bt_video_reset)
    Button btVideoReset;
    @BindView(R.id.video_view)
    VideoView videoView;
    @BindView(R.id.iv_first_pic)
    ImageView ivFirstPic;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        mediaPlayer = new MediaPlayer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int i = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (i == PackageManager.PERMISSION_GRANTED) {
                initMediaPlayer();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST);
            }
        } else {
            initMediaPlayer();
        }
    }

    private void initMediaPlayer() {
        File file = new File(getExternalCacheDir(), "xzq.mp3");
        File videoFile = new File(getExternalCacheDir(), "guilin.mp4");
//        videoView.setVideoPath(videoFile.getPath());//本地video
        Uri videoUri = Uri.parse("https://v.daokoudai.com/my.mp4");
        videoView.setVideoURI(Uri.parse("https://v.daokoudai.com/my.mp4"));//网络video

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        //获取网络视频第一帧图片
        if (Build.VERSION.SDK_INT >= 14) {
            retriever.setDataSource("https://v.daokoudai.com/my.mp4", new HashMap<String, String>());
        } else {
            retriever.setDataSource(videoUri.getPath());
        }
        Bitmap firstPic = retriever.getFrameAtTime();
        ivFirstPic.setImageBitmap(firstPic);
        videoView.setVisibility(View.GONE);
        
        try {
//            mediaPlayer.setDataSource(file.getPath());//本地文件播放
            mediaPlayer.setDataSource(this, Uri.parse("http://other.web.rh01.sycdn.kuwo.cn/resource/n3/92/27/2554399261.mp3"));//网络文件播放
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    ToastUtils.showInstance("please allow external storage permission");
                    return;
                }
                break;
        }
    }

    @OnClick({R.id.bt_music_start, R.id.bt_music_pause, R.id.bt_music_reset, R.id.bt_video_start, R.id.bt_video_pause, R.id.bt_video_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_music_start:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.bt_music_pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.bt_music_reset:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                }
                initMediaPlayer();
                break;
            case R.id.bt_video_start:
                if (!videoView.isPlaying()) {
                    videoView.setVisibility(View.VISIBLE);
                    ivFirstPic.setVisibility(View.GONE);
                    videoView.start();
                }
                break;
            case R.id.bt_video_pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.bt_video_reset:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
        }
    }

    //在manifest里设置了横竖屏切换的参数。这里是在切换时会走的方法。要处理的放在里面
//    android:configChanges="screenSize|keyboardHidden|orientation"
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        
    }
}
