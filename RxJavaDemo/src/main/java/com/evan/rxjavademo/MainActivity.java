package com.evan.rxjavademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView mListView;

    ImageView mImageView;

    private List<ActivityInfo> activitys = Arrays.asList(
            new ActivityInfo(SimpleUseActivity.class, "简单使用"),
            new ActivityInfo(ListSampleActivity.class, "在列表中加载数据"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, activitys);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();

                Student stu = null;
                intent.putExtra("type", stu);
                stu = (Student)intent.getSerializableExtra("type");
                startActivity(new Intent(MainActivity.this, activitys.get(position).clazz));
            }
        });
    }

    class Student implements Serializable {

    }

}
