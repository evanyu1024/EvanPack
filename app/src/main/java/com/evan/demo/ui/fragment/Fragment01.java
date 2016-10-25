package com.evan.demo.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.evan.demo.R;
import com.evan.demo.model.bean.ActivityInfo;
import com.evan.demo.ui.activity.ConstraintLayoutDemo;
import com.evan.demo.ui.activity.CustomKeyboardDemo;
import com.evan.demo.ui.activity.LoginActivity;
import com.evan.demo.ui.activity.MDUIActivity;
import com.evan.demo.ui.activity.RadioGroupTestActivity;
import com.evan.demo.ui.activity.RecyclerViewAdapterDemo;
import com.evan.demo.ui.activity.StorageUtilsTestActivity;
import com.evan.demo.ui.activity.SuperRadioGroupDemo;
import com.evan.demo.ui.activity.TabGroupDemo;
import com.evan.demo.ui.activity.ToastTestActivity;
import com.evan.demo.ui.activity.VideoViewDemo;
import com.evan.demo.ui.activity.VitamioDemo;
import com.evan.demo.ui.fragment.base.BaseFragment;
import com.evan.demo.utils.IntentUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Fragment01
 * Created by evanyu on 16/8/9.
 */
public class Fragment01 extends BaseFragment {

    @BindView(R.id.listview)
    ListView mListView;

    private List<ActivityInfo> mActivityList = Arrays.asList(
            new ActivityInfo(LoginActivity.class, "MVP Demo"),
            new ActivityInfo(SuperRadioGroupDemo.class, "SuperRadioGroupDemo"),
            new ActivityInfo(TabGroupDemo.class, "TabGroupDemo"),
            new ActivityInfo(RecyclerViewAdapterDemo.class, "RecyclerViewAdapter"),
            new ActivityInfo(ToastTestActivity.class, "ToastUtils"),
            new ActivityInfo(StorageUtilsTestActivity.class, "StorageUtils"),
            new ActivityInfo(RadioGroupTestActivity.class, "CustomRadioGroup"),
            new ActivityInfo(ConstraintLayoutDemo.class, "ConstraintLayout"),
            new ActivityInfo(CustomKeyboardDemo.class, "CustomKeyboardDemo"),
            new ActivityInfo(MDUIActivity.class, "Metrial Design"),
            new ActivityInfo(VitamioDemo.class, "VitamioDemo"),
            new ActivityInfo(VideoViewDemo.class, "VideoViewDemo")
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
