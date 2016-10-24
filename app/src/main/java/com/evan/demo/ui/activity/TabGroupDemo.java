package com.evan.demo.ui.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.ui.widget.TabGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by evanyu on 16/10/24.
 */
public class TabGroupDemo extends BaseActivity {


    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabgroup)
    TabGroup mTabGroup;

    private int imgs[] = {R.drawable.lz01, R.drawable.lz02, R.drawable.lz03};
    private InnerAdapter mAdapter;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_tab_group);
        ButterKnife.bind(this);

        mAdapter = new InnerAdapter();
        mViewPager.setAdapter(mAdapter);
        mTabGroup.setupWithViewPager(mViewPager);
    }

    private class InnerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgs == null ? 0 : imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(params);
            iv.setImageResource(imgs[position]);
            iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

}
