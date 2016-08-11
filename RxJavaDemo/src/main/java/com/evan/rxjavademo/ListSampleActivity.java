package com.evan.rxjavademo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by evanyu on 16/8/2.
 */
public class ListSampleActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView mListview;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private List<String> data = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sample);
        ButterKnife.bind(this);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        mListview.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // loadDataTest1();
                loadDataTest2();
                // loadDataTest3();
            }
        });

    }

    private void loadDataTest3() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i <= 100; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).sample(50, TimeUnit.MILLISECONDS)
        .map(new Func1<Integer, String>() {
            @Override
            public String call(Integer i) {
                return "item" + i;
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(String s) {
                data.add(s);
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadDataTest2() {
        /*Observable.just("aaa", "bbb", "ccc")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        data.add(s);
                        mAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });*/
        String[] arr = {"aaa", "bbb", "ccc", "ddd"};
        Observable.from(arr)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        data.add(s);
                        mAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void loadDataTest1() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i <= 100; i++) {
                    subscriber.onNext("item" + i);
                }
                subscriber.onCompleted();
            }
        })
        /*.filter(new Func1<String, Boolean>() { // 过滤
            @Override
            public Boolean call(String s) {
                return s.contains("1");
            }
        })*/
        //.take(3) // 只取前三个数据
        //.takeLast(3)
        .toSortedList(new Func2<String, String, Integer>() {
            @Override
            public Integer call(String s, String s2) {
                return 0;
            }
        }) // 将类型转变成List<T>类型
        .subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> listData) {
                data.clear();
                data.addAll(listData);
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
