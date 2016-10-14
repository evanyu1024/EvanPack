package com.evan.demo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by evanyu on 16/10/13.
 */
public class ExpandVideoView extends VideoView {

    public ExpandVideoView(Context context) {
        super(context);
    }

    public ExpandVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(getWidth(), widthMeasureSpec);
        int height = getDefaultSize(getHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
