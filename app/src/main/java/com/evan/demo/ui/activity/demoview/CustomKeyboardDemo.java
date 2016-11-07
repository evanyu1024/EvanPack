package com.evan.demo.ui.activity.demoview;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;

import butterknife.ButterKnife;


/**
 * 自定义软键盘
 * Created by evanyu on 16/9/30.
 */
public class CustomKeyboardDemo extends BaseActivity {

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_custom_keyboard);
        ButterKnife.bind(this);
    }

}
