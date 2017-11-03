package com.evan.demo.ui.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * Created by evanyu on 17/1/9.
 */

public class ExpandScrollView extends NestedScrollView {

    public ExpandScrollView(Context context) {
        super(context);
    }

    public ExpandScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, 300, isTouchEvent);
    }
}
