package com.evan.demo.ui.activity.demoview;

import android.view.View;
import android.widget.EditText;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.utils.LogUtils;

/**
 * KeyBoardDemo
 *
 * @author evanyu
 * @date 16/10/14
 */
public class KeyBoardDemo extends BaseActivity {

    private EditText mEditText;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_keyboard);
        mEditText = (EditText) findViewById(R.id.edittext);

//        this.getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        // 点击输入框时不显示系统键盘
//        try {
//            Class<EditText> cls = EditText.class;
//            Method setShowSoftInputOnFocus;
//            setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
//                    boolean.class);
//            setShowSoftInputOnFocus.setAccessible(true);
//            setShowSoftInputOnFocus.invoke(mEditText, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        mEditText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                KeyBoardDemo.this.getWindow().setSoftInputMode(
//                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//                try {
//                    Class<EditText> cls = EditText.class;
//                    Method setShowSoftInputOnFocus;
//                    setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
//                            boolean.class);
//                    setShowSoftInputOnFocus.setAccessible(true);
//                    setShowSoftInputOnFocus.invoke(mEditText, false);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public void onBtnClick(View view) {
//        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
////        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        LogUtils.d(mEditText.getSelectionStart() + "...." + mEditText.getSelectionEnd());
    }

}