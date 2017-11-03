package com.evan.demo.ui.activity.demoview;

import android.view.MotionEvent;
import android.view.View;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.ui.widget.MyButton;
import com.evan.demo.ui.widget.MyFrameLayout;
import com.evan.demo.ui.widget.MyImageView;
import com.evan.demo.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 事件分发传递
 * Created by evanyu on 16/12/19.
 */
public class EventDispatchDemo extends BaseActivity {

    @BindView(R.id.btn)
    MyButton mBtn;
    @BindView(R.id.iv)
    MyImageView mIv;
    @BindView(R.id.frame)
    MyFrameLayout mFrame;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_event_dispatch);
        ButterKnife.bind(this);

        mIv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        LogUtils.d("mIv...onTouch...down");
                        return false;
                    // break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtils.d("mIv...onTouch...move");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtils.d("mIv...onTouch...up");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("mIv...onClick");
            }
        });

        mBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.d("mBtn...onTouch");
                return false;
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("mBtn...onClick");
            }
        });

        mFrame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.d("mFrame...onTouch");
                return false;
            }
        });

        mFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("mFrame...onClick");
            }
        });
    }
}
