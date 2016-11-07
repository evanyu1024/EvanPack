package com.evan.demo.ui.activity.demoview;

import android.graphics.BitmapFactory;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;

import com.evan.demo.R;
import com.evan.demo.ui.activity.base.BaseActivity;
import com.evan.demo.utils.LogUtils;
import com.evan.demo.utils.StorageUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by evanyu on 16/8/31.
 */
public class StorageUtilsTestActivity extends BaseActivity {

    @BindView(R.id.imageview)
    ImageView mImageView;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_test_storageutils);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_read_info:

                break;
            case R.id.btn_read_file:
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.btn_read_info)
    void readInfo(View view) {
        LogUtils.d("path = " + StorageUtils.getExternalStoragePath());
        LogUtils.d("freeSize = " + Formatter.formatFileSize(this, StorageUtils.getExternalStorageAvailableSize()));
        LogUtils.d("totalSize = " + Formatter.formatFileSize(this, StorageUtils.getExternalStorageTotalSize()));
    }

    @OnClick(R.id.btn_read_file)
    void readFile(View view) {
        byte[] data = StorageUtils.readData(new File(StorageUtils.getExternalStoragePath(), "pic.png"));
        mImageView.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
    }

}
