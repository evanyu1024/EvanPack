package com.evan.demo.ui.activity.demoview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.ui.widget.recyclerview.adapter.DefaultRecyclerViewAdapter;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemClickListener;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemLongClickListener;
import com.evan.demo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerViewAdapter demo
 * Created by evanyu on 16/10/25.
 */
public class RecyclerViewAdapterDemo extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private List<String> mData;
    private ImageView view1, view2;
    private InnerAdapter mAdapter;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_recyclerview_adapter);
        ButterKnife.bind(this);

        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add("item" + i);
        }

        // 准备两个View对象
        view1 = new ImageView(this);
        view2 = new ImageView(this);
        view1.setImageResource(R.drawable.icon01);
        view2.setImageResource(R.drawable.icon02);

        mAdapter = new InnerAdapter();
        // header view
        mAdapter.addHeaderView(view1);
        // footer view
        mAdapter.addFooterView(view1);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToast("click....position = " + position);
            }
        });

        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                ToastUtils.showToast("long click....position = " + position);
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview_adapter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_headerview:
                mAdapter.addHeaderView(view2);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.menu_remove_headerview:
                mAdapter.removeHeaderView(view2);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.menu_add_footerview:
                break;
            case R.id.menu_remove_footerview:
                break;
            case R.id.menu_add_item:
                break;
            default:
                break;
        }
        return true;
    }

    private class InnerAdapter extends DefaultRecyclerViewAdapter<String> {

        public InnerAdapter() {
            super(mContext, mData);
        }

        @Override
        public int[] getItemLayouts() {
            return new int[]{
                    R.layout.test_item_type1,
                    R.layout.test_item_type2,
                    R.layout.test_item_type3};
        }

        @Override
        public int getNormalItemViewType(int position) {
            if (position == 3 || position == 19) return 1;
            if (position == 10 || position == 20) return 2;
            return super.getNormalItemViewType(position);
        }

        @Override
        public void convert(InnerViewHolder holder, String itemBean, int position) {
            holder.setText(R.id.tv_item_test, itemBean);
        }
    }

}
