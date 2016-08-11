package com.evan.demo.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.evan.demo.R;
import com.evan.demo.ui.adapter.BaseViewPagerAdapter;

import java.util.List;

/**
 * 广告轮播控件
 * Created by evanyu on 16/8/3.
 */
public class BannerView extends FrameLayout {

    private Context mContext;
    private ViewPager mViewPager; // 图片容器
    private LinearLayout mLLPoints; // 圆点容器
    private List<View> pointList; // 圆点集合
    // private List<String> mImgUrls; // 图片地址集合
    private InnerPagerAdapter mAdapter;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_banner, this, true);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mLLPoints = (LinearLayout) findViewById(R.id.ll_points);
        mViewPager.setAdapter(mAdapter = new InnerPagerAdapter());
    }

    public void setData(List<String> imgUrls) {
        mAdapter.setData(imgUrls);
        mAdapter.notifyDataSetChanged();
    }

    private class InnerPagerAdapter extends BaseViewPagerAdapter<String> {

        @Override
        protected Object getItemView(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            /*Glide.with(mContext)
                    .load(getData().get(position))
                    .dontAnimate() // 去除淡入淡出效果
                    .into(imageView);*/
            imageView.setImageResource(R.mipmap.ic_launcher);
            container.addView(imageView);
            return imageView;
        }
    }

}
