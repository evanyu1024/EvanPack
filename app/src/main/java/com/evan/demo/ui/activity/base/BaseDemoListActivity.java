package com.evan.demo.ui.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.evan.demo.ui.widget.recyclerview.RecyclerViewUtils;
import com.evan.demo.ui.widget.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.evan.demo.ui.widget.recyclerview.listener.OnItemClickListener;

import java.util.List;

/**
 * demo列表页基类
 *
 * @author evanyu
 * @date 17/12/20
 */
public abstract class BaseDemoListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    public void onCreate() {
        mRecyclerView = new RecyclerView(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRecyclerView.setLayoutParams(layoutParams);
        setContentView(mRecyclerView);

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerViewUtils.initVerticalView(mContext, mRecyclerView);
        InnerAdapter adapter = new InnerAdapter();
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mContext, getActivityList().get(position).clazz));
            }
        });
    }

    protected abstract List<ActivityInfo> getActivityList();

    private class InnerAdapter extends BaseRecyclerViewAdapter<ActivityInfo> {

        public InnerAdapter() {
            super(mContext, getActivityList(), android.R.layout.simple_list_item_1);
        }

        @Override
        public void convert(InnerViewHolder holder, ActivityInfo itemBean, int position) {
            holder.setText(android.R.id.text1, itemBean.title);
        }
    }

    protected class ActivityInfo {
        String title;
        Class<? extends Activity> clazz;

        public ActivityInfo(String title, Class<? extends Activity> clazz) {
            this.title = title;
            this.clazz = clazz;
        }
    }
}
