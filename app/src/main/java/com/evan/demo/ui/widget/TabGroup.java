package com.evan.demo.ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by evanyu on 16/8/11.
 */
public class TabGroup extends SuperRadioGroup implements ViewPager.OnPageChangeListener, SuperRadioGroup.OnCheckedChangeListener {

    private ViewPager mViewPager;
    private List<Integer> rbtnIds;
    private boolean smoothScroll;

    // 为解决OnPageChangeListener回调被反复调用的问题,添加以下两个字段
    private boolean pageChangeBySlide = false;
    private int checkedBtnIdTemp = getCheckedRadioButtonId(); // 临时记录被check的btnid

    public TabGroup(Context context) {
        super(context);
    }

    public TabGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        rbtnIds = findAllCompoundButtonId(this);
    }

    public void setupWithViewPager(ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
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
        this.setOnCheckedChangeListener(this);
    }

    public void addViewPagerListener(ViewPager.OnPageChangeListener listener) {
        if (mViewPager != null) {
            mViewPager.addOnPageChangeListener(listener);
        }
    }

    @Override
    public void onCheckedChanged(SuperRadioGroup group, int checkedId) {
        int position = rbtnIds.indexOf(checkedId);
        if (mViewPager != null && position >= 0 && (checkedId == checkedBtnIdTemp || !pageChangeBySlide)) {
            mViewPager.setCurrentItem(position, smoothScroll);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position < rbtnIds.size()) {
            checkedBtnIdTemp = rbtnIds.get(position);
            if (checkedBtnIdTemp >= 0 && getCheckedRadioButtonId() != checkedBtnIdTemp) {
                pageChangeBySlide = true;
                check(checkedBtnIdTemp);
                pageChangeBySlide = false;
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
