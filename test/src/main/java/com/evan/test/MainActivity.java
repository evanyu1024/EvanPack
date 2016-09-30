package com.evan.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private EditText mEtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtTest = (EditText) findViewById(R.id.et_test);

        mEtTest.addTextChangedListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.toolbar_back);
    }

    public void onClick(View view) {
        Log.d("mtag", StorageUtils.isExternalStorageMounted() + "......" + StorageUtils.getExternalStoragePath());
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Log.d("mtag", s + "..." + start + "..." + count + "..." + after);
    }

    /**
     * @param s
     * @param start  光标操作的位置
     * @param before 删除字符的数量
     * @param count  添加字符的数量
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("mtag", s + "..." + start + "..." + before + "..." + count);
        mEtTest.removeTextChangedListener(this);
        int startCount = getBlankCount(s);
        String bankCardId = formatBankCardId(s);
        int endCount = getBlankCount(bankCardId);
        mEtTest.setText(bankCardId);
        Log.d("mtag", startCount + "..." + endCount);
        if (count > 0) {
            start += count + endCount - startCount;
        } else if (before > 0) {
            start -= before + startCount - endCount;
        }
        mEtTest.setSelection(start);
        mEtTest.addTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public int getBlankCount(CharSequence s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }

    /**
     * 格式化银行卡号(添加空格)
     * 示例: 8888 8888 8888 8888 888
     */
    public static String formatBankCardId(CharSequence bankCardId) {
        String content = bankCardId.toString().replaceAll(" ", "");
        String result = bankCardId.toString();
        if (content.length() > 16) {
            String a = content.substring(0, 4);
            String b = content.substring(4, 8);
            String c = content.substring(8, 12);
            String d = content.substring(12, 16);
            String e = content.substring(16, content.length());
            result = a + " " + b + " " + c + " " + d + " " + e;
        } else if (content.length() > 12) {
            String a = content.substring(0, 4);
            String b = content.substring(4, 8);
            String c = content.substring(8, 12);
            String d = content.substring(12, content.length());
            result = a + " " + b + " " + c + " " + d;
        } else if (content.length() > 8) {
            String a = content.substring(0, 4);
            String b = content.substring(4, 8);
            String c = content.substring(8, content.length());
            result = a + " " + b + " " + c;
        } else if (content.length() > 4) {
            String a = content.substring(0, 4);
            String b = content.substring(4, content.length());
            result = a + " " + b;
        }
        return result;
    }

    public void onClick2(View view) {
        TextView tv = (TextView) findViewById(R.id.tv_test);
        tv.setText(null);
    }
}
