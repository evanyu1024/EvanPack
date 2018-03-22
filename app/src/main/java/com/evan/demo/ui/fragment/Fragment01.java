package com.evan.demo.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.evan.demo.R;
import com.evan.demo.model.bean.ActivityInfo;
import com.evan.demo.ui.activity.LoginActivity;
import com.evan.demo.ui.activity.demoview.EventDispatchDemo;
import com.evan.demo.ui.activity.demoview.KeyBoardDemo;
import com.evan.demo.ui.activity.demoview.MDUIDemo;
import com.evan.demo.ui.activity.demoview.RadioGroupTestActivity;
import com.evan.demo.ui.activity.demoview.RecyclerViewAdapterDemo;
import com.evan.demo.ui.activity.demoview.RecyclerViewDemo;
import com.evan.demo.ui.activity.demoview.ScrollViewAndRecyclerView;
import com.evan.demo.ui.activity.demoview.ScrollViewDemo;
import com.evan.demo.ui.activity.demoview.StorageUtilsTestActivity;
import com.evan.demo.ui.activity.demoview.SuperRadioGroupDemo;
import com.evan.demo.ui.activity.demoview.TabGroupDemo;
import com.evan.demo.ui.activity.demoview.ToastTestActivity;
import com.evan.demo.ui.activity.demoview.VideoViewDemo;
import com.evan.demo.ui.activity.demoview.db.DataBaseDemo;
import com.evan.demo.ui.fragment.base.BaseFragment;
import com.evan.demo.utils.IntentUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Fragment01
 *
 * @author evanyu
 * @date 16/8/9
 */
public class Fragment01 extends BaseFragment {

    @BindView(R.id.listview)
    ListView mListView;

    private List<ActivityInfo> mActivityList = Arrays.asList(
            new ActivityInfo(LoginActivity.class, "MVP Demo"),
            new ActivityInfo(SuperRadioGroupDemo.class, "SuperRadioGroupDemo"),
            new ActivityInfo(TabGroupDemo.class, "TabGroupDemo"),
            new ActivityInfo(RecyclerViewAdapterDemo.class, "RecyclerViewAdapter"),
            new ActivityInfo(RecyclerViewDemo.class, "RecyclerViewDemo"),
            new ActivityInfo(ScrollViewAndRecyclerView.class, "ScrollViewAndRecyclerView"),
            new ActivityInfo(ToastTestActivity.class, "ToastUtils"),
            new ActivityInfo(StorageUtilsTestActivity.class, "StorageUtils"),
            new ActivityInfo(RadioGroupTestActivity.class, "CustomRadioGroup"),
            new ActivityInfo(MDUIDemo.class, "Metrial Design"),
            new ActivityInfo(VideoViewDemo.class, "VideoViewDemo"),
            new ActivityInfo(KeyBoardDemo.class, "KeyBoardDemo"),
            new ActivityInfo(EventDispatchDemo.class, "EventDispatchDemo"),
            new ActivityInfo(ScrollViewDemo.class, "ScrollView"),
            new ActivityInfo(DataBaseDemo.class, "DataBase Demo")
    );

    @Override
    public int getLayoutResId() {
        return R.layout.simple_listview;
    }

    @Override
    public void onViewCreated(View view) {
        ButterKnife.bind(this, view);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mActivityList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentUtils.startActivity(getActivity(), mActivityList.get(position).clazz);
            }
        });
    }
}
