package com.evan.demo.ui.activity.demoview;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.evan.demo.model.bean.ActivityInfo;
import com.evan.demo.ui.activity.base.BaseListActivity;
import com.evan.demo.utils.IntentUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Android Metrial Design 新UI控件Demo
 * Created by evanyu on 16/10/10.
 */
public class MDUIDemo extends BaseListActivity {

    private List<ActivityInfo> mActivityList = Arrays.asList(
            new ActivityInfo(RecyclerViewDemo.class, "RecyclerView")
    );

    @Override
    protected void onCreate() {
        super.onCreate();
        ArrayAdapter<ActivityInfo> adapter = new ArrayAdapter<ActivityInfo>(mContext, android.R.layout.simple_list_item_1, mActivityList);
        setListAdapter(adapter);
        setOnListItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentUtils.startActivity(mContext, mActivityList.get(position).clazz);
            }
        });
    }

}
