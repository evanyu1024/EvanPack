package com.evan.pulltorefreshdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivityInfo[] activityData = {
            new ActivityInfo("PullToRefresh", PtrActivity.class),
            new ActivityInfo("UltraPullToRefresh", UltraPtrActivity.class)
    };

    @butterknife.BindView(R.id.listView)
    ListView mListView;

    ArrayAdapter<ActivityInfo> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butterknife.ButterKnife.bind(this);

        mAdapter = new ArrayAdapter<ActivityInfo>(this, android.R.layout.simple_list_item_1, activityData);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, activityData[position].clazz));
    }

}
