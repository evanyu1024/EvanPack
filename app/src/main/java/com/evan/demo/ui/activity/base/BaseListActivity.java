package com.evan.demo.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.evan.demo.R;

/**
 * 页面中默认包含一个ListView
 * Created by evanyu on 16/10/10.
 */
public class BaseListActivity extends BaseActivity {

    protected ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onCreate() {
        setContentView(R.layout.layout_listview);
        mListView = (ListView) findViewById(R.id.listview);
    }

    protected void setListAdapter(BaseAdapter adapter) {
        mListView.setAdapter(adapter);
    }

    protected void setOnListItemClickListener(AdapterView.OnItemClickListener listener) {
        mListView.setOnItemClickListener(listener);
    }

}
