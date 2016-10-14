package com.evan.demo.ui.activity;

import android.media.MediaPlayer;
import android.widget.MediaController;
import android.widget.VideoView;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * VideoViewDemo
 * Created by evanyu on 16/10/13.
 */
public class VideoViewDemo extends BaseActivity {

    @BindView(R.id.videoview)
    VideoView mVideoView;

    private String videoUrl = "http://183.131.82.140/v1/aWg9MkI5QjYzNzIzQTE1NUE5NjE0RjQ1N0JGODNEMDIxNEQ1RUY3MTJEQSZmbmFtZT0yMDU4MTI1X2hpZ2gubXA0JnRva2VuPU0wTTJRamMxUXpVNVJVTkRNMFl3UVVReE5rWTRRVFZDUXpoR05qRXdOREF6TURSRVFqWTRRVjkzWldKZk1UUTNOakk1TURFd01RPT0mdXNlcj13ZWJfMl8yMDE2LTEwLTEzLTAwLTMwJnZmPU1Dd3pNVGhHUkE9PSZ1c2VyX2lkPSZ1c2VyX3Rva2VuPSZtYWM9MTQ3NDA0ODQ1N2M4MzNhJmFwcF9jb2RlPXdlYg%3D%3D?random=0.49214506801217794";
    private String imgUrl = "http://odt6w2hxj.bkt.clouddn.com/57fafcaca8f6c0.84487974.mp4?vframe/jpg/offset/5&e=1476271597&token=TL6OQNsG7mo7oyGclVOF67eUBqtkRx4MfO2mebDs:fudLKgQANzUoi4iSVSqJjSOOhIM=";

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_videoview);
        ButterKnife.bind(this);

        mVideoView.setVideoPath(videoUrl);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                ToastUtils.showToast("video prepared");
            }
        });
    }

    @OnClick(R.id.btn_play)
    public void onClick() {
        mVideoView.start();
    }
}
