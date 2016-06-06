package com.evan.imageloader;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideDemo extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_load_img)
    public void onClick() {
        // 网络图片
        // Glide.with(this).load(Constant.url).into(mImageView);
        // drawable资源
        // Glide.with(this).load(R.drawable.bd_logo).into(mImageView);
        // 资产目录中的图片
        // Glide.with(this).load(readFromAssets("bd_logo.png")).into(mImageView);
        // 加载GIF
        // Glide.with(this).load("http://img2.imgtn.bdimg.com/it/u=1608871163,3223894477&fm=21&gp=0.jpg").into(mImageView);
        Glide.with(this).load(R.raw.splash_gif).into(mImageView);

        // 其他设置
        // 占位图
        // .placeholder(R.drawable.placeholder)
        // 加载图
        // .error(R.drawable.imagenotfound)
        // 既缓存全尺寸又缓存其他尺寸(Glide默认会缓存图片,即使是同一张图片的不同尺寸也会缓存)
        // .diskCacheStrategy(DiskCacheStrategy.ALL)
        // 重新调整图片大小
        // .override(300, 200);
        // 去除过度动画
        // .dontAnimate()

        // .centerCrop();
        // .transform(new CircleTransform(context))

    }

    @OnClick(R.id.btn_load_video)
    public void loadVideo() {
        String path = Environment.getExternalStorageDirectory()+"/xpg.mp4";
        Glide.with(this).load(Uri.fromFile(new File(path))).into(mImageView);
       // Glide.with(this).load(Uri.fromFile(new File(path))).into();
    }

    private byte[] readFromAssets(String fileName) {
        InputStream is = null;
        try {
            is = this.getAssets().open(fileName);
            return readFromStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null) {
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
