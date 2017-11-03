package com.evan.demo.ui.widget.keyboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.evan.demo.R;

import java.lang.reflect.Method;

/**
 * 数字键盘
 * Created by evanyu on 16/11/23.
 */
public class NumberKeyboardView extends BaseKeyboardView {

    private Context mContext;
    private EditText mEditText;

    public NumberKeyboardView(Context context, EditText editText) {
        super(context);
        mContext = context;
        mEditText = editText;
        initView();
        initEditText();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_keyboard_number, this, true);
    }

    private void initEditText() {
        // 点击输入框时不显示系统键盘
        try {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(mEditText, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NumberKeyboardView.this.isFocused()) {

                }
            }
        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // dismiss();
                }
            }
        });
    }

    public void show() {

    }

}
