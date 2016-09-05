package com.evan.demo.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.evan.demo.utils.LogUtils;

import java.util.List;

/**
 * ViewPager适配器的基类
 * Created by evanyu on 16/8/4.
 */
public abstract class BaseViewPagerAdapter<T> extends PagerAdapter {

    protected List<T> mData;

    public void setData(List<T> data) {
        this.mData = data;
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public int getCount() {
        LogUtils.d("getCount()..." + (mData == null ? 0 : mData.size()));
        return mData == null ? 0 : mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        LogUtils.d("isViewFromObject");
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LogUtils.d("instantiateItem");
        return getItemView(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LogUtils.d("destroyItem");
        container.removeView((View) object);
    }

    protected abstract Object getItemView(ViewGroup container, int position);
}
