package com.evan.demo.ui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.evan.demo.utils.UIUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyToast {

    private static Handler mHandler = new Handler();

    private static BlockingQueue<MyToast> mQueue = new LinkedBlockingQueue<MyToast>();

    protected static AtomicInteger mAtomicInteger = new AtomicInteger(0);

    private WindowManager mWindowManager;

    private long mDurationMillis;

    private View mView;

    private WindowManager.LayoutParams mParams;

    private Context mContext;

    public static MyToast makeText(Context context, CharSequence text, long duration) {
        return new MyToast(context).setText(text).setDuration(duration)
                .setGravity(Gravity.BOTTOM, 0, UIUtils.dip2px(64));
    }

    public static MyToast makeText(Context context, int resId, long duration) {
        return new MyToast(context).setText(context.getResources().getText(resId)).setDuration(duration)
                .setGravity(Gravity.BOTTOM, 0, UIUtils.dip2px(64));
    }

    public MyToast(Context context) {

        mContext = context.getApplicationContext();
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.format = PixelFormat.TRANSLUCENT;
        mParams.windowAnimations = android.R.style.Animation_Toast;
        mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mParams.setTitle("Toast");
        mParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        mParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
    }


    public MyToast setGravity(int gravity, int xOffset, int yOffset) {

        final int finalGravity;
        if (Build.VERSION.SDK_INT >= 17) {
            final Configuration config = mView.getContext().getResources().getConfiguration();
            finalGravity = Gravity.getAbsoluteGravity(gravity, config.getLayoutDirection());
        } else {
            finalGravity = gravity;
        }
        mParams.gravity = finalGravity;
        if ((finalGravity & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.FILL_HORIZONTAL) {
            mParams.horizontalWeight = 1.0f;
        }
        if ((finalGravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.FILL_VERTICAL) {
            mParams.verticalWeight = 1.0f;
        }
        mParams.y = yOffset;
        mParams.x = xOffset;
        return this;
    }

    public MyToast setDuration(long durationMillis) {
        if (durationMillis < 0) {
            mDurationMillis = 0;
        }
        if (durationMillis == Toast.LENGTH_SHORT) {
            mDurationMillis = 2000;
        } else if (durationMillis == Toast.LENGTH_LONG) {
            mDurationMillis = 3500;
        } else {
            mDurationMillis = durationMillis;
        }
        return this;
    }


    public MyToast setView(View view) {
        mView = view;
        return this;
    }

    public View getView() {
        return mView;
    }


    public MyToast setMargin(float horizontalMargin, float verticalMargin) {
        mParams.horizontalMargin = horizontalMargin;
        mParams.verticalMargin = verticalMargin;
        return this;
    }


    public MyToast setText(CharSequence text) {
        if (mView == null) {
            View view = Toast.makeText(mContext, text, Toast.LENGTH_SHORT).getView();
            if (view != null) {
                TextView tv = (TextView) view.findViewById(android.R.id.message);
                tv.setText(text);
                setView(view);
            }
        } else {
            TextView tv = (TextView) mView.findViewById(android.R.id.message);
            tv.setText(text);
        }

        return this;
    }

    public MyToast setText(int resId) {
        if (mView == null) {
            View view = Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).getView();
            if (view != null) {
                TextView tv = (TextView) view.findViewById(android.R.id.message);
                tv.setText(mContext.getResources().getText(resId));
                setView(view);
            }
        } else {
            TextView tv = (TextView) mView.findViewById(android.R.id.message);
            tv.setText(mContext.getResources().getText(resId));
        }

        return this;
    }

    public void show() {
        mQueue.offer(this);

        if (0 == mAtomicInteger.get()) {
            mAtomicInteger.incrementAndGet();
            mHandler.post(mActivite);
        }
    }

    public void cancel() {
        if (0 == mAtomicInteger.get() && mQueue.isEmpty()) {
            return;
        }

        if (this.equals(mQueue.peek())) {
            mHandler.removeCallbacks(mActivite);
            mHandler.post(mHide);
            mHandler.post(mActivite);
        }

    }

    private void handleShow() {
        if (mView != null) {
            if (mView.getParent() != null) {
                mWindowManager.removeView(mView);
            }
            mWindowManager.addView(mView, mParams);
        }
    }

    private void handleHide() {
        final MyToast myToast = mQueue.poll();
        if (myToast.mView != null) {
            if (mView.getParent() != null) {
                mWindowManager.removeView(mView);
            }
        }
    }

    private static void activeQueue() {
        MyToast myToast = mQueue.peek();
        if (myToast == null) {
            mAtomicInteger.decrementAndGet();
        } else {
            mHandler.post(myToast.mShow);
            mHandler.postDelayed(myToast.mHide, myToast.mDurationMillis);
            mHandler.postDelayed(mActivite, myToast.mDurationMillis);
        }
    }

    private final Runnable mShow = new Runnable() {
        @Override
        public void run() {
            handleShow();
        }
    };

    private final Runnable mHide = new Runnable() {
        @Override
        public void run() {
            handleHide();
        }
    };

    private final static Runnable mActivite = new Runnable() {
        @Override
        public void run() {
            activeQueue();
        }
    };

}