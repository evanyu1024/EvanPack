package com.evan.demo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.ui.widget.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.evan.demo.ui.widget.recyclerview.adapter.HeaderAndFooterRecyclerViewAdapter;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemClickListener;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemLongClickListener;
import com.evan.demo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by evanyu on 16/10/25.
 */

public class RecyclerViewAdapterDemo extends BaseActivity {

    @Override
    protected void onCreate() {

    }

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private List<String> mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_adapter);
        ButterKnife.bind(this);

        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add("item" + i);
        }

        InnerAdapter testAdapter = new InnerAdapter();
        HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = new HeaderAndFooterRecyclerViewAdapter(testAdapter);


        ImageView view1 = new ImageView(this);
        ImageView view2 = new ImageView(this);

        view1.setImageResource(R.drawable.icon01);
        view2.setImageResource(R.drawable.icon02);
        // header view
        headerAndFooterAdapter.addHeaderView(view1);
        headerAndFooterAdapter.addHeaderView(view2);
        // footer view
        headerAndFooterAdapter.addFooterView(view1);
        headerAndFooterAdapter.addFooterView(view2);

        mRecyclerView.setAdapter(headerAndFooterAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 监听点击事件
        headerAndFooterAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToast("headerAndFooterAdapter...click....position = " + position);
            }
        });

        // 监听长按事件
        headerAndFooterAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                ToastUtils.showToast("headerAndFooterAdapter...long click....position = " + position);
                return true;
            }
        });

    }

    private class InnerAdapter extends BaseRecyclerViewAdapter<String> {

//        public InnerAdapter() {
//            super(RecyclerViewAdapterDemo.this, mData, R.layout.test_item_type1);
//        }

        public InnerAdapter() {
            super(RecyclerViewAdapterDemo.this, mData);
        }

        @Override
        public int[] getItemLayouts() {
            return new int[]{
                    R.layout.test_item_type1,
                    R.layout.test_item_type2,
                    R.layout.test_item_type3};
        }

        @Override
        public int getItemViewType(int position) {
            // 返回对应布局在布局数组中的索引
            if (position == 3 || position == 19) return 1;
            if (position == 10 || position == 20) return 2;
            return super.getItemViewType(position);
        }

        @Override
        public void convert(InnerViewHolder holder, String itemBean) {
            holder.setText(R.id.tv_item_test, itemBean);
        }
    }

}
