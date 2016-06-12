package com.evan.demo.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.evan.demo.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting: // 设置

                break;
            case R.id.menu_feedback: // 用户反馈

                break;
            default:
                break;
        }
        return true;
    }
}
