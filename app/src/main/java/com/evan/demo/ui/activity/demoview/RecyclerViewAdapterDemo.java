package com.evan.demo.ui.activity.demoview;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * RecyclerViewAdapter demo
 * Created by evanyu on 16/10/25.
 */
public class RecyclerViewAdapterDemo extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private List<String> mData;
    private ImageView view1, view2;
//    private InnerAdapter mAdapter;
    private PtrClassicFrameLayout mPtrFrame;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_recyclerview_adapter);
        ButterKnife.bind(this);

//        initRecyclerView();
//
//        initPtrList();

    }

//    private void initRecyclerView() {
//        mData = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            mData.add("item" + i);
//        }
//
//        // 准备两个View对象
//        view1 = new ImageView(this);
//        view2 = new ImageView(this);
//        view1.setImageResource(R.drawable.icon01);
//        view2.setImageResource(R.drawable.icon02);
//
//        view1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showToast("view1....");
//            }
//        });
//
//        view2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showToast("view2....");
//            }
//        });
//
//        mAdapter = new InnerAdapter();
//        // header view
//        mAdapter.addHeaderView(view1);
//        // footer view
//        mAdapter.addFooterView(view2);
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        mRecyclerView.setAdapter(mAdapter);
//
//        mAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                ToastUtils.showToast("click....position = " + position);
//            }
//        });
//
//        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(View view, int position) {
//                ToastUtils.showToast("long click....position = " + position);
//                return false;
//            }
//        });
//    }
//
//    private void initPtrList() {
//
//        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.ptr_frame);
//        mPtrFrame.setLastUpdateTimeRelateObject(this);
//        mPtrFrame.setPtrHandler(new PtrHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                updateData();
//            }
//
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//                // return true;
//            }
//        });
//
//        // the following are default settings
//        mPtrFrame.setResistance(1.7f);
//        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
//        mPtrFrame.setDurationToClose(200);
//        mPtrFrame.setDurationToCloseHeader(1000);
//        // default is false
//        mPtrFrame.setPullToRefresh(false);
//        // default is true
//        mPtrFrame.setKeepHeaderWhenRefresh(true);
//    }
//
//    private void updateData() {
//        mPtrFrame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                int n = mData.size();
//                for (int i = n - 1; i < n + 9; i++) {
//                    mData.add("item" + i);
//                }
//                mAdapter.notifyDataSetChanged();
//                mPtrFrame.refreshComplete();
//            }
//        }, 2000);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_recyclerview_adapter, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_add_headerview:
//                mAdapter.addHeaderView(view2);
//                mAdapter.notifyDataSetChanged();
//                break;
//            case R.id.menu_remove_headerview:
//                mAdapter.removeHeaderView(view2);
//                mAdapter.notifyDataSetChanged();
//                break;
//            case R.id.menu_add_footerview:
//                break;
//            case R.id.menu_remove_footerview:
//                break;
//            case R.id.menu_add_item:
//                break;
//            default:
//                break;
//        }
//        return true;
//    }
//
//    private class InnerAdapter extends DefaultRecyclerViewAdapter<String> {
//
//        public InnerAdapter() {
//            super(mContext, mData);
//        }
//
//        @Override
//        public int[] getItemLayouts() {
//            return new int[]{
//                    R.layout.test_item_type1,
//                    R.layout.test_item_type2,
//                    R.layout.test_item_type3};
//        }
//
//        @Override
//        public int getNormalItemViewType(int position) {
//            if (position == 3 || position == 19) return 1;
//            if (position == 10 || position == 20) return 2;
//            return super.getNormalItemViewType(position);
//        }
//
//        @Override
//        public void convert(InnerViewHolder holder, String itemBean, int position) {
//            holder.setText(R.id.tv_item_test, itemBean);
//        }
//    }

}
