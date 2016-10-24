package com.evan.demo.ui.widget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by evanyu on 16/8/31.
 */
public class UIToast {

    private static final int MESSAGE_TIMEOUT = 2;
    private WindowManager mWindowManager;
    private long time;
    private View mView;
    private WindowManager.LayoutParams mParams;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_TIMEOUT:
                    cancel();
                    break;
            }
        }
    };

    private UIToast(Context context, String text, long time) {
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        // mHandler = newengine WorkerHandler();

        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        mView = toast.getView();

        mParams = new WindowManager.LayoutParams();
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.format = PixelFormat.TRANSLUCENT;
        mParams.windowAnimations = toast.getView().getAnimation().INFINITE;
        mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mParams.setTitle("Toast");
        mParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        mParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        this.time = time;
    }

    public static UIToast makeText(Context context, String text, long time) {
        UIToast toast = new UIToast(context, text, time);
        return toast;
    }

    public void show() {
        mWindowManager.addView(mView, mParams);
        mHandler.sendEmptyMessageDelayed(MESSAGE_TIMEOUT, time);
    }

    public void cancel() {
        mWindowManager.removeView(mView);
    }

}


