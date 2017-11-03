package com.evan.demo.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.EditText;

import com.evan.demo.R;

/**
 * Created by evanyu on 16/12/6.
 */

public class EditTextWithDel extends EditText {

    private Context mContext;
    private Drawable clearDrawable;

    public EditTextWithDel(Context context) {
        super(context);
        initView(context, null);
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    public void initView(Context context, AttributeSet attrs) {
        mContext = context;
        setPadding(getPaddingLeft(), getPaddingTop(), dp2px(8), getPaddingRight());
        parseAttrs(attrs);
        handleClearIconVisiable();

        // add text watcher
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handleClearIconVisiable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void parseAttrs(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.EditTextWithDel);
        int clearIconRes = a.getResourceId(R.styleable.EditTextWithDel_clearIcon, 0);
        a.recycle();

        if (clearIconRes == 0) {
            clearDrawable = getResources().getDrawable(R.drawable.icon_et_clear);
        } else {
            clearDrawable = getResources().getDrawable(clearIconRes);
        }
    }

    /**
     * 处理清空按钮的可见情况
     */
    private void handleClearIconVisiable() {
        if (TextUtils.isEmpty(getText())) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else if (clearDrawable != null) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, clearDrawable, null);
        }
    }

    /**
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (clearDrawable != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private int dp2px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }
}
