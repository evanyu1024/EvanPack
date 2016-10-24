package com.evan.httpdemo.ui.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.evan.httpdemo.R;
import com.evan.httpdemo.constant.NetConstant;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    @BindView(R.id.iv)
    ImageView mImageView;

    private Activity mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    // 普通的get请求
    @OnClick(R.id.btn_doget)
    public void doGet(View view) {
        // http://api.k780.com:88/?app=weather.today&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json
        String url = "http://api.k780.com:88/"
                + "?app=weather.today"
                + "&weaid=1"
                + "&&appkey=" + NetConstant.APP_KEY
                + "&sign=" + NetConstant.SIGN
                + "&format=json";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);

        // 1. 同步请求
        /*try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // 2. 异步请求
        // 注意:该回调接口中的方法是在子线程中被调用的
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String data = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, data, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    // 普通的post请求
    @OnClick(R.id.btn_dopost)
    public void doPost(View view) {
        OkHttpClient client = new OkHttpClient();
        // 这里的RequestBody对象的创建从3.0版本开始发生了改变
        // 3.0之前使用: newengine FormEncodingBuilder().add(key,value).add(key,value)...build();
        // 3.0之后使用: newengine FormBody.Builder().add()...build();
        RequestBody requestBody = new FormBody.Builder()
                .add("app", "weather.today")
                .add("weaid", "1")
                .add("appkey", NetConstant.APP_KEY)
                .add("sign", NetConstant.SIGN)
                .add("format", "json")
                .build();
        Request request = new Request.Builder()
                .url(NetConstant.BASE_URL)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, data, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    // 上传文件
    @OnClick(R.id.btn_upload_file)
    public void uploadFile(View view) {

    }

    // 下载文件
    @OnClick(R.id.btn_download_file)
    public void downloadFile(View view) {
        // 百度logo
        String url = "https://www.baidu.com/img/bd_logo1.png";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "访问失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = response.body().byteStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = is.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                byte[] data = out.toByteArray();
                final Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(bm);
                    }
                });
            }
        });
    }

}
