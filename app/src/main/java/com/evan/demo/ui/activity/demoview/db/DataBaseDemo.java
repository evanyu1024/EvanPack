package com.evan.demo.ui.activity.demoview.db;

import android.view.View;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author evanyu
 * @date 17/11/27
 */

public class DataBaseDemo extends BaseActivity {

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_wcdb)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_wcdb:
                startActivity(WCDBDemo.class);
                break;
            default:
                break;
        }
    }
}
