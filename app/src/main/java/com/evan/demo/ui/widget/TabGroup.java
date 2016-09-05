package com.evan.demo.ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evanyu on 16/8/11.
 */
public class TabGroup extends RadioGroup implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private ViewPager mViewPager;
    private List<Integer> rbtnIds;
    private boolean smoothScroll;

    public TabGroup(Context context) {
        this(context, null);
    }

    public TabGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        rbtnIds = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            rbtnIds.add(getChildAt(i).getId());
        }
        this.setOnCheckedChangeListener(this);
    }

    public void setupWithViewPager(ViewPager viewPager) {
        setupWithViewPager(viewPager, false);
    }

    public void setupWithViewPager(ViewPager viewPager, boolean smoothScroll) {
        if (viewPager == null) {
            if (mViewPager != null) {
                mViewPager.removeOnPageChangeListener(this);
                mViewPager = null;
            }
            return;
        }
        this.smoothScroll = smoothScroll;
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (position < rbtnIds.size()) {
            int rbtnId = rbtnIds.get(position);
            if (rbtnId >= 0 && getCheckedRadioButtonId() != rbtnId) {
                check(rbtnId);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int position = rbtnIds.indexOf(checkedId);
        if (mViewPager != null && position >= 0) {
            mViewPager.setCurrentItem(position, smoothScroll);
        }
    }
}
