package com.evan.demo.ui.activity.demoview;

import android.view.View;
import android.widget.Toast;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.ui.widget.MyToast;

/**
 *
 * Created by evanyu on 16/8/31.
 */
public class ToastTestActivity extends BaseActivity {

    private MyToast mToast;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_test_toast);
        mToast = MyToast.makeText(this, "哈哈", Toast.LENGTH_LONG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                mToast.show();
                break;
            case R.id.btn02:
                mToast.cancel();
                break;
            case R.id.btn03:
                break;
            case R.id.btn04:
                // mToast.cancel();
                break;
            default:
                break;
        }
    }

}
