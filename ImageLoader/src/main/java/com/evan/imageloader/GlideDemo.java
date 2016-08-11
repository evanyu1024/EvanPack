package com.evan.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideDemo extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView mImageView;

    // Glide加载图像时使用的目标对象,会在图像加载完成后执行回调方法
    // (如果使用这种方式加载图像,控件大小不确定,默认加载全尺寸图像,可以在构造方法中自己手动设置)
    private SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>(250, 250) {
        @Override
        public void onResourceReady(Bitmap bm, GlideAnimation<? super Bitmap> glideAnimation) {
            mImageView.setImageBitmap(bm);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_load_img)
    public void onClick() {
        Glide.with(this) // picasso只能是Conetext类型,而Glide支持Context,Activity,Fragment等类型
                .load("https://www.baidu.com/img/bd_logo1.png") // 网络图片
                //.load(R.drawable.bd_logo) // drawable资源
                //.load(readFromAssets("bd_logo.png")) // 资产目录中的图片
                //.load(R.raw.splash_gif) // gif图片(这行代码存在问题,加载很慢,待解决)
                //.load(resIdToUri(this, R.drawable.bd_logo)) // uri对象(uri不必从资源中获取,可以是任何uri)
                //.load(Uri.fromFile(new File(path))) // 加载视频(只能是本地视频)
                //.asGif() // 判断url是否是一个gif图,如果不是的话就加载失败(即使这个url访问的是一个完整的图片)
                .asBitmap() // 如果是已将gif图则只会加载gif的第一祯
                .placeholder(R.mipmap.ic_launcher) // 占位图(加载时显示)
                .error(R.drawable.error) // 占位图(加载失败时显示)
                //.crossFade(2000) // 淡入淡出效果(默认300ms)
                .dontAnimate() // 去除淡入淡出效果
                .override(200, 300) // 在图片显示之间重新设置图片的尺寸
                //.centerCrop() // 将图片显示在控件冲,并裁剪超出部分
                .fitCenter() // 将图片完全显示在空间中,保证图片的边界一定小于等于控件的边界
                //.skipMemoryCache(true) // 设置是否跳过内存缓存(默认false)
                //.diskCacheStrategy(DiskCacheStrategy.SOURCE) // 调整磁盘缓存策略(参数是一个枚举值)
                //.priority(Priority.HIGH) // 优先级(规则类似于线程的优先级,并不会完全遵守)
                //.thumbnail(0.1f) // 缩略图
                .into(mImageView);
                //.into(ViewTarget) // 用于将图片显示在自定义View中
                //.into(target); // 设置加载完后的回调对象,可在内部的回调方法中处理加载到的图像(如果是Bitmap类型通常需要asBitmap()进行检测)


        /*
         * 磁盘缓存的参数
         *
         * 参数是以下枚举值之一:
         * NONE     禁用磁盘缓存
         * SOURCE   只缓存原来的全分辨率的图像
         * RESULT   只缓存最终显示的图像
         * ALL      缓存所有版本的图像(默认)
         */

    }

    private Uri resIdToUri(Context context, int resId) {
        return Uri.parse("android.resource://" + context.getPackageName() + "/" + resId);
    }

    private byte[] readFromAssets(String fileName) {
        InputStream is = null;
        try {
            is = this.getAssets().open(fileName);
            return readFromStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private byte[] readFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = is.read(buf)) != -1) {
            bos.write(buf, 0, len);
        }
        return bos.toByteArray();
    }
}
