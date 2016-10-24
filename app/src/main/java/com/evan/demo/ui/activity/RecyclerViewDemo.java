package com.evan.demo.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.ui.adapter.base.CommonRecyclerViewAdapter;
import com.evan.demo.ui.widget.recyclerview.decorator.DefaultDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * RecyclerView演示
 * Created by evanyu on 16/10/10.
 */
public class RecyclerViewDemo extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.ptr_frame)
    PtrFrameLayout mPtrFrame;

    private List<String> mData;
    private InnerAdapter mAdapter;

    private LinearLayoutManager mLineaLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        mLineaLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mGridLayoutManager = new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.addItemDecoration(new DefaultDividerItemDecoration(mContext, DefaultDividerItemDecoration.VERTICAL_LIST));
        // mRecyclerView.addItemDecoration(newengine DefaultDividerItemDecoration(mActivity, DefaultDividerItemDecoration.HORIZONTAL_LIST));
        // mRecyclerView.addItemDecoration(newengine DefaultBorderDividerItemDecoration(50, 50));
        mRecyclerView.setLayoutManager(mLineaLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter = new InnerAdapter());

        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("item" + i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_linear_port: // 线性纵向
                mLineaLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mLineaLinearLayoutManager);
                break;
            case R.id.menu_linear_land: // 线性横向
                mLineaLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(mLineaLinearLayoutManager);
                break;
            case R.id.menu_grid_port: // 网格纵向
                mGridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mGridLayoutManager);
                break;
            case R.id.menu_grid_land: // 网格横向
                mGridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(mGridLayoutManager);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class InnerAdapter extends CommonRecyclerViewAdapter<String> {

        public InnerAdapter() {
            super(mContext, mData, android.R.layout.simple_list_item_1);
        }

        @Override
        public void convert(InnerViewHolder holder, String item) {
            holder.setText(android.R.id.text1, item);
        }
    }

}
