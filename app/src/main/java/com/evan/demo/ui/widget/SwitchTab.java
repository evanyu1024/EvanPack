package com.evan.demo.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evan.demo.R;

/**
 * 自定义View:导航标签
 * Created by evanyu on 16/7/18.
 */
public class SwitchTab extends FrameLayout implements View.OnClickListener {

    private Context mContext;
    private View contentView;
    private LinearLayout llLeft, llRight;
    private TextView tvLeft, tvRight;
    private View lineLeft, lineRight;
    private OnTabClickListener mListener;

    private static final int COLOR_SELECTED = Color.WHITE;
    private static final int COLOR_UNSELECTED = Color.parseColor("#99C4EC");

    public interface OnTabClickListener {
        int STATE_LEFT = 1;
        int STATE_RIGHT = 2;

        void onClick(View view, int state);
    }

    public void setOnTabClickListener(OnTabClickListener listener) {
        this.mListener = listener;
    }

    public SwitchTab(Context context) {
        this(context, null);
    }

    public SwitchTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = context;
        contentView = LayoutInflater.from(context).inflate(R.layout.view_switch_tab, this, true);
        findViews();
        parseAttrs(attrs);
        initListener();
        changeSelect(true);
    }

    private void parseAttrs(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.SwitchTab);
        String textLeft = ta.getString(R.styleable.SwitchTab_textLeft);
        String textRight = ta.getString(R.styleable.SwitchTab_textRight);

        tvLeft.setText(textLeft);
        tvRight.setText(textRight);
    }

    private void findViews() {
        llLeft = (LinearLayout) findViewById(R.id.ll_left);
        llRight = (LinearLayout) findViewById(R.id.ll_right);
        tvLeft = (TextView) contentView.findViewById(R.id.tv_left);
        tvRight = (TextView) contentView.findViewById(R.id.tv_right);
        lineLeft = contentView.findViewById(R.id.line_left);
        lineRight = contentView.findViewById(R.id.line_right);
    }

    private void initListener() {
        llLeft.setOnClickListener(this);
        llRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int state = -1;
        switch (v.getId()) {
            case R.id.ll_left:
                changeSelect(true);
                state = OnTabClickListener.STATE_LEFT;
                break;
            case R.id.ll_right:
                changeSelect(false);
                state = OnTabClickListener.STATE_RIGHT;
                break;
            default:
                break;
        }

        if (mListener != null) {
            mListener.onClick(v, state);
        }
    }

    private void changeSelect(boolean selectLeft) {
        if (selectLeft) {
            tvLeft.setTextColor(COLOR_SELECTED);
            lineLeft.setBackgroundColor(COLOR_SELECTED);
            tvRight.setTextColor(COLOR_UNSELECTED);
            lineRight.setBackgroundColor(COLOR_UNSELECTED);
        } else {
            tvLeft.setTextColor(COLOR_UNSELECTED);
            lineLeft.setBackgroundColor(COLOR_UNSELECTED);
            tvRight.setTextColor(COLOR_SELECTED);
            lineRight.setBackgroundColor(COLOR_SELECTED);
        }
    }

}
