package com.evan.demo.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.evan.demo.R;
import com.evan.demo.ui.fragment.Fragment01;
import com.evan.demo.ui.fragment.Fragment02;
import com.evan.demo.ui.fragment.Fragment03;
import com.evan.demo.ui.fragment.Fragment04;
import com.evan.demo.ui.widget.TabGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.home_viewpager)
    ViewPager mViewpager;
    @BindView(R.id.home_tablayout)
    TabLayout mTablayout;
    @BindView(R.id.home_tab_group)
    TabGroup mTabGroup;

    private List<Fragment> fragList;
    private HomeFragPagerAdapter mAdapter;
    private String[] mTabTitles = {"首页", "产品", "发现", "我的"};
    private int[] mTabIcons = {R.drawable.slt_menu_home,
            R.drawable.slt_menu_product,
            R.drawable.slt_menu_find,
            R.drawable.slt_menu_mine};

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragList = new ArrayList<>();
        fragList.add(new Fragment01());
        fragList.add(new Fragment02());
        fragList.add(new Fragment03());
        fragList.add(new Fragment04());

        mAdapter = new HomeFragPagerAdapter();

        mViewpager.setAdapter(mAdapter);

        mViewpager.setOffscreenPageLimit(3);

        mTabGroup.setupWithViewPager(mViewpager, true);
        mTablayout.setupWithViewPager(mViewpager);

        for (int i = 0; i < mAdapter.getCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mAdapter.getTabView(i));
            }
        }
        mViewpager.setCurrentItem(0);
    }

    private class HomeFragPagerAdapter extends FragmentPagerAdapter {

        public HomeFragPagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public int getCount() {
            return fragList == null ? 0 : fragList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("aaa", "getItem: " + position);
            return fragList.get(position);
        }

        public View getTabView(int position) {
            TextView tabView = new TextView(mActivity);
            tabView.setText(getPageTitle(position));
            tabView.setTextSize(12);
            // tabView.setTextColor(R.drawable.slt_home_tab_textcolor);
            // tabView.setTextColor(getResources().getColor(R.color.slt_home_tab_textcolor));
            tabView.setTextColor(getResources().getColorStateList(R.color.slt_home_tab_textcolor));
            Drawable icon = getResources().getDrawable(mTabIcons[position]);
            tabView.setCompoundDrawablesWithIntrinsicBounds(null, icon, null, null);
            tabView.setGravity(Gravity.CENTER_HORIZONTAL);
            return tabView;
        }

    }
}
