package com.evan.imageloader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.listView)
    ListView mListView;

    private ActivityInfo[] activityData = {
            new ActivityInfo("glide", GlideDemo.class)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayAdapter<ActivityInfo> adapter = new ArrayAdapter<ActivityInfo>(this, android.R.layout.simple_list_item_1, activityData);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, activityData[position].clazz));
    }
}
