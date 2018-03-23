package com.evan.demo.ui.activity.demoview;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.Toast;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.utils.MultiClickHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Android Metrial Design 新UI控件Demo
 *
 * @author evanyu
 * @date 16/10/10
 */
public class MetrialDesignDemo extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.floating_action_button)
    FloatingActionButton mFloatingActionButton;

    private MultiClickHelper mMultiClickHelper = new MultiClickHelper();

    @Override
    protected void onCreate() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_metrial_design);
        ButterKnife.bind(this);

        // init ToolBar
        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);

        mFloatingActionButton.setOnClickListener(v -> {
            Toast.makeText(mContext, "FloatingActionButton", Toast.LENGTH_SHORT).show();
        });

//        mFloatingActionButton.setClickable(true);
    }

//    private List<ActivityInfo> mActivityList = Arrays.asList(
//            new ActivityInfo(RecyclerViewDemo.class, "RecyclerView")
//    );
//
//    @Override
//    protected void onCreate() {
//        super.onCreate();
//        ArrayAdapter<ActivityInfo> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mActivityList);
//        setListAdapter(adapter);
//        setOnListItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                IntentUtils.startActivity(mContext, mActivityList.get(position).clazz);
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        if (mMultiClickHelper.click()) {
            finish();
        } else {
            Snackbar.make(mToolbar, "再按一次返回上级页面", Snackbar.LENGTH_SHORT)
                    .setAction("show toast", v -> Toast.makeText(MetrialDesignDemo.this, "test", Toast.LENGTH_SHORT).show())
                    .setActionTextColor(Color.GREEN)
                    .show();
        }
    }
}
