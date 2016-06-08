package com.evan.pulltorefreshdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PtrActivity extends AppCompatActivity {

    private Activity mContext = this;

    @BindView(R.id.ptr_list)
    PullToRefreshListView mPtrList;

    private LinkedList<String> data = new LinkedList<String>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptf);
        ButterKnife.bind(this);

        initData();

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        mPtrList.setAdapter(mAdapter);

        // 设置刷新时的文字提示
        mPtrList.getLoadingLayoutProxy().setRefreshingLabel("努力加载中...");
        // 设置下拉后提示刷新的文字提示
        mPtrList.getLoadingLayoutProxy().setReleaseLabel("释放后刷新");
        // 设置下拉刷新时显示的图片
        mPtrList.getLoadingLayoutProxy().setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        // 下拉刷新时的监听
        mPtrList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                new LoadNewData().execute();
            }
        });

        mPtrList.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(mContext, "已是最后一项", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            data.add("item" + i);
        }
    }

    private class LoadNewData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(1000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            String date = DateUtils.formatDateTime(mContext, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME);
            data.addFirst("new item " + date);
            mAdapter.notifyDataSetChanged();
            // 通知刷新结束
            mPtrList.onRefreshComplete();
        }
    }

}
