package com.evan.demo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.evan.demo.R;
import com.evan.demo.ui.view.SwitchTab;
import com.evan.demo.utils.ToastUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_main);
        SwitchTab switchTab = (SwitchTab) findViewById(R.id.switch_tab);
        if(switchTab != null) {
            switchTab.setOnTabClickListener(new SwitchTab.OnTabClickListener() {
                @Override
                public void onClick(View view, int state) {
                    switch (state) {
                        case SwitchTab.OnTabClickListener.STATE_LEFT:
                            ToastUtils.showToastShort("左");
                            break;
                        case SwitchTab.OnTabClickListener.STATE_RIGHT:
                            ToastUtils.showToastShort("右");
                            break;
                        default:
                            break;
                    }
                }
            });
        }
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

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
