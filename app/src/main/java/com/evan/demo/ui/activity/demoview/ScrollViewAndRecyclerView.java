package com.evan.demo.ui.activity.demoview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.ui.widget.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.evan.demo.utils.LogUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by evanyu on 16/12/19.
 */
public class ScrollViewAndRecyclerView extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_scrollview_and_recyclerview);
        ButterKnife.bind(this);


        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {

            float x, y;
            float dx, dy; // 手指移动距离

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = event.getRawX();
                        y = event.getRawY();
                        mRecyclerView.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        dx = Math.abs(event.getRawX() - x);
                        dy = Math.abs(event.getRawY() - y);
                        double tanValue = Math.toRadians(15);
                        LogUtils.d(dy + "/" + dx + "..." + tanValue);
                        if (dy / dx > Math.tan(Math.toRadians(15))) {
                            mRecyclerView.getParent().requestDisallowInterceptTouchEvent(true);
                        } else {
                            mRecyclerView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mRecyclerView.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
        List<String> data = Arrays.asList("111", "222", "333", "444", "5555", "666", "7777", "8888", "9999", "10101");
        mRecyclerView.setAdapter(new InnerAdapter(mContext, data));
    }

    private static class InnerAdapter extends BaseRecyclerViewAdapter<String> {

        public InnerAdapter(Context context, List<String> data) {
            super(context, data, android.R.layout.simple_list_item_1);
        }

        @Override
        public void convert(InnerViewHolder holder, String text, int position) {
            holder.setText(android.R.id.text1, text);
        }
    }

}
