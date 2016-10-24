package com.evan.demo.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.evan.demo.R;
import com.evan.demo.utils.ViewIdGenerator;

/**
 * 产品相关页面中的标签选项卡控件
 * Created by evanyu on 16/9/5.
 */
public class ProductTab extends FrameLayout {

    RadioButton rbtnTab;

    public ProductTab(Context context) {
        this(context, null);
    }

    public ProductTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_product_tab, this, true);
        // View.inflate(context, R.layout.view_product_tab, this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        rbtnTab = (RadioButton) ((FrameLayout) findViewById(R.id.frame_rbtn)).getChildAt(0);
        // rbtnTab.setId(View.generateViewId());
        rbtnTab.setId(ViewIdGenerator.generateViewId());
        rbtnTab.setChecked(true);
        /*rbtnTab.setOnClickListener(newengine OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("哈哈..." + rbtnTab.getId());
            }
        });*/
    }
}
