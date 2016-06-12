package com.evan.webviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webView);
        // 设置滚动条在页面中显示()
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 是否支持JS
        webSettings.setSupportZoom(true); // 是否支持缩放(默认true)
        webSettings.setBuiltInZoomControls(true); // 是否使用缩放工具(默认false)
        webSettings.setDisplayZoomControls(false); // 是否显示缩放工具
        webSettings.setDefaultTextEncodingName("GBK"); // 设置默认编码
        /*
         * LayoutAlgorithm.NORMAL           完整显示页面，没有渲染变化
         * LayoutAlgorithm.SINGLE_COLUMN    把所有内容放到WebView组件等宽的一列中
         * LayoutAlgorithm.NARROW_COLUMNS   可能的话，使所有列的宽度不超过屏幕宽度
         */
        // webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // webSettings.setDefaultFontSize(16); // 设置默认的字体大小，默认为16，有效值区间在1-72之间

        // 在WebView中打开页面,而不会跳转到外部浏览器
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 重写此方法，用于捕捉页面上的跳转链接
                /*if ("http://start/".equals(url)){
                    //在html代码中的按钮跳转地址需要同此地址一致
                    Toast.makeText(getApplicationContext(), "开始体验", Toast.LENGTH_SHORT).show();
                    finish();
                }*/
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        // 加载本地assets文件夹中的本地页面
        // mWebView.loadUrl("file:///android_asset/html/test1.html");
        // 加载远程网页
        // mWebView.loadUrl("http://www.sina.com.cn/");

        // 加载HTML代码
        loadHtmlData();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        // mWebView.goBack();
    }

    public void loadHtmlData() {
        String data = "<html>"
                + "<head>"
                + "<title>欢迎您</title>"
                + "</head>"
                + "<body>"
                + "<p>我是一段html代码</p>"
                + "</body>"
                + "</html>";
        // 使用简单的loadData()方法总会导致乱码，有可能是Android API的Bug
        // webView.loadData(data, "text/html", "GBK");
        mWebView.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
    }
}
