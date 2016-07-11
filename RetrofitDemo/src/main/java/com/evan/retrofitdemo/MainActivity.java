package com.evan.retrofitdemo;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ListView listView;
    private ImageView imageView;

    private String[] items = {"简单get请求", "QueryMap+GSON转换", "下载图片", "下载大文件"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Logger.init("mtag");

        imageView = (ImageView) findViewById(R.id.imageView);
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // @get @path @query
                        test1();
                        break;
                    case 1: // QueryMap+GSON转换
                        test2();
                        break;
                    case 2: // 下载图片+@url
                        test3();
                        break;
                    case 3: // 下载大文件(@Streaming)
                        test4();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void test1() {
        // 创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstant.BASE_URL)
                .build();
        // 获取接口的实例
        MyApiService myApiService = retrofit.create(MyApiService.class);

        // 获取Call对象
        // Call<ResponseBody> call = myApiService.getWeatherData();
        Call<ResponseBody> call = myApiService.getWeatherData("weather.today", 1, NetConstant.APP_KEY, NetConstant.SIGN, "json");

        // 发起get异步请求(回调方法运行在主线程中)
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void test2() {
        // 创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // 添加 GsonConverter (使用GSON将JSON数据解析成对象)
                .build();
        // 获取接口的实例
        MyApiService myApiService = retrofit.create(MyApiService.class);

        Map<String, Object> map = new HashMap<>();
        map.put("app", "weather.today");
        map.put("weaid", "1");
        map.put("appkey", NetConstant.APP_KEY);
        map.put("sign", NetConstant.SIGN);
        map.put("format", "json");
        Call<Weather> call = myApiService.getWeatherData(map);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    Logger.d(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
    }

    private void test3() {
        // 创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // 添加 GsonConverter (使用GSON将JSON数据解析成对象)
                .build();
        // 获取接口的实例
        MyApiService myApiService = retrofit.create(MyApiService.class);

        String imgUrl = "https://www.baidu.com/img/bd_logo1.png";
        Call<ResponseBody> call = myApiService.downloadImage(imgUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // InputStream is = response.body().byteStream();
                // imageView.setImageBitmap(BitmapFactory.decodeStream(is));

                // 默认一次性将文件下载到内存中
                try {
                    byte[] data = response.body().bytes();
                    imageView.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void test4() {
        // 下载大文件
        // 必须在子线程中执行,因为当下载大文件时,需要边下边写(此时一直都在执行网络访问操作)
        new Thread(new Runnable() {
            @Override
            public void run() {
                /*
                // 创建Retrofit对象
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(NetConstant.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()) // 添加 GsonConverter (使用GSON将JSON数据解析成对象)
                        .build();
                // 获取接口的实例
                MyApiService myApiService = retrofit.create(MyApiService.class);
                Call<ResponseBody> call = myApiService.downloadBigFile(fileUrl);
                Response<ResponseBody> response = call.execute();
                response.body().byteStream();
                ...
                */
            }
        }).start();

    }


    @Override
    protected void onStop() {
        super.onStop();
        // 取消网络请求
        // call.cancel();
    }
}
