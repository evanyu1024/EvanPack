package com.evan.demo.ui.activity;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * VitamioDemo
 * Created by evanyu on 16/10/12.
 */
public class VitamioDemo extends BaseActivity {

    private String videoUrl = "http://www.modrails.com/videos/passenger_nginx.mov";
    private String imgUrl = "http://odt6w2hxj.bkt.clouddn.com/57fafcaca8f6c0.84487974.mp4?vframe/jpg/offset/5&e=1476271597&token=TL6OQNsG7mo7oyGclVOF67eUBqtkRx4MfO2mebDs:fudLKgQANzUoi4iSVSqJjSOOhIM=";

    @BindView(R.id.videoview)
    VideoView mVideoView;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_vitamio);
        ButterKnife.bind(this);

        mVideoView.setVideoPath(videoUrl);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                ToastUtils.showToast("video prepare");
                mVideoView.pause();
            }
        });
    }


    @OnClick(R.id.btn_play)
    public void onClick() {
        mVideoView.start();
    }
}
