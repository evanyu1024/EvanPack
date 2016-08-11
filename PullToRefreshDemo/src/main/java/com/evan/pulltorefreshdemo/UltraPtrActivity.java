package com.evan.pulltorefreshdemo;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class UltraPtrActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.ptr_fml)
    PtrClassicFrameLayout mPtrFml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra_ptr);
        ButterKnife.bind(this);

        // 以下设置都可在xml中进行设置
        mPtrFml.setResistance(1.7f); // 阻尼系数(默认1.7),数值越大,感觉下拉时越吃力
        mPtrFml.setRatioOfHeaderHeightToRefresh(1.2f); // 触发刷新时移动的位置比例(默认1.2),移动达到头部高度1.2倍时可触发刷新操作
        mPtrFml.setDurationToClose(200); // 回弹延时(默认200ms),回弹到刷新高度所用时间
        mPtrFml.setDurationToCloseHeader(1000); // 头部回弹时间(默认1000ms)
        mPtrFml.setKeepHeaderWhenRefresh(true); // 刷新时保持头部(默认true)
        mPtrFml.setPullToRefresh(false); // 下拉刷新/释放刷新(默认为释放刷新)

        mPtrFml.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                /*frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFml.refreshComplete();
                    }
                }, 1800);*/
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(1500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mPtrFml.refreshComplete();
                            }
                        });
                    }
                }).start();

                // mPtrFml.setLastUpdateTimeRelateObject(new Object());
            }
        });

        mPtrFml.setLastUpdateTimeRelateObject(this);

//        TextView ptrHeader = new TextView(this);
//        ptrHeader.setText("下拉刷新");
//        mPtrFml.setHeaderView(ptrHeader);
    }
}
